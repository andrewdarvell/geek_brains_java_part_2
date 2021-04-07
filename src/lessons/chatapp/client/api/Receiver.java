package lessons.chatapp.client.api;

@FunctionalInterface
public interface Receiver {
    void receive(String data);
}
