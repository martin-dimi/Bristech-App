package com.bristech.bristech.services;

import com.bristech.bristech.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * Interface for retrofit to connect to different endpoints
 */
public interface UserService {

    @GET("/user/login")
    Call<User> loginUserWithToken(@Header("token") String token);

    @GET("/user/create")
    Call<User> createUser(@Body User user);
}
