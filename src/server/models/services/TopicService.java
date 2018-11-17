package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.Topic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class TopicService {
    public static String selectAllInto(List<Topic> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT TopicId, QuestionId, AnswerCorrect FROM Topic"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Topic(results.getInt("TopicId"), results.getInt("QuestionId"), results.getInt("AnswerCorrect")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Topic' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }
    public static Topic selectById(int id) {
        Topic result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT TopicId, QuestionId, AnswerCorrect FROM Topic WHERE TopicId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Topic(results.getInt("TopicId"), results.getInt("QuestionId"), results.getInt("AnswerCorrect"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Topic' table: " + resultsException.getMessage();
            Logger.log(error);
        }
        return result;
    }
    public static String insert(Topic itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Topic (TopicId, QuestionId, AnswerCorrect) VALUES (?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getTopicId());
            statement.setInt(2, itemToSave.getQuestionId());
            statement.setInt(3, itemToSave.getAnswerCorrect());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Topic' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String update(Topic itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Topic SET QuestionId = ?, AnswerCorrect = ? WHERE TopicId = ?"
            );
            statement.setInt(1, itemToSave.getQuestionId());
            statement.setInt(2, itemToSave.getAnswerCorrect());
            statement.setInt(3, itemToSave.getTopicId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Topic' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Topic WHERE TopicId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Topic' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

}
