package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class QuizQuestions {
    private int quizId;
    private String questionId;

    public QuizQuestions(int quizId, String questionId) {
        this.quizId = quizId;
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public static ArrayList<QuizQuestions> quizquestionss = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (QuizQuestions q: quizquestionss) {
            if (q.getQuizId() > id) {
                id = q.getQuizId();
            }
        }
        return id + 1;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("quizId", getQuizId());
        j.put("questionId", getQuestionId());








        return j;
    }
}