package lessons.chatapp.client.ui;

import lessons.chatapp.client.history.ChatHistoryWorker;
import lessons.chatapp.client.ui.communication.ChatCommunication;
import lessons.chatapp.client.ui.communication.SimpleChatCommunication;
import lessons.chatapp.client.api.Receiver;

public class Chat {

    private final ChatWindow frame;
    private final ChatCommunication communication;

    public Chat(String host, int port) {
        communication = new SimpleChatCommunication(host, port);
        String lastMessages = ChatHistoryWorker.readLastMessages(100);
        frame = new ChatWindow(lastMessages, (data) -> communication.transmit(data));

        new Thread(()->{
            Receiver receiver = frame.getReceiver();
            while (true) {
                String msg = communication.receive();
                if (!msg.isBlank()) {
                    receiver.receive(msg);
                    ChatHistoryWorker.writeMessage(msg);
                }
            }
        }).start();
    }
}
