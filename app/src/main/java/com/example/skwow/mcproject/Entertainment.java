package com.example.skwow.mcproject;

public class Entertainment {

    private int id;
    private String title;
    private String book;
    private String description;
    private int image;

    public Entertainment(int id, String title, String book, String description, int image) {
        this.id = id;
        this.title = title;

        this.book = book;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBook() {
        return book;
    }
    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
