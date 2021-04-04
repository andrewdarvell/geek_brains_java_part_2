package lessons.chatapp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {

    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
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
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("-auth")) {
                    String[] credentialsStruct = message.split("\\s");
                    String login = credentialsStruct[1];
                    String password = credentialsStruct[2];

                    Optional<AuthService.Entry> mayBeCredentials = server.getAuthService()
                            .findEntryByCredentials(login, password);

                    if (mayBeCredentials.isPresent()) {
                        AuthService.Entry credentials = mayBeCredentials.get();
                        if (!server.isLoggedIn(credentials.getName())) {
                            name = credentials.getName();
                            server.broadcast(String.format("User[%s] entered the chat", name));
                            server.subscribe(this);
                            sendMessage(String.format("Welcome to chat User[%s]", name));
                            return;
                        } else {
                            sendMessage(String.format("User with name %s is already logged in", credentials.getName()));
                        }
                    } else {
                        sendMessage("Incorrect login or password.");
                    }
                }
            } catch (IOException e) {
                throw new ServerException("Something went wrong during authorization.", e);
            }
        }

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
                } else {
                    server.broadcast(String.format("%s: %s", name, message));
                }
            } catch (IOException e) {
                doLogout();
                throw new ServerException("Something went wrong during receiving the message.", e);
            }
        }

    }

    private void doLogout(){
        server.logout(this);
        closeConnection();
    }

    private void closeConnection() {
        try {
            socket.close();
        } catch (IOException e){
            throw new ServerException("Something went wrong when close connection", e);
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
}
