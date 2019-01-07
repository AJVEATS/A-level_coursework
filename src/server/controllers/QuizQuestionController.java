package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;    // Imports from the Logger file in the server folder.
import server.models.Question;    // Imports the Question file from the server folder.
import server.models.QuizQuestion;    // Imports the QuizQuestion file from the server folder.
import server.models.services.QuestionService;    // Imports the QuizService file from the service file in the server folder.
import server.models.services.QuizQuestionService;    // Imports the QuizQuestionService file from the service file in the server folder.

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("quizquestion/")    // The API path for the quizQuestion file

public class QuizQuestionController {

    @GET
    @Path("list")    // The second part of the API path for the list function in the quizQuestion file.
    @Produces(MediaType.APPLICATION_JSON)

    public String questionsByTopic(@QueryParam("quizId") int quizId) {    // New function declared called questionByTopic using the variable quizId and quizId from queryPara.

        Logger.log("/quizquestion/list - Getting list of questions for quizID " + quizId);    // The console will log that it is reached and is getting all of teh questions for quizID
        String status = QuizQuestionService.selectAllByQuizId(QuizQuestion.quizquestions, quizId);    // Status is declared as a string.

        if (!status.equals("OK")) {    // If the status is ok it will run the code inside if the if statement.
            JSONObject response = new JSONObject();
            response.put("Error: ", status);    // Declares the response as an error and add the current status.
            return response.toString();    // Returns the response that was declared above in the format of a string.
        }

        //  now we have the quizQuestions records back we need to get the actual question referred to by the questionID
        status = QuestionService.selectAllInto(Question.questions);    // Status is declared.
        if (!status.equals("OK")) {    // If the status is not equal to ok it will run teh code inside of the if statement.
            JSONObject response = new JSONObject();    // A new JSONObject is declared
            response.put("Error: ", status);    // Declares the response as an error and add the current status.
            return response.toString();    // Returns the response that was declared above in the format of a string.
        }

        //now we have two arrayLists one with quizID and QuestionId and another with questions including QuestionId
        //let's make a JSON array of objects

        JSONArray newJSONArray = new JSONArray();    // A new JSONArray is declared

        for (QuizQuestion qq : QuizQuestion.quizquestions) {
            for (Question q : Question.questions) {
                if(qq.getQuestionId()==q.getQuestionId()) {
                    JSONObject j = new JSONObject();
                    j.put("questionID", q.getQuestionId());    // Adds the rows questionId to the JSONArray from the database.
                    j.put("question", q.getQuestion());   // Adds the rows question to the JSONArray from the database.
                    j.put("answerA", q.getAnswerA());    // Adds the rows answerA to the JSONArray from the database.
                    j.put("answerB", q.getAnswerB());    // Adds the rows answerB to the JSONArray from the database.
                    j.put("answerC", q.getAnswerC());   // Adds the rows answerC to the JSONArray from the database.
                    j.put("answerD", q.getAnswerD());    // Adds the rows answerD to the JSONArray from the database.
                    j.put("answerCorrect", q.getAnswerCorrect());    // Adds the rows answerCorrect to the JSONArray from the database.

                    newJSONArray.add(j);    // It goes onto the next row of the database.
                }
            }
        }


        Logger.log(newJSONArray.toString());
        return newJSONArray.toString();


    }


}
