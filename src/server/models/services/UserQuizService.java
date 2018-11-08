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
                    "SELECT UserId, Score, ScorePercentage, DateCompleted FROM UserQuiz"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new UserQuiz(results.getInt("UserId"), results.getInt("Score"), results.getFloat("ScorePercentage"), results.getInt("DateCompleted")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'UserQuiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static UserQuiz selectById(int id) {
        UserQuiz result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT UserId, Score, ScorePercentage, DateCompleted FROM UserQuiz WHERE UserId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new UserQuiz(results.getInt("UserId"), results.getInt("Score"), results.getFloat("ScorePercentage"), results.getInt("DateCompleted"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'UserQuiz' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(UserQuiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO UserQuiz (UserId, Score, ScorePercentage, DateCompleted) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getUserId());
            statement.setInt(2, itemToSave.getScore());
            statement.setFloat(3, itemToSave.getScorePercentage());
            statement.setInt(4, itemToSave.getDateCompleted());






            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'UserQuiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(UserQuiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE UserQuiz SET Score = ?, ScorePercentage = ?, DateCompleted = ? WHERE UserId = ?"
            );
            statement.setInt(1, itemToSave.getScore());
            statement.setFloat(2, itemToSave.getScorePercentage());
            statement.setInt(3, itemToSave.getDateCompleted());






            statement.setInt(4, itemToSave.getUserId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'UserQuiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM UserQuiz WHERE UserId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'UserQuiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}
