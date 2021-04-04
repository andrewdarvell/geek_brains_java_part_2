package lessons.chatapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private final AuthService authService = new AuthService();
    private final Set<ClientHandler> loggedClients = new HashSet<>();

    public void run() {
        run(8081);
    }

    public void run(int port) {
        try {
            System.out.println("Chat server start");
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server get client connection");
                new ClientHandler(clientSocket, this);
            }
        } catch (IOException e) {
            throw new ServerException("Server get error", e);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void broadcast(String message) {
        for (ClientHandler clientHandler : loggedClients) {
            clientHandler.sendMessage(message);
        }
    }

    public void sendToUser(String name, String message) throws ChatUserNotFoundException {
        ClientHandler handler = loggedClients.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ChatUserNotFoundException("User not found"));

        handler.sendMessage("wisp: "+ message);
    }

    public synchronized boolean logout(ClientHandler clientHandler) {
        return loggedClients.remove(clientHandler);
    }

    public synchronized boolean isLoggedIn(String name) {
        return loggedClients.stream()
                .anyMatch(c -> c.getName().equals(name));
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        loggedClients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        loggedClients.remove(clientHandler);
    }
}
