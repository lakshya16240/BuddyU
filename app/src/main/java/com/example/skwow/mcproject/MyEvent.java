package com.example.skwow.mcproject;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MyEvent
{

    private String type;
    private String Venue;
    private String Time;
    private float cost;
    private String heading;
    private String description;
    private String imageLink;
    private String createdBy;
    private String salt;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    public MyEvent(String type, String venue, String time, float cost, String heading, String description, String createdBy, String imgLink,String salt) {
        this.type = type;
        Venue = venue;
        Time = time;
        this.cost = cost;
        this.heading = heading;
        this.description = description;
        this.createdBy = createdBy;
        imageLink = imgLink;
        users.add(User.currentUser);
        this.salt = salt;

    }
    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void addUser(User user){
        users.add(user);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groupDatabaseReference = database.getReference("Groups").child(salt).child("users");

        groupDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users = (ArrayList<User>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        users.add(user);
        groupDatabaseReference.setValue(users);
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public MyEvent() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String getSaltString(int size) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        this.salt = salt.toString();
        return this.salt;
    }

    public void pushToDatabase()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Events");
        myRef.child(getSaltString(15)).setValue(this);
        myRef.child(salt).child("EventId").setValue(salt);

        DatabaseReference groupDatabaseReference = database.getReference("Groups");
        groupDatabaseReference.child(salt).child("users").setValue(users);

    }

}
