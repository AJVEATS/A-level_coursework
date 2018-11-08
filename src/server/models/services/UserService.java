package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static String selectAllInto(List<User> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, Password FROM User"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new User(results.getInt("UserId"), results.getString("UserName"), results.getString("Password")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'User' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static User selectById(int id) {
        User result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, Password FROM User WHERE UserId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new User(results.getInt("UserId"), results.getString("UserName"), results.getString("Password"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'User' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(User itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO User (UserId, UserName, Password) VALUES (?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getUserId());
            statement.setString(2, itemToSave.getUserName());
            statement.setString(3, itemToSave.getPassword());







            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'User' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(User itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE User SET UserName = ?, Password = ? WHERE UserId = ?"
            );
            statement.setString(1, itemToSave.getUserName());
            statement.setString(2, itemToSave.getPassword());







            statement.setInt(3, itemToSave.getUserId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'User' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM User WHERE UserId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'User' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}
