package server.models.services;
import server.Logger;
import server.DatabaseConnection;
import server.models.QuizQuestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuizQuestionService {


    public static String selectAllByQuizId(List<QuizQuestion> targetList, int quizId) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT QuizId, QuestionId FROM QuizQuestions where QuizId = ?"
            );
            if (statement != null) {
                statement.setInt(1, quizId);
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new QuizQuestion(results.getInt("QuizId"), results.getInt("QuestionId")));
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





}