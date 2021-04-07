package lessons.chatapp.client.ui.communication;

public interface ChatCommunication {

    void transmit(String data);
    String receive();
}
