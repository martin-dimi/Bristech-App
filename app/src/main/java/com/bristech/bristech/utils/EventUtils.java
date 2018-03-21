package com.bristech.bristech.utils;


import android.support.annotation.NonNull;
import android.util.Log;

import com.bristech.bristech.entities.Event;
import com.bristech.bristech.entities.User;
import com.bristech.bristech.services.EventService;
import com.bristech.bristech.services.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bristech.bristech.utils.LoginUtils.mAuth;

public class EventUtils {

    public interface EventsCallback {
        void onComplete(List<Event> events);
    }

    public static final String TAG = "EventUtils";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://bristech-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    //Retrofit populates interface
    static EventService mEventService = retrofit.create(EventService.class);

    public static void getAllEvents(final EventsCallback callback){
        Log.i(TAG, "Getting all events");

        //TODO FIX ASYNCHRONOUS
        Call<List<Event>> allEventsCall = mEventService.getAllEvents();
        allEventsCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "successfully fetched events");
                    List<Event> events = response.body();

                    callback.onComplete(events);
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

    }

}
