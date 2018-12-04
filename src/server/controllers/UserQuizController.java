package server.controllers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.UserQuiz;
import server.models.services.UserQuizService;
import sun.java2d.cmm.Profile;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
@Path("userQuizzes/")
public class UserQuizController {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listTopicsCompleted(){
        Logger.log("/profile/list - Getting all profiles from the database");
        String status = UserQuizService.selectAllInto(UserQuiz.userquizs);
        if (status.equals("OK")){
            JSONArray profileList = new JSONArray();
            for(UserQuiz c: UserQuiz.userquizs){
                JSONObject jc = c.toJSON();
                profileList.add(jc);
            }
            return profileList.toString();
        }else {
            System.out.println("An error occurred!" + status);
            return "";
        }
    }
}