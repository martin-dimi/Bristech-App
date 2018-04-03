package com.bristech.bristech.entities;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.bristech.bristech.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Event implements Serializable{

    private long id;
    private String name;
    private String description;
    private Long time;
    private Long duration;
    private int waitlistCount;
    private String status;
    private String eventUrl;

    private String location;
    private String shortDescription;
//    private Drawable image;

    public Event() {

    }

    // TODO: specify whether event is from meetup in order to format description as HTML or not
    public Event(long id, String name, String description, Long time, Long duration, int waitlistCount, String status, String eventUrl, List<User> users, String backdrop, String location/*, Drawable image*/) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.duration = duration;
        this.waitlistCount = waitlistCount;
        this.status = status;
        this.eventUrl = eventUrl;
        this.location = location;
//        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        if( name != null ) { return name; }
        else { return "no_name"; }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if( description != null ) { return description; }
        else { return "no_description"; }
    }

    public Spanned getDescriptionHtml() {
        if( description != null ) { return Html.fromHtml(description); }
        else { return Html.fromHtml("no_description"); }
    }

    public String getShortDescription() {
        if( this.shortDescription != null ) {
            return this.shortDescription;
        }

        if( this.description != null ) {
            try {
                String[] pSep = description.split("<br/>")[1].split("</p>");
                this.shortDescription = pSep[0].concat("\n\n").concat(pSep[1].split("<p>")[1]);
            }
            catch(Exception e1) {
                try {
                    String[] ppSep = description.split("</p>");
                    this.shortDescription = ppSep[0].split("<p>")[1].concat("\n\n").concat(ppSep[1].split("<p>")[1]);
                }
                catch(Exception e2) {
                    this.shortDescription = this.description;
                }
            }
            this.shortDescription = this.shortDescription.replaceAll("<br/>", "\n\n");
            return this.shortDescription;
        }
        else { return "no_short_description"; }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTime() {
        if( time != null ) { return time; }
        else {
            long i = 0;
            return i;
        }
    }

    public void setTime(Long time) {
        this.time = time;
    }

    // TODO: fix time
    public String getTimeStr() {
        if( time != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE HH:mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(this.time + 3600000 * 19);
            String retStr = formatter.format(calendar.getTime());
            return retStr;
        }
        else {
            return "no_time";
        }
    }

    public String getDateStr() {
        if( time != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(this.time);
            String retStr = formatter.format(calendar.getTime());
            return retStr;
        }
        else {
            return "no_time";
        }
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

    public String getLocation() {
        if( location != null ) { return location; }
        else { return "Bristol Engine Shed"; }
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

//    public Drawable getImage(Activity inActivity) {
//        if( this.image != null ) { return image; }
//        else { return ContextCompat.getDrawable(inActivity,R.drawable.test_event_image_2); }
//    }

}
