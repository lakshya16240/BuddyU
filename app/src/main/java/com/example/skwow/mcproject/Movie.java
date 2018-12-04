package com.example.skwow.mcproject;

import com.google.firebase.database.Exclude;

import java.security.PublicKey;
import java.util.ArrayList;

public class Movie
{
    String name;
    String time;
    String venue;

    @Exclude
    public static ArrayList<Movie> allMovies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTiming() {
        return time;
    }

    public void setTiming(String timing) {
        this.time = timing;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    String imageLink;

    public Movie(){}

    public Movie(String name, String timing, String venue, String imageLink) {
        this.name = name;
        this.time = timing;
        this.venue = venue;
        this.imageLink = imageLink;
    }
}
