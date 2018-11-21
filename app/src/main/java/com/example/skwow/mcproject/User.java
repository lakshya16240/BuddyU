package com.example.skwow.mcproject;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class User {


    private String username;
    private String email;
    private String UID;
    private String notification;
    private ArrayList<String> moviesInterests = new ArrayList<>();
    private ArrayList<String> sportsInterests = new ArrayList<>();


    @Exclude
    public static User currentUser = null;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String _UID, ArrayList<String> _moviesInterests, ArrayList<String> _sportsInterests)
    {
        this.username = username;
        this.email = email;
        this.UID = _UID;
        this.notification = "default";
        moviesInterests = _moviesInterests;
        sportsInterests = _sportsInterests;
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

    public void setSportsInterests(ArrayList<String> sportsInterests) {
        this.sportsInterests = sportsInterests;
    }

    @Override
    public String toString() {
        return email + " " + sportsInterests;
    }
}
