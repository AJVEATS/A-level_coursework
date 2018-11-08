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
                    "SELECT QuizId, QuestionId, TopicId, AnswerCorrect FROM Quiz"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Quiz(results.getInt("QuizId"), results.getInt("QuestionId"), results.getInt("TopicId"), results.getString("AnswerCorrect")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Quiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Quiz selectById(int id) {
        Quiz result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizId, QuestionId, TopicId, AnswerCorrect FROM Quiz WHERE QuizId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Quiz(results.getInt("QuizId"), results.getInt("QuestionId"), results.getInt("TopicId"), results.getString("AnswerCorrect"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Quiz' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Quiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Quiz (QuizId, QuestionId, TopicId, AnswerCorrect) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getQuizId());
            statement.setInt(2, itemToSave.getQuestionId());
            statement.setInt(3, itemToSave.getTopicId());
            statement.setString(4, itemToSave.getAnswerCorrect());






            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Quiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Quiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Quiz SET QuestionId = ?, TopicId = ?, AnswerCorrect = ? WHERE QuizId = ?"
            );
            statement.setInt(1, itemToSave.getQuestionId());
            statement.setInt(2, itemToSave.getTopicId());
            statement.setString(3, itemToSave.getAnswerCorrect());






            statement.setInt(4, itemToSave.getQuizId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Quiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Quiz WHERE QuizId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Quiz' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}