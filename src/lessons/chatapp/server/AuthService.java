package lessons.chatapp.server;

import java.util.Optional;
import java.util.Set;

public class AuthService {

    private final static Set<Entry> entries = Set.of(
            new Entry("user1", "l1", "p1"),
            new Entry("user2", "l2", "p2"),
            new Entry("user3", "l3", "p3")
    );

    public Optional<Entry> findEntryByCredentials(String login, String password) {
        return entries.stream()
                .filter(e -> e.getLogin().equals(login) && e.getPassword().equals(password))
                .findFirst();
    }

    public static class Entry {
        private String name;
        private String login;
        private String password;

        public Entry(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

}
