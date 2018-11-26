package com.example.skwow.mcproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {


    private String username;
    private String email;
    private String UID;
    private ArrayList<String> moviesInterests = new ArrayList<>();
    private ArrayList<String> sportsInterests = new ArrayList<>();
    private ArrayList<MyEvent> events = new ArrayList<>();

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    private ArrayList<Notification> notifications = new ArrayList<>();

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification noti) {
        this.notifications.add(noti);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDatabaseReference = database.getReference("users");
        userDatabaseReference.child(UID).child("notifications").setValue(notifications);
    }

    @Exclude
    public static User currentUser = null;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String _UID, ArrayList<String> _moviesInterests, ArrayList<String> _sportsInterests, ArrayList<MyEvent> _events)
    {
        this.username = username;
        this.email = email;
        this.UID = _UID;
        moviesInterests = _moviesInterests;
        sportsInterests = _sportsInterests;
        events = _events;
    }


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getMoviesInterests() {
        return moviesInterests;
    }

    public ArrayList<String> getSportsInterests() {
        return sportsInterests;
    }

    public void setMoviesInterests(ArrayList<String> moviesInterests) {
        this.moviesInterests = moviesInterests;
    }

    public void setEvents(ArrayList<MyEvent> events) {
        this.events = events;
    }

    public ArrayList<MyEvent> getEvents() {
        return events;
    }

    public void addEvent(MyEvent event) {
        this.events.add(event);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDatabaseReference = database.getReference("users");
        userDatabaseReference.child(UID).child("events").setValue(events);
    }


    public void setSportsInterests(ArrayList<String> sportsInterests) {
        this.sportsInterests = sportsInterests;
    }

    public boolean interestedIn(String s)
    {
        if(moviesInterests.contains(s))
            return true;
        if(sportsInterests.contains(s))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return email + " " + sportsInterests;
    }
}
