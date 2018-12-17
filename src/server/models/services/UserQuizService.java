package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.UserQuiz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class UserQuizService {
    public static String selectAllInto(List<UserQuiz> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, UserName, Score, TopicCompleted FROM USERQUIZ"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new UserQuiz(results.getInt("UserId"), results.getString("UserName"), results.getInt("Score"), results.getInt("TopicCompleted")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'USERQUIZ' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }
    public static String insert(UserQuiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO USERQUIZ (UserId, UserName, Score, TopicCompleted) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getUserId());
            statement.setString(2, itemToSave.getUserName());
            statement.setInt(3, itemToSave.getScore());
            statement.setInt(4, itemToSave.getTopicCompleted());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'USERQUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String update(UserQuiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE USERQUIZ SET UserName = ?, Score = ?, TopicCompleted = ? WHERE UserId = ?"
            );
            statement.setString(1, itemToSave.getUserName());
            statement.setInt(2, itemToSave.getScore());
            statement.setInt(3, itemToSave.getTopicCompleted());
            statement.setInt(4, itemToSave.getUserId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'USERQUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM USERQUIZ WHERE UserId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'USERQUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
}