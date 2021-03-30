package lessons.six;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8081);

            System.out.println("Connected to server");
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
                    System.out.println("Cannot send message...");
                }
            }).start();


            try {
                while (true) {
                    System.out.println("Server> " + in.readUTF());
                }
            } catch (IOException e) {
                System.out.println("server is down... press Enter");
            }


        } catch (IOException e) {
            System.out.println("Cannot connect to server");
        }
    }
}
