package com.bristech.bristech.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bristech.bristech.R;
import com.bristech.bristech.activities.EventDetailActivity;
import com.bristech.bristech.adaptors.EventsAdaptor;
import com.bristech.bristech.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment implements EventsAdaptor.EventOnClick {
    public static final String TAG = "EventsFragment";

    private static final String EVENT = "events";

    private List<Event> mEvents;
    private EventsAdaptor mEventsAdaptor;

    //Views
    private RecyclerView mRecyclerView;

    public EventsFragment() {
    }

    public static EventsFragment getInstance(List<Event> events){
        EventsFragment fragment = new EventsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(EVENT, (ArrayList<Event>) events);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEvents = (List<Event>) getArguments().getSerializable(EVENT);
        mEventsAdaptor = new EventsAdaptor(mEvents, this);

        Log.i(TAG, "passing to individual Event View");
        Log.i(TAG, mEvents.get(0).getTitle());
        Log.i(TAG, mEvents.get(0).getLocation());
        Log.i(TAG, mEvents.get(0).getDate());
        Log.i(TAG, mEvents.get(0).getTime());
        Log.i(TAG, mEvents.get(0).getDescription());
        Log.i(TAG, mEvents.get(0).getBackdrop());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        //Setting up the events recycler view
        mRecyclerView = root.findViewById(R.id.rv_events);
        mRecyclerView.setAdapter(mEventsAdaptor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    public void setEvents(List<Event> events){
        this.mEvents = events;
        updateAdaptor(events);
    }

    private void updateAdaptor(List<Event> events){
        if(events != null)
            mEventsAdaptor.updateEvents(events);
    }

    @Override
    public void onClick() {
        // create new intent
        Intent eventDetailActivityIntent = new Intent(getContext(), EventDetailActivity.class);

        // send details of event to individual view
        Bundle mBundle = new Bundle();
        mBundle.putString("event_title", mEvents.get(0).getTitle());
        mBundle.putString("event_date", mEvents.get(0).getDate());
        mBundle.putString("event_time", mEvents.get(0).getTime());
        mBundle.putString("event_location", mEvents.get(0).getLocation());
        mBundle.putString("event_description", mEvents.get(0).getDescription());
        eventDetailActivityIntent.putExtras(mBundle);

        // start the activity
        startActivity(eventDetailActivityIntent);
    }
}
