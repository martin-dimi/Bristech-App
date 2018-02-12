package com.bristech.bristech.services;

import com.bristech.bristech.entities.Account;
import com.bristech.bristech.entities.Event;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


/**
 * Interface for retrofit to connect to different endpoints
 */
public interface UserService {

    @POST("/login")
    Call<Void> login(@Body Account account);

    @GET("/event/1")
    Call<Event> getEvent1(@Header("token") String token);
}
