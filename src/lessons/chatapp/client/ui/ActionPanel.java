package lessons.chatapp.client.ui;

import javax.swing.*;
import java.awt.*;

public class ActionPanel {

    private final JPanel panel;
    private final TextField textField = new TextField();

    public ActionPanel(SendActionListener actionListener) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        textField.addActionListener(e -> sendAction(actionListener));

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendAction(actionListener));
        panel.add(sendButton, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }

    private void sendAction(SendActionListener actionListener){
        actionListener.onSend(textField.getText());
        textField.setText("");
    }
}
