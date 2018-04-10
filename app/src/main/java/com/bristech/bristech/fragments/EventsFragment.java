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
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import com.bristech.bristech.R;
import com.bristech.bristech.activities.EventDetailActivity;
import com.bristech.bristech.adaptors.EventsAdaptor;
import com.bristech.bristech.entities.Event;
import com.bristech.bristech.utils.EventUtils;

import java.util.List;

public class EventsFragment extends Fragment implements EventsAdaptor.EventOnClick, EventUtils.EventsCallback<List<Event>>{
    public static final String TAG = "EventsFragment";

    public static final String EVENT = "events";

    private List<Event> mEvents;
    private EventsAdaptor mEventsAdaptor;

    //Views
    private RecyclerView mRecyclerView;

    public EventsFragment() {
    }

    public static EventsFragment getInstance(){
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO parcelable

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        //Setting up the events recycler view
        mEventsAdaptor = new EventsAdaptor(mEvents, this);

        mRecyclerView = root.findViewById(R.id.rv_events);
        mRecyclerView.setAdapter(mEventsAdaptor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void setEvents(List<Event> events){
        Log.d(TAG, "seating the events:" + events.get(0).getName());
        this.mEvents = events;
        updateAdaptor(events);
    }

    private void updateAdaptor(List<Event> events){
        if(events != null) {
            Log.d(TAG, "Updating adaptor");
            mEventsAdaptor.updateEvents(events);
        }
    }

    @Override
    public void onClick(int position) {
        // create new intent
        Intent eventDetailActivityIntent = new Intent(getContext(), EventDetailActivity.class);

        Event event = mEvents.get(position);

        // send details of event to individual view
        Bundle bundle = new Bundle();
        bundle.putSerializable(EVENT, event);
        eventDetailActivityIntent.putExtras(bundle);

        // start the activity
        startActivity(eventDetailActivityIntent);

    }

    @Override
    public void onComplete(List<Event> events) {
        setEvents(events);
        Toast.makeText(getActivity(), "Events are updated!", Toast.LENGTH_LONG).show();
    }
}
