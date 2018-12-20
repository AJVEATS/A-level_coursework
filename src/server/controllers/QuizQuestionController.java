package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Question;
import server.models.QuizQuestion;
import server.models.services.QuestionService;
import server.models.services.QuizQuestionService;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("quizquestion/")

public class QuizQuestionController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public String questionsByTopic(@QueryParam("quizId") int quizId) {

        Logger.log("/quizquestion/list - Getting list of questions for quizID " + quizId);
        String status = QuizQuestionService.selectAllByQuizId(QuizQuestion.quizquestions, quizId);

        if (!status.equals("OK")) {
            JSONObject response = new JSONObject();
            response.put("Error: ", status);
            return response.toString();
        }

        //now we have the quizQuestions records back we need to get the actual question referred to by the questionID
        status = QuestionService.selectAllInto(Question.questions);
        if (!status.equals("OK")) {
            JSONObject response = new JSONObject();
            response.put("Error: ", status);
            return response.toString();
        }

        //now we have two arrayLists one with quizID and QuestionId and another with questions including QuestionId
        //let's make a JSON array of objects

        JSONArray newJSONArray = new JSONArray();

        for (QuizQuestion qq : QuizQuestion.quizquestions) {
            for (Question q : Question.questions) {
                if(qq.getQuestionId()==q.getQuestionId()) {
                    JSONObject j = new JSONObject();
                    j.put("questionID", q.getQuestionId());
                    j.put("question", q.getQuestion());
                    j.put("answerA", q.getAnswerA());
                    j.put("answerB", q.getAnswerB());
                    j.put("answerC", q.getAnswerC());
                    j.put("answerD", q.getAnswerD());
                    j.put("answerCorrect", q.getAnswerCorrect());

                    newJSONArray.add(j);
                }
            }
        }


        Logger.log(newJSONArray.toString());
        return newJSONArray.toString();


    }


}
