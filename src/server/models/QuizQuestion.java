package server.models;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class QuizQuestion {
    private int quizId;
    private int questionId;

    public QuizQuestion(int quizId, int questionId) {
        this.quizId = quizId;
        this.questionId = questionId;
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

    public static ArrayList<QuizQuestion> quizquestions = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (QuizQuestion q: quizquestions) {
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