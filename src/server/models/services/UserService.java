package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.User;
import javax.ws.rs.core.Cookie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class UserService {
    public static String selectAllInto(List<User> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, UserPassword, SessionToken FROM USERS"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new User(results.getInt("UserId"), results.getString("UserName"), results.getString("UserPassword"), results.getInt("SessionToken")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'USERS' table: " + resultsException.getMessage();
            Logger.log(error);
            Logger.log(null);
            return error;
        }
        return "OK";
    }
    public static User selectById(int id) {
        User result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, UserPassword, SessionToken FROM USERS WHERE UserId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new User(results.getInt("UserId"), results.getString("UserName"), results.getString("UserPassword"), results.getInt("SessionToken"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'USERS' table: " + resultsException.getMessage();
            Logger.log(error);
        }
        return result;
    }
    public static String insert(User itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO USERS (UserId, UserName, UserPassword, SessionToken) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getUserId());
            statement.setString(2, itemToSave.getUserName());
            statement.setString(3, itemToSave.getUserPassword());
            statement.setInt(4, itemToSave.getSessionToken());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'USERS' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String update(User itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE USERS SET UserName = ?, UserPassword = ?, SessionToken = ? WHERE UserId = ?"
            );
            statement.setString(1, itemToSave.getUserName());
            statement.setString(2, itemToSave.getUserPassword());
            statement.setInt(3, itemToSave.getSessionToken());
            statement.setInt(4, itemToSave.getUserId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'USERS' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM USERS WHERE UserId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'USERS' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String validateSessionCookie(Cookie sessionCookie) {
        return null;
    }
}