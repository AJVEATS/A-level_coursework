package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Topic {
    private String topic;
    private String topicDescription;

    public Topic(String topic, String topicDescription) {
        this.topic = topic;
        this.topicDescription = topicDescription;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public static ArrayList<Topic> topics = new ArrayList<>();



    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("topic", getTopic());
        j.put("topicDescription", getTopicDescription());








        return j;
    }
}