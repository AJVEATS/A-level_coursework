package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Topic {
    private int topicId;
    private int questionId;
    private int answerCorrect;
    public Topic(int topicId, int questionId, int answerCorrect) {
        this.topicId = topicId;
        this.questionId = questionId;
        this.answerCorrect = answerCorrect;
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
    public int getAnswerCorrect() {
        return answerCorrect;
    }
    public void setAnswerCorrect(int answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
    public static ArrayList<Topic> topics = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (Topic t: topics) {
            if (t.getTopicId() > id) {
                id = t.getTopicId();
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







        return j;
    }
}
