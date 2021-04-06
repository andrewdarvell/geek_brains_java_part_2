package lessons.chatapp.client.ui.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleChatCommunication implements ChatCommunication{

    private final DataInputStream in;
    private final DataOutputStream out;

    public SimpleChatCommunication(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during connection establishing.", e);
        }
    }

    @Override
    public void transmit(String data) {
        try {
            out.writeUTF(data);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during data transmitting.", e);
        }
    }

    @Override
    public String receive() {
        try {
            return in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during data receiving.", e);
        }
    }
}
