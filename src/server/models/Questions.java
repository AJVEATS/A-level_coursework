package server.models;
import org.json.simple.JSONObject;
import java.util.ArrayList;
public class Questions {
    private int questionId;
    private int topicId;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String question;
    private String answerCorrect;
    public Questions(int questionId, int topicId, String answerA, String answerB, String answerC, String answerD, String question, String answerCorrect) {
        this.questionId = questionId;
        this.topicId = topicId;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.question = question;
        this.answerCorrect = answerCorrect;
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
    public String getAnswerA() {
        return answerA;
    }
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }
    public String getAnswerD() {
        return answerD;
    }
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswerCorrect() {
        return answerCorrect;
    }
    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
    public static ArrayList<Questions> questionss = new ArrayList<>();
    public static int nextId() {
        int id = 0;
        for (Questions q: questionss) {
            if (q.getQuestionId() > id) {
                id = q.getQuestionId();
            }
        }
        return id + 1;
    }
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("questionId", getQuestionId());
        j.put("topicId", getTopicId());
        j.put("answerA", getAnswerA());
        j.put("answerB", getAnswerB());
        j.put("answerC", getAnswerC());
        j.put("answerD", getAnswerD());
        j.put("question", getQuestion());
        j.put("answerCorrect", getAnswerCorrect());
        return j;
    }
}
