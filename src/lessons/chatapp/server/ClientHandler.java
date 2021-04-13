package lessons.chatapp.server;

import lessons.chatapp.server.database.repository.RepositoryFabric;
import lessons.chatapp.server.database.repository.UserRepository;
import lessons.chatapp.server.exeptions.ChatUserDuplicateException;
import lessons.chatapp.server.exeptions.ServerException;
import lessons.chatapp.server.model.User;
import lessons.chatapp.server.exeptions.ChatUserNotFoundException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {

    private final int AUTH_TIMEOUT_SECONDS = 120;

    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final AtomicBoolean authorized = new AtomicBoolean(false);
    private String name;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ServerException("Client can't connect", e);
        }

        new Thread(() -> {
            doAuth();
            listen();
        }).start();

    }

    private void listen() {
        receiveMessage();
    }

    private void doAuth() {
        sendMessage("Welcome! Please do authentication.");
        startAuthTimer();
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("-auth")) {
                    String[] credentialsStruct = message.split("\\s");
                    String login = credentialsStruct[1];
                    String password = credentialsStruct[2];

                    UserRepository userRepository = RepositoryFabric.getUserRepository();
                    Optional<User> maybeUser = userRepository.getUserByCredentials(login, password);

//                    Optional<AuthService.Entry> mayBeCredentials = server.getAuthService()
//                            .findEntryByCredentials(login, password);

                    if (maybeUser.isPresent()) {
                        User user = maybeUser.get();
                        if (!server.isLoggedIn(user.getName())) {
                            setAuthorized();
                            name = user.getName();
                            server.broadcast(String.format("User[%s] entered the chat", name));
                            server.subscribe(this);
                            sendMessage(String.format("Welcome to chat User[%s]", name));
                            return;
                        } else {
                            sendMessage(String.format("User with name %s is already logged in", user.getName()));
                        }
                    } else {
                        sendMessage("Incorrect login or password.");
                    }
                } else {
                    sendMessage("Incorrect login command [-auth login pasword]");
                }
            } catch (IOException e) {
                throw new ServerException("Something went wrong during authorization.", e);
            }
        }
    }

    public void startAuthTimer() {
        new Thread(() -> {
            try {
                Thread.sleep(AUTH_TIMEOUT_SECONDS * 1000);
                if (!isAuthorized()) {
                    System.out.println("close connection");
                    sendMessage("Connection clothed by auth timeout");
                    closeConnection();
                } else {
                    System.out.println("Client authorized");
                }
            } catch (InterruptedException e) {
                throw new ServerException("Auth timer interrupted", e);
            }
        }).start();
    }

    public void receiveMessage() {
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("/w")) {
                    doPrivateMessage(message);
                } else if (message.startsWith("-exit")) {
                    doLogout();
                    server.broadcast(String.format("User[%s] exit the chat", name));
                    return;
                } else if (message.startsWith("-change_name")) {
                    doChangeNickName(message);
                } else {
                    server.broadcast(String.format("%s: %s", name, message));
                }
            } catch (IOException e) {
                doLogout();
                throw new ServerException("Something went wrong during receiving the message.", e);
            }
        }
    }


    private void doLogout() {
        server.logout(this);
        closeConnection();
    }

    private void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new ServerException("Something went wrong when close connection", e);
        }
    }

    private void doChangeNickName(String message) {
        String newNick = message.replace("-change_name", "").strip().replaceAll("\\s", "");
        if (newNick.length() > 0) {
            UserRepository userRepository = RepositoryFabric.getUserRepository();
            try {
                userRepository.changeNickName(name, newNick);
                server.broadcast(String.format("User [%s] change nick to [%s]", name, newNick));
                name = newNick;

            } catch (ChatUserDuplicateException e) {
                sendMessage(e.getMessage());
            }
        } else {
            sendMessage("please try in [-change_name newName] format");
        }

    }

    private void doPrivateMessage(String message) {
        message = message.replace("/w ", "");
        if (!message.isBlank()) {

            if (message.matches(".+\\s.+")) {

                String toUserName = message.substring(0, message.indexOf(" "));
                String toUserMessage = message.substring(message.indexOf(" ") + 1);

                try {
                    server.sendToUser(toUserName, String.format("%s: %s", name, toUserMessage));
                    sendMessage(String.format("w:%s->%s: %s", name, toUserName, toUserMessage));

                } catch (ChatUserNotFoundException e) {
                    sendMessage(String.format("User %s not found", toUserName));
                }
            } else {
                sendMessage("please try in [/w name message] format");
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            doLogout();
            throw new ServerException("Something went wrong during sending the message.", e);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAuthorized() {
        return authorized.get();
    }

    public void setAuthorized() {
        this.authorized.compareAndSet(false, true);
    }
}
