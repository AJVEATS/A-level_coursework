package server.controllers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Quiz;
import server.models.services.QuizService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("quiz/")

public class QuizController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public String quizByTopic(@QueryParam("topic") String topic) {
        Logger.log("/quiz/list - Getting quiz list by topic " + topic + "from database");
        String status = QuizService.selectByTopic(Quiz.quizzes, topic);
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
}


