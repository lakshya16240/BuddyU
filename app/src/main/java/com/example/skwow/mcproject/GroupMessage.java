package com.example.skwow.mcproject;

public class GroupMessage {

    private String message;
    private boolean isSelfMessage;
    private User user;

    public GroupMessage(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelfMessage() {
        return isSelfMessage;
    }

    public void setSelfMessage(boolean selfMessage) {
        isSelfMessage = selfMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
