package com.bristech.bristech.entities;


import java.io.Serializable;
import java.util.List;

public class Event implements Serializable{

    private long id;
    private String name;
    private String description;

    // TODO The time is in milliseconds transfer it to Date type (hint use set method to get long and transfer it to Date)
    private Long time;
    private Long duration;
    private int waitlistCount;
    private String status;
    private String eventUrl;

    public Event(){

    }

    public Event(long id, String name, String description, Long time, Long duration, int waitlistCount, String status, String eventUrl, List<User> users, String backdrop) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.duration = duration;
        this.waitlistCount = waitlistCount;
        this.status = status;
        this.eventUrl = eventUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getWaitlistCount() {
        return waitlistCount;
    }

    public void setWaitlistCount(int waitlistCount) {
        this.waitlistCount = waitlistCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

}
