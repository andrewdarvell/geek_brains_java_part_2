package lessons.chatapp.server;

public class ChatUserNotFoundException extends RuntimeException{

    public ChatUserNotFoundException(String message) {
        super(message);
    }

    public ChatUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
