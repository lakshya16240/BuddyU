package com.example.skwow.mcproject;

import java.io.Serializable;

public class Group implements Serializable {

    private String eventTitle, eventVenue, eventTimings;

    public Group(String eventTitle, String eventVenue, String eventTimings) {
        this.eventTitle = eventTitle;
        this.eventVenue = eventVenue;
        this.eventTimings = eventTimings;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventTimings() {
        return eventTimings;
    }

    public void setEventTimings(String eventTimings) {
        this.eventTimings = eventTimings;
    }
}
