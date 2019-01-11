package server.models;

import org.json.simple.JSONObject;
import java.util.ArrayList;

public class Quiz {

    private int quizId;
    private String quizDescription;
    private String dateCreated;
    private String topic;

    public Quiz(int quizId, String quizDescription, String dateCreated, String topic) {

        this.quizId = quizId;
        this.quizDescription = quizDescription;
        this.dateCreated = dateCreated;
        this.topic = topic;

    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
        j.put("quizDescription", getQuizDescription());
        j.put("dateCreated", getDateCreated());
        j.put("topic", getTopic());
        return j;

    }

}