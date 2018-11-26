package com.example.skwow.mcproject;

public class Notification
{
    private String sentBy;
    private String topic;
    private String msg;

    public Notification(String sentBy, String topic, String msg) {
        this.sentBy = sentBy;
        this.topic = topic;
        this.msg = msg;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
