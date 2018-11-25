package server.controllers;
import server.models.Quiz;
import server.models.services.QuizService;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import static server.Logger.log;

@Path("quiz/")
public class QuizController {
    String AnswerCorrect = "A";
    @GET
    @Path("answer")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Character getAnswer(@FormParam("answerCorrect") String AnswerCorrect){
        log("/quiz/answer - Get" + AnswerCorrect);
        QuizService.selectAllInto(Quiz.quizs);

        return null;
    }
    public int userScore = 0;
    public String AnswerCheck(){
        String userAnswer = "a";


        if (userAnswer.toUpperCase() == AnswerCorrect){
            userScore = userScore + 1;
        }
        return null;
    }
    public void GenerateUserScorePecentage(){
        int userScorePercentage = ( userScore * 10);

    }
    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public void getAnswerCorrect(@CookieParam("sessionToken")Cookie sessionCookie){
        Character AnswerCorrect = QuizService.validateSessionCookie(sessionCookie);
        Character UserAnswer = null;
        if (AnswerCorrect == UserAnswer){

        }
    }

}
