package lessons.chatapp.server.database.repository;

import lessons.chatapp.server.exeptions.ChatUserDuplicateException;
import lessons.chatapp.server.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAllUsers();
    Optional<User> getUserByName(String userName);
    Optional<User> getUserByCredentials(String login, String password);
    void changeNickName(String oldNick, String newNick) throws ChatUserDuplicateException;

}
