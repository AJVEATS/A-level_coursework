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
                    "SELECT QuizID, QuizDescription, DateCreated, Topic FROM Quizes"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Quiz(results.getInt("QuizID"), results.getString("QuizDescription"), results.getString("DateCreated"), results.getString("Topic")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Quizes' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Quiz selectById(int id) {
        Quiz result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizID, QuizDescription, DateCreated, Topic FROM Quizes WHERE QuizID = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Quiz(results.getInt("QuizID"), results.getString("QuizDescription"), results.getString("DateCreated"), results.getString("Topic"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Quizes' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Quiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Quizes (QuizID, QuizDescription, DateCreated, Topic) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getQuizId());
            statement.setString(2, itemToSave.getQuizDescription());
            statement.setString(3, itemToSave.getDateCreated());
            statement.setString(4, itemToSave.getTopic());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Quizes' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Quiz itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Quizes SET QuizDescription = ?, DateCreated = ?, Topic = ? WHERE QuizID = ?"
            );
            statement.setString(1, itemToSave.getQuizDescription());
            statement.setString(2, itemToSave.getDateCreated());
            statement.setString(3, itemToSave.getTopic());
            statement.setInt(4, itemToSave.getQuizId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Quizes' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Quizes WHERE QuizID = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Quizes' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}