package com.example.skwow.mcproject;

import com.google.firebase.database.Exclude;

public class User {

    public String username;
    public String email;

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


}
