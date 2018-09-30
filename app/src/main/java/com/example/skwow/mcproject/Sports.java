package com.example.skwow.mcproject;

public class Sports {
    private int id;
    private String title;

    private String book;
    private String language;
    private int image;

    public Sports(int id, String title, double rating, String book, String language, int image) {
        this.id = id;
        this.title = title;

        this.book = book;
        this.language = language;
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
    public String getLanguage() {
        return language;
    }

    public int getImage() {
        return image;
    }
}
