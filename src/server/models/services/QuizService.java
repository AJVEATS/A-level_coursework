package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.Quiz;
import server.models.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuizService {

    public static String selectAllInto(List<Quiz> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizId, QuizDescription, DateCreated, Topic FROM Quizzes"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Quiz(results.getInt("QuizId"), results.getString("QuizDescription"), results.getString("DateCreated"), results.getString("Topic")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Quizzes' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }


    public static String selectByTopic(List<Quiz> targetList, String topic) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizId, QuizDescription, DateCreated, Topic FROM Quizzes WHERE Topic = ? "
            );
            if (statement != null) {
                statement.setString(1, topic);
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Quiz(results.getInt("QuizId"), results.getString("QuizDescription"), results.getString("DateCreated"), results.getString("Topic")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select quizzes by topic from DB: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }


   

}