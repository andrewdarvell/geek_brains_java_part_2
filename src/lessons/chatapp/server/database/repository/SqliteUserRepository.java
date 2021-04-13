package lessons.chatapp.server.database.repository;

import lessons.chatapp.server.exeptions.ChatUserDuplicateException;
import lessons.chatapp.server.exeptions.ServerException;
import lessons.chatapp.server.database.DBConnection;
import lessons.chatapp.server.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteUserRepository implements UserRepository {


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DBConnection.getSqliteDBConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT *\n" +
                    "FROM users");
            while (rs.next()) {
                users.add(new User(
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            throw new ServerException("Error while getting user from DB", e);
        }
        return users;
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        Optional<User> result = Optional.empty();
        try {
            Connection connection = DBConnection.getSqliteDBConnection();

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM users\n" +
                            "WHERE name = ?"
            );
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                result = Optional.of(new User(
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new ServerException("Error while getting user from DB", e);
        }
        return result;
    }

    @Override
    public Optional<User> getUserByCredentials(String login, String password) {
        Optional<User> result = Optional.empty();
        try {
            Connection connection = DBConnection.getSqliteDBConnection();

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM users\n" +
                            "WHERE login = ? AND password = ?"
            );
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                result = Optional.of(new User(
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new ServerException("Error while getting user from DB", e);
        }
        return result;
    }

    @Override
    public void changeNickName(String oldNick, String newNick) throws ChatUserDuplicateException {
        Optional<User> user = getUserByName(oldNick);
        if (user.isPresent()) {
            Optional<User> checkNewNickUser = getUserByName(newNick);
            if (checkNewNickUser.isEmpty()) {
                try {
                    Connection connection = DBConnection.getSqliteDBConnection();
                    connection.setAutoCommit(false);
                    PreparedStatement ps = connection.prepareStatement("UPDATE users\n" +
                            "SET name = ?\n" +
                            "WHERE name = ?");
                    ps.setString(1, newNick);
                    ps.setString(2, oldNick);

                    ps.execute();
                    connection.commit();
                    ps.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new ServerException("Error while changing user name from DB", e);
                }
            } else {
                throw new ChatUserDuplicateException(String.format("User %s already exist", newNick));
            }
        }
    }
}
