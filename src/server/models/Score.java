package server.models;

import org.json.simple.JSONObject;
import java.util.ArrayList;

public class Score {

    private int scoreId;
    private int userId;
    private double quizId;
    private int score;

    public Score(int scoreId, int userId, double quizId, int score) {

        this.scoreId = scoreId;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;

    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getQuizId() {
        return quizId;
    }

    public void setQuizId(double quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static ArrayList<Score> scoress = new ArrayList<>();

    public static int nextId() {

        int id = 0;
        for (Score s: scoress) {
            if (s.getScoreId() > id) {
                id = s.getScoreId();
            }
        }
        return id + 1;

    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("scoreId", getScoreId());
        j.put("userId", getUserId());
        j.put("quizId", getQuizId());
        j.put("score", getScore());
        return j;

    }

}