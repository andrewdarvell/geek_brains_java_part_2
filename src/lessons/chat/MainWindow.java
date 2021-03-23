package lessons.chat;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements SendActionListener{

    private final TextArea chatText;

    public MainWindow() {
        setTitle("My chat 1.0");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 700);

        setLayout(new BorderLayout());

        chatText = new TextArea();
        add(chatText, BorderLayout.CENTER);

        ActionPanel actionPanel = new ActionPanel(this);
        add(actionPanel.getPanel(), BorderLayout.SOUTH);

        setVisible(true);

    }

    @Override
    public void onSend(String message) {
        chatText.append(message);
        chatText.append("\n");
    }
}
