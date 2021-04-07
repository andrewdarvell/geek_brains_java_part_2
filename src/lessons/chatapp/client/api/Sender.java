package lessons.chatapp.client.api;

@FunctionalInterface
public interface Sender {
    void send(String data);
}
