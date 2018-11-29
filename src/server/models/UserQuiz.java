package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class UserQuiz {
    private int userId;
    private int score;
    private float scorePercentage;
    private int dateCompleted;
    public UserQuiz(int userId, int score, float scorePercentage, int dateCompleted) {
        this.userId = userId;
        this.score = score;
        this.scorePercentage = scorePercentage;
        this.dateCompleted = dateCompleted;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public float getScorePercentage() {
        return scorePercentage;
    }
    public void setScorePercentage(float scorePercentage) {
        this.scorePercentage = scorePercentage;
    }
    public int getDateCompleted() {
        return dateCompleted;
    }
    public void setDateCompleted(int dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    public static ArrayList<UserQuiz> userquizzes = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (UserQuiz u: userquizzes) {
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
        j.put("score", getScore());
        j.put("scorePercentage", getScorePercentage());
        j.put("dateCompleted", getDateCompleted());
        return j;
    }
}
