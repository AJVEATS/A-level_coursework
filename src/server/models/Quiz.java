package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Quiz {
    private int quizId;
    private int questionId;
    private int topicId;
    private String answerCorrect;
    public Quiz(int quizId, int questionId, int topicId, String answerCorrect) {
        this.quizId = quizId;
        this.questionId = questionId;
        this.topicId = topicId;
        this.answerCorrect = answerCorrect;
    }
    public int getQuizId() {
        return quizId;
    }
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public int getTopicId() {
        return topicId;
    }
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    public String getAnswerCorrect() {
        return answerCorrect;
    }
    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
    public static ArrayList<Quiz> quizzes = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (Quiz q: quizzes) {
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
        j.put("topicId", getTopicId());
        j.put("answerCorrect", getAnswerCorrect());
        return j;
    }
}
