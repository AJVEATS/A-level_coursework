package server.models;

import org.json.simple.JSONObject;
import java.util.ArrayList;

public class Question {

    private int questionId;
    private String topic;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerCorrect;

    public Question(int questionId, String topic, String question, String answerA, String answerB, String answerC, String answerD, String answerCorrect) {

        this.questionId = questionId;
        this.topic = topic;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerCorrect = answerCorrect;

    }

    public Question(int questionId, String question, String answerA, String answerB, String answerC, String answerD) {

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public static ArrayList<Question> questions = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (Question q: questions) {
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
        j.put("topic", getTopic());
        j.put("question", getQuestion());
        j.put("answerA", getAnswerA());
        j.put("answerB", getAnswerB());
        j.put("answerC", getAnswerC());
        j.put("answerD", getAnswerD());
        j.put("answerCorrect", getAnswerCorrect());
        return j;

    }

}