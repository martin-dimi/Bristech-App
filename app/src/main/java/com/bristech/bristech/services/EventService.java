package com.bristech.bristech.services;

import com.bristech.bristech.entities.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface EventService {
    @GET("/event/all")
    Call<List<Event>> getAllEvents();

    @GET("/event/upcoming")
    Call<List<Event>> getUpcomingEvents();

    @GET("/event/past")
    Call<List<Event>> getPastEvents();

    @GET("/event/attend")
    Call<Boolean> attendEvent(@Header("email") String email, @Header("event_id") long eventId);
}
