package lessons.chatapp.client.ui;

import lessons.chatapp.client.api.Receiver;
import lessons.chatapp.client.api.Sender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow extends JFrame implements SendActionListener {

    private final TextArea chatText;
    private final Sender sender;

    public ChatWindow(Sender sender) {
        setTitle("My chat 1.0");
        this.sender = sender;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 700);

        setLayout(new BorderLayout());

        chatText = new TextArea();
        chatText.setEditable(false);
        chatText.setFocusable(false);
        add(chatText, BorderLayout.CENTER);

        ActionPanel actionPanel = new ActionPanel(this);
        add(actionPanel.getPanel(), BorderLayout.SOUTH);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sender.send("-exit");
                super.windowClosing(e);
            }
        });

    }

    @Override
    public void onSend(String message) {
        if (!message.isBlank()) {
            sender.send(message);
            chatText.append("\n");
        }
    }

    public Receiver getReceiver() {
        return (message) -> {
            if (!message.isBlank()) {
                chatText.append(message);
                chatText.append("\n");
            }
        };
    }
}
