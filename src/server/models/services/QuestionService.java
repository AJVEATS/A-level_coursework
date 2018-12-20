package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionService {

    public static String selectAllInto(List<Question> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuestionId, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, AnswerCorrect FROM Questions"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Question(results.getInt("QuestionId"), results.getString("Topic"), results.getString("Question"), results.getString("AnswerA"), results.getString("AnswerB"), results.getString("AnswerC"), results.getString("AnswerD"), results.getString("AnswerCorrect")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Questions' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Question selectById(int id) {
        Question result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuestionId, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, AnswerCorrect FROM Questions WHERE QuestionId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Question(results.getInt("QuestionId"), results.getString("Topic"), results.getString("Question"), results.getString("AnswerA"), results.getString("AnswerB"), results.getString("AnswerC"), results.getString("AnswerD"), results.getString("AnswerCorrect"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Questions' table: " + resultsException.getMessage();
            Logger.log(error);
        }
        return result;
    }

    public static String insert(Question itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Questions (QuestionId, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, AnswerCorrect) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getQuestionId());
            statement.setString(2, itemToSave.getTopic());
            statement.setString(3, itemToSave.getQuestion());
            statement.setString(4, itemToSave.getAnswerA());
            statement.setString(5, itemToSave.getAnswerB());
            statement.setString(6, itemToSave.getAnswerC());
            statement.setString(7, itemToSave.getAnswerD());
            statement.setString(8, itemToSave.getAnswerCorrect());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Questions' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

    public static String update(Question itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Questions SET Topic = ?, Question = ?, AnswerA = ?, AnswerB = ?, AnswerC = ?, AnswerD = ?, AnswerCorrect = ? WHERE QuestionId = ?"
            );
            statement.setString(1, itemToSave.getTopic());
            statement.setString(2, itemToSave.getQuestion());
            statement.setString(3, itemToSave.getAnswerA());
            statement.setString(4, itemToSave.getAnswerB());
            statement.setString(5, itemToSave.getAnswerC());
            statement.setString(6, itemToSave.getAnswerD());
            statement.setString(7, itemToSave.getAnswerCorrect());
            statement.setInt(8, itemToSave.getQuestionId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Questions' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Questions WHERE QuestionId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Questions' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

    public static String selectByTopic(List<Question> targetList, String topic) {
        targetList.clear();
        try{
            PreparedStatement statement = DatabaseConnection.newStatement(
                    " SELECT Question, AnswerA, AnswerB, AnswerC, AnswerD FROM Questions WHERE Topic = ?"
            );
            if (statement!= null) {
                statement.setString(1, topic);
                ResultSet results = statement.executeQuery();
                while (results.next()){
                    targetList.add(new Question(results.getInt("QuestionId"), results.getString("Question"), results.getString("AnswerA"), results.getString("AnswerB"), results.getString("AnswerC"), results.getString("AnswerD")));
                }
            }
        } catch ( SQLException resultsException) {
            String error = "Database error  can't select questions by topic from DB: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }
}
