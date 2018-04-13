package com.bristech.bristech;


import android.provider.CalendarContract;

import com.bristech.bristech.entities.Event;
import com.bristech.bristech.entities.User;
import com.bristech.bristech.services.EventService;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

public class EventUtilsTest {

    List<Event> mResultList;
    MockWebServer mMockWebServer;
    TestSubscriber<List<Event>> mSubscriber;

    @Before
    public void setUp() {
        Event event1 = new Event(1, "a", "a"
                , (long) 1, (long) 1, 1, "a",
                "a", new ArrayList<User>(), "a", "a");
        Event event2 = new Event(5, "g", "d"
                , (long) 6, (long) 2, 7, "fg",
                "d", new ArrayList<User>(), "v", "df");
        mResultList = new ArrayList();
        mResultList.add(event1);
        mResultList.add(event2);

        mMockWebServer = new MockWebServer();
        mSubscriber = new TestSubscriber<>();
    }

    @Test
    public void getAllEventsTest() {
        //Given
        String url = "https://guessthebeach.herokuapp.com/api/";
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mResultList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();

        EventService mEventService = retrofit.create(EventService.class);

        mEventService.getAllEvents();
        allEvents.enqueue();

        //When
        remoteDataSource.getTopicsRx().subscribe(mSubscriber);

        //Then
        mSubscriber.assertNoErrors();
        mSubscriber.assertCompleted();

    }


}

