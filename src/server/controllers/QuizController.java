package server.controllers;
import server.Logger;
import server.models.User;
import server.models.services.QuizService;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.UUID;
@Path("quiz/")
public class QuizController {
    @POST
    @Path("quiz")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String AnswerCheck(){
        String userAnswer = "a";
        String answerCorrect = "A";
        int userScore = 0;
        float userScorePercentage = 0/10;
        if (userAnswer.toUpperCase() == answerCorrect){
            userScore = userScore + 1;
            userScorePercentage = userScorePercentage + 1;

        }
        return null;
    }
}
