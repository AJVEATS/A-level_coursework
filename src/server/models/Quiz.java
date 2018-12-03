package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Quiz {
    private int topicId;
    private int questionId;
    private String answerCorrect;
    private int score;
    public Quiz(int topicId, int questionId, String answerCorrect, int score) {
        this.topicId = topicId;
        this.questionId = questionId;
        this.answerCorrect = answerCorrect;
        this.score = score;
    }
    public int getTopicId() {
        return topicId;
    }
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getAnswerCorrect() {
        return answerCorrect;
    }
    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public static ArrayList<Quiz> quizzes = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (Quiz q: quizzes) {
            if (q.getTopicId() > id) {
                id = q.getTopicId();
            }
        }
        return id + 1;
    }
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("topicId", getTopicId());
        j.put("questionId", getQuestionId());
        j.put("answerCorrect", getAnswerCorrect());
        j.put("score", getScore());
        return j;
    }
}