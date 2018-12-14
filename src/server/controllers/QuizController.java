package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Quiz;
import server.models.services.QuizService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("quiz/")

public class QuizController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String quizList() {

        Logger.log("/quiz/list - Getting list of quizes from database");

        String status = QuizService.selectAllInto(Quiz.quizes);

        if (status.equals("OK")) {
            JSONArray quizJSONArray = new JSONArray();
            for (Quiz q : Quiz.quizes) {
                quizJSONArray.add(q.toJSON());
            }
            Logger.log(quizJSONArray.toString());
            return quizJSONArray.toString();
        } else {
            JSONObject response = new JSONObject();
            response.put("Error: ", status);
            return response.toString();
        }

    }


    @GET
    @Path("questions")
    @Produces(MediaType.APPLICATION_JSON)
    public String listQuestions() {
        Logger.log("/quiz/questions - Getting all questions from the database");
        String status = QuizService.selectAllInto(Quiz.quizes);
        if (status.equals("OK")) {
            JSONArray questionsList = new JSONArray();
            for (Quiz c : Quiz.quizes) {
                JSONObject jc = c.toJSON();
                questionsList.add(jc);
            }
            return questionsList.toString();
        } else {
            System.out.println("An error occurred!" + status);
            return "";
        }
    }

    @GET
    @Path("answers")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAnswers() {
        Logger.log("/quiz/questions - Getting all the answers from the database");
        String status = QuizService.selectAllInto(Quiz.quizes);
        if (status.equals("OK")) {
        } else {
            System.out.println("An error has occurred" + status);
            return "";
        }
        return status;
    }
}


