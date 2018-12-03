package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.Quiz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class QuizService {
    public static String selectAllInto(List<Quiz> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT TopicId, QuestionId, AnswerCorrect, Score FROM QUIZ"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Quiz(results.getInt("TopicId"), results.getInt("QuestionId"), results.getString("AnswerCorrect"), results.getInt("Score")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'QUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }
    public static Quiz selectById(int id) {
        Quiz result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT TopicId, QuestionId, AnswerCorrect, Score FROM QUIZ WHERE TopicId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Quiz(results.getInt("TopicId"), results.getInt("QuestionId"), results.getString("AnswerCorrect"), results.getInt("Score"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'QUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
        }
        return result;
    }
    public static String insert(Quiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO QUIZ (TopicId, QuestionId, AnswerCorrect, Score) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getTopicId());
            statement.setInt(2, itemToSave.getQuestionId());
            statement.setString(3, itemToSave.getAnswerCorrect());
            statement.setInt(4, itemToSave.getScore());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'QUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String update(Quiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE QUIZ SET QuestionId = ?, AnswerCorrect = ?, Score = ? WHERE TopicId = ?"
            );
            statement.setInt(1, itemToSave.getQuestionId());
            statement.setString(2, itemToSave.getAnswerCorrect());
            statement.setInt(3, itemToSave.getScore());
            statement.setInt(4, itemToSave.getTopicId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'QUIZ' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }
    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM QUIZ WHERE TopicId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'QUIZ' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }
}