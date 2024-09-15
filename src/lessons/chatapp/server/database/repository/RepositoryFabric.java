package lessons.chatapp.server.database.repository;

public class RepositoryFabric {

    public static UserRepository getUserRepository(){
        return new SqliteUserRepository();
    }
}
