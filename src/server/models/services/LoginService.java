package server.models.services;

import server.Logger;
import server.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginService {
    public static String selectAllInto(List<server.models.LoginService> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Username, Password, SessionToken FROM Admins"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new server.models.LoginService(results.getString("Username"), results.getString("Password"), results.getString("SessionToken")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Admins' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static server.models.LoginService selectById(int id) {
        server.models.LoginService result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Username, Password, SessionToken FROM Admins WHERE Username = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new server.models.LoginService(results.getString("Username"), results.getString("Password"), results.getString("SessionToken"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Admins' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String update(server.models.LoginService itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Admins SET Password = ?, SessionToken = ? WHERE Username = ?"
            );
            statement.setString(1, itemToSave.getPassword());
            statement.setString(2, itemToSave.getSessionToken());

            statement.setString(3, itemToSave.getUsername());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Admins' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}
