package com.example.skwow.mcproject;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class User {


    private String username;
    private String email;
    private ArrayList<String> moviesInterests = new ArrayList<>();
    private ArrayList<String> sportsInterests = new ArrayList<>();

    //interests -> movies
    //                      -> Horror
    //                      -> Fantasy
    //          -> sports

    @Exclude
    public static User currentUser = null;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
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


}
