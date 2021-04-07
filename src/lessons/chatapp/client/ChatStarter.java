package lessons.chatapp.client;

import lessons.chatapp.client.ui.Chat;

public class ChatStarter {

    public static void run() {
        run("localhost", 8081);
    }

    public static void run(String host, int port) {
        new Chat(host, port);
    }
}
