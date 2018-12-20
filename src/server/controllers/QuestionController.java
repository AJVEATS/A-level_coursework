package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Questions;
import server.models.services.QuestionsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("question/")

public class QuestionController {

    @GET
    @Path("ByTopic")
    @Produces(MediaType.APPLICATION_JSON)
    public String questionsByTopic(@QueryParam("topic")String topic) {

        Logger.log("/questions/ByTopic - Getting list of questions by topic from the database");
        String status = QuestionsService.selectByTopic(Questions.questionss, topic);

        if ( status.equals("OK")) {
            JSONArray questionsJSONArray = new JSONArray();
            for (Questions q : Questions.questionss) {
                questionsJSONArray.add(q.toJSON());
            }
            Logger.log(questionsJSONArray.toString());
            return questionsJSONArray.toString();
        } else {
            JSONObject response = new JSONObject();
            response.put ("Error: ", status);
            return response.toString();
        }
    }
}
