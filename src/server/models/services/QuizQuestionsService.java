package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.QuizQuestions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuizQuestionsService {

    public static String selectAllInto(List<QuizQuestions> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizId, QuestionId FROM QuizQuestions"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new QuizQuestions(results.getInt("QuizId"), results.getString("QuestionId")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'QuizQuestions' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static QuizQuestions selectById(int id) {
        QuizQuestions result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizId, QuestionId FROM QuizQuestions WHERE QuizId = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new QuizQuestions(results.getInt("QuizId"), results.getString("QuestionId"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'QuizQuestions' table: " + resultsException.getMessage();
            Logger.log(error);
        }
        return result;
    }

    public static String insert(QuizQuestions itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO QuizQuestions (QuizId, QuestionId) VALUES (?, ?)"
            );
            statement.setInt(1, itemToSave.getQuizId());
            statement.setString(2, itemToSave.getQuestionId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'QuizQuestions' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

    public static String update(QuizQuestions itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE QuizQuestions SET QuestionId = ? WHERE QuizId = ?"
            );
            statement.setString(1, itemToSave.getQuestionId());
            statement.setInt(2, itemToSave.getQuizId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'QuizQuestions' table: " + resultsException.getMessage();
            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM QuizQuestions WHERE QuizId = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'QuizQuestions' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}