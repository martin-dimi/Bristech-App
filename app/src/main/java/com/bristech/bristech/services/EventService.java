package com.bristech.bristech.services;

import com.bristech.bristech.entities.Event;
import com.bristech.bristech.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by marti on 21/03/2018.
 */

public interface EventService {
    @GET("/event/all")
    Call<List<Event>> getAllEvents();
}
