package com.bristech.bristech.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bristech.bristech.R;
import com.bristech.bristech.entities.Event;

import java.util.List;

public class EventsAdaptor extends RecyclerView.Adapter<EventsAdaptor.EventCard> {

    private List<Event> mEvents;
    private EventOnClick mEventOnClick;


    public EventsAdaptor(List<Event> mEvents, EventOnClick eventOnClick) {
        this.mEvents = mEvents;
        this.mEventOnClick = eventOnClick;
    }

    public void updateEvents(List<Event> events){
        mEvents = events;
        notifyDataSetChanged();
    }

    @Override
    public EventCard onCreateViewHolder(ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        int movieItemLayout = R.layout.card_event;
        View view = LayoutInflater.from(parentContext).inflate(movieItemLayout, parent, false);
        return new EventCard(view);    }

    @Override
    public void onBindViewHolder(EventCard holder, int position) {
        Event event = mEvents.get(position);

        holder.myTitle.setText(event.getName());
//        holder.myImage.setImageResource(R.drawable.test_event_image_2);
        holder.myShortDescription.setText(event.getShortDescription());
        holder.myTime.setText(event.getTimeStr());
        holder.myDate.setText(event.getDateStr());
//        holder.myLocation.setText(event.getLocation());
    }

    @Override
    public int getItemCount() {
        if(mEvents == null) return 0;
        return mEvents.size();
    }

    public interface EventOnClick{
        void onClick(int position);
    }

    class EventCard extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView myTitle;
//        ImageView myImage;
        TextView myTopic;
        TextView myDate;
        TextView myTime;
//        TextView myLocation;
        TextView myShortDescription;

        EventCard(View itemView) {
            super(itemView);
            myTitle = itemView.findViewById(R.id.event_card_title);
//            myImage = itemView.findViewById(R.id.event_card_image);
            myTopic = itemView.findViewById(R.id.event_card_topic);
            myDate = itemView.findViewById(R.id.event_card_date);
            myTime = itemView.findViewById(R.id.event_card_time);
//            myLocation = itemView.findViewById(R.id.event_card_location);
            myShortDescription = itemView.findViewById(R.id.event_card_shortdescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mEventOnClick.onClick(getAdapterPosition());
        }
    }
}
