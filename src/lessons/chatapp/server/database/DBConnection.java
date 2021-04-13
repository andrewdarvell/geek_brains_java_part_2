package lessons.chatapp.server.database;

import lessons.chatapp.server.exeptions.ServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getSqliteDBConnection(){
        try{
            return DriverManager.getConnection("jdbc:sqlite:SQLiteDataBase/chat.db");
        } catch (SQLException e) {
            throw new ServerException("Cannot connect to database", e);
        }
    }
}
