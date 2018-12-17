package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Topic;
import server.models.services.TopicService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("topic/")

public class TopicController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String quizList() {

        Logger.log("/topic/list - Getting list of topics from database");

        String status = TopicService.selectAllInto(Topic.topics);


        if (status.equals("OK")) {
            JSONArray topicJSONArray = new JSONArray();
            for (Topic t : Topic.topics) {
                topicJSONArray.add(t.toJSON());
            }
            Logger.log(topicJSONArray.toString());
            return topicJSONArray.toString();
        } else {
            JSONObject response = new JSONObject();
            response.put("Error: ", status);
            return response.toString();
        }

    }

}


