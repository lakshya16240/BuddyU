package com.example.skwow.mcproject;

public class Message {

    private String message;
    private String userName;
    private String userId;

    public Message() {
    }

    public Message(String message, String userName, String userId) {
        this.message = message;
        this.userName = userName;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
