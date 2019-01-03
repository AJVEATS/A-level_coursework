package server.models.services;    // Imports the services package from the models folder in the server folder.
import server.Logger;    // Imports from the Logger file in the server folder.
import server.DatabaseConnection;    // Imports from the DatabaseConnection file in the server folder.
import server.models.QuizQuestion;    // Imports the QuizQuestion file from the server folder.

import java.sql.PreparedStatement;    // Imports prepared statement from sql in the java folder.
import java.sql.ResultSet;    // Imports resultSet from sql in the java folder.
import java.sql.SQLException;    // Imports SQLException from sql in the java folder.
import java.util.List;   // Imports List from util in the java folder.

public class QuizQuestionService {    // Creating a new class called QuizQuestionService.


    public static String selectAllByQuizId(List<QuizQuestion> targetList, int quizId) {   // Imports the QuizQuestion list and QuizId for the class.
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(    // The server imports the prepared statement to interact with the database.
                    "SELECT QuizId, QuestionId FROM QuizQuestions where QuizId = ?"    // A prepared statement for teh website to interact with the database.
            );
            if (statement != null) {    // Checks if the statement for the database is something and is not null
                statement.setInt(1, quizId);    // The statement sets its Id to one in the quizID column
                ResultSet results = statement.executeQuery();    // ResultSet results is now equal to teh statement in executeQuery() function.
                if (results != null) {    // Checks if the result from the database is not null.
                    while (results.next()) {
                        targetList.add(new QuizQuestion(results.getInt("QuizId"), results.getInt("QuestionId")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'QuizQuestions' table: " + resultsException.getMessage();    // Creates a message for the error message.
            Logger.log(error);    // If the server get's an error it will log in the browser console that there was a database error.
            return error;    // The server will return the error.
        }
        return "OK";    // If it is working properly it will return "OK".
    }





}