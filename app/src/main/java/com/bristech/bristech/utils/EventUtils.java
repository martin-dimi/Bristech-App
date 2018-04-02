package com.bristech.bristech.utils;


import android.support.annotation.NonNull;
import android.util.Log;

import com.bristech.bristech.entities.Event;
import com.bristech.bristech.services.EventService;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventUtils {

    public static final String TAG = "EventUtils";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://bristech-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    //Retrofit populates interface
    private static EventService mEventService = retrofit.create(EventService.class);

    /**
     * Get's all the events from server
     * @param callback callback to be executed when the callback is done
     */
    public static void getAllEvents(final EventsCallback callback){
        Log.i(TAG, "Getting all events");

        //TODO extract callback function for events (you're using twice)
        Call<List<Event>> allEventsCall = mEventService.getAllEvents();
        allEventsCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "Successfully fetched events");
                    List<Event> events = response.body();

                    // TODO Add null check for callback
                    callback.onComplete(events);
                }
                else{
                    // TODO Handle appropriate failure for response != OK
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // TODO Handle appropriate failure for not connecting to server
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * Get's upcoming the events from server
     * @param callback callback to be executed when the callback is done
     */
    public static void getUpcomingEvents(final EventsCallback<List<Event>> callback){
        Call<List<Event>> allEventsCall = mEventService.getUpcomingEvents();
        allEventsCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "successfully fetched events");
                    List<Event> events = response.body();

                    // TODO Add null check for callback
                    callback.onComplete(events);
                } else{
                    // TODO Handle appropriate failure for response != OK
                    Log.e(TAG, "Error request was not successful");
                    Log.e(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // TODO Handle appropriate failure for not connecting to server
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * Get's past the events from server
     * @param callback callback to be executed when the callback is done
     */
    public static void getPastEvents(final EventsCallback<List<Event>> callback){
        Call<List<Event>> allEventsCall = mEventService.getPastEvents();
        allEventsCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "successfully fetched events");
                    List<Event> events = response.body();
                    Collections.reverse(events);

                    // TODO Add null check for callback
                    callback.onComplete(events);
                } else{
                    // TODO Handle appropriate failure for response != OK
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // TODO Handle appropriate failure for not connecting to server
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * Registers/Unregisters the user from the event
     * @param eventId   the id of the event
     * @param userEmail the email of the user
     * @param callback  callback to be executed when the callback is done
     */
    public static void attendEvent(long eventId, String userEmail, final EventsCallback<Boolean> callback){
        Call<Boolean> allEventsCall = mEventService.attendEvent(userEmail, eventId);
        allEventsCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if(response.isSuccessful()){
                    Boolean isUserAttending = response.body();

                    callback.onComplete(isUserAttending);
                } else {
                    // TODO Handle appropriate failure for response != OK
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                // TODO Handle appropriate failure for not connecting to server
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public interface EventsCallback<T> {
        void onComplete(T object);
    }
}
