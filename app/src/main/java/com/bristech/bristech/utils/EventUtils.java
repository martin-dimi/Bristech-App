package com.bristech.bristech.utils;

import android.content.Context;
import android.widget.Toast;

import com.bristech.bristech.entities.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bristech.bristech.utils.UserUtils.userService;


public class EventUtils {

    /**
     * Creates and executes a call to the endpoint spring:8080/event/1
     * @param context app context
     * @param token JWT token
     */
    public static void getAllEvents(final Context context, String token){
        Call<Event> call = userService.getEvent1(token);

        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.isSuccessful()){
                    Event event = response.body();
                    if(event != null)
                        Toast.makeText(context, event.getTitle(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Wrong token", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
