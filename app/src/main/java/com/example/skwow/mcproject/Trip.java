package com.example.skwow.mcproject;

public class Trip {

    private int id;
    private String title;


    private String book;
    private double rating;
    private int image;

    public Trip(int id, String title, String book, double rating, int image) {
        this.id = id;
        this.title = title;

        this.book = book;
        this.image = image;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }
    public String getBook() {
        return book;
    }

    public String getTitle() {
        return title;
    }
    public double getRating() {
        return rating;
    }



    public int getImage() {
        return image;
    }
}
