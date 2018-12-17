package server.models;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class UserQuiz {
    private int userId;
    private String userName;
    private int score;
    private int topicCompleted;

    public UserQuiz(int userId, String userName, int score, int topicCompleted) {
        this.userId = userId;
        this.userName = userName;
        this.score = score;
        this.topicCompleted = topicCompleted;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getTopicCompleted() {
        return topicCompleted;
    }
    public void setTopicCompleted(int topicCompleted) {
        this.topicCompleted = topicCompleted;
    }
    public static ArrayList<UserQuiz> userquizs = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (UserQuiz u: userquizs) {
            if (u.getUserId() > id) {
                id = u.getUserId();
            }
        }
        return id + 1;
    }
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("userId", getUserId());
        j.put("userName", getUserName());
        j.put("score", getScore());
        j.put("topicCompleted", getTopicCompleted());
        return j;
    }
}