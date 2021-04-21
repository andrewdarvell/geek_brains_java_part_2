package lessons.chatapp.client.exceptions;

public class ChatClientException extends RuntimeException {

    public ChatClientException(String message) {
        super(message);
    }

    public ChatClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
