package com.bristech.bristech.entities;


import java.io.Serializable;

public class Event implements Serializable{

    private String title;
    private String topic;
    private String date;
    private String time;
    private int duration;
    private String location;
    private String description;

    public Event(String title, String topic, String date, String time, int duration, String location, String description) {
        this.title = title;
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.location = location;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
