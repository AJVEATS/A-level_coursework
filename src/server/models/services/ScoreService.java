package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScoreService {

    public static String selectAllInto(List<Score> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT ScoreId, UserId, QuizId, ScoreId FROM Score"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Score(results.getInt("ScoreId"), results.getInt("UserId"), results.getDouble("QuizId"), results.getInt("ScoreId")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Score' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Score selectById(int id) {
        Score result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT ScoreId, UserId, QuizId, ScoreId FROM Score WHERE ScoreId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Score(results.getInt("ScoreId"), results.getInt("UserId"), results.getDouble("QuizId"), results.getInt("ScoreId"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Score' table: " + resultsException.getMessage();
            Logger.log(error);
        }
        return result;
    }

    public static String insert(Score itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Score (ScoreId, UserId, QuizId, ScoreId) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getScoreId());
            statement.setInt(2, itemToSave.getUserId());
            statement.setDouble(3, itemToSave.getQuizId());
            statement.setInt(4, itemToSave.getScore());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Score' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

    public static String update(Score itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Score SET UserId = ?, QuizId = ?, ScoreId = ? WHERE ScoreId = ?"
            );
            statement.setInt(1, itemToSave.getUserId());
            statement.setDouble(2, itemToSave.getQuizId());
            statement.setInt(3, itemToSave.getScore());
            statement.setInt(4, itemToSave.getScoreId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Score' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Score WHERE ScoreId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Score' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

}