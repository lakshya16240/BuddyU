package com.example.skwow.mcproject;

public class Sports {
    private int id;
    private String title;

    private String venue;

    private int image;

    public Sports(int id, String title, String venue, String book, int image) {
        this.id = id;
        this.title = title;
            this.venue = venue;

        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }



    public String getVenue() {
        return venue;
    }



    public int getImage() {
        return image;
    }
}
