package lessons.chatapp.server.exeptions;

public class ChatUserDuplicateException extends  RuntimeException{
    public ChatUserDuplicateException(String message) {
        super(message);
    }

    public ChatUserDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
