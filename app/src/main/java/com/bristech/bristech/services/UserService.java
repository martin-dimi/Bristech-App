package com.bristech.bristech.services;

import com.bristech.bristech.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * Interface for retrofit to connect to different endpoints
 */
public interface UserService {

    @GET("/user")
    Call<User> getUser(@Header("token") String token);
}
