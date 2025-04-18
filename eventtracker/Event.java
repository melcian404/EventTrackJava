package com.zybooks.eventtracker;


// Event class to be used by Array list
public class Event {
    // Variables for input fields
    private String date;
    private String name;
    private String details;

    public Event(String date, String name, String details) {
        this.date = date;
        this.name = name;
        this.details = details;
    }

    // Getters for input fields
    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public String getDetails() {
        return details;
    }
}