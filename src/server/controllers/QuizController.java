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
    @Path("byTopic")
    @Produces(MediaType.APPLICATION_JSON)
    public String quizByTopic(Sess xxxxxxx  ) {

        Logger.log("/quiz/byTopic - Getting list of quizzes by topic from database");

        String status = QuizService.selectAllInto(Quiz.quizzes, topic);

        if (status.equals("OK")) {
            JSONArray quizJSONArray = new JSONArray();
            for (Quiz q : Quiz.quizzes) {
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
        String status = QuizService.selectAllInto(Quiz.quizzes);
        if (status.equals("OK")) {
            JSONArray questionsList = new JSONArray();
            for (Quiz c : Quiz.quizzes) {
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
        String status = QuizService.selectAllInto(Quiz.quizzes);
        if (status.equals("OK")) {
        } else {
            System.out.println("An error has occurred" + status);
            return "";
        }
        return status;
    }
}


