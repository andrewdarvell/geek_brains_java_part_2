package lessons.six;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("Server start");
            System.out.println("Waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");
            System.out.println("Enter messages and chat");

            Scanner scanner = new Scanner(System.in);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        out.writeUTF(scanner.nextLine());
                    }
                } catch (IOException e) {
                    System.out.println("Cannot send message to client...");
                }
            }).start();

            try {
                while (true) {
                    String s = in.readUTF();
                    if ("-exit".equals(s)) {
                        System.out.println("Client send shutdown server command... press Enter");
                        break;
                    } else {
                        System.out.println("client> " + s);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client is disconnect... press Enter");
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
