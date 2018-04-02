package com.bristech.bristech.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bristech.bristech.R;
import com.bristech.bristech.entities.Event;

import static com.bristech.bristech.fragments.EventsFragment.EVENT;

public class EventDetailActivity extends AppCompatActivity {
    public static final String TAG = "EventDetails";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Event event = (Event) getIntent().getSerializableExtra(EVENT);
        setText(event.getName(), R.id.event_title);
        setText(event.getDateStr(), R.id.event_date);
        setText(event.getTimeStr(), R.id.event_time);
        setText(event.getLocation(), R.id.event_location);
        setText(event.getDescription(), R.id.event_description);

//        ImageView testImage = findViewById(R.id.event_image);
//        testImage.setImageDrawable(event.getImage(this));
    }

    private void setText(String key, int textViewID) {
        TextView textView = findViewById(textViewID);
        textView.setText(key);
    }

    public void registerForTalkBtnPress(View view) {
        Log.i("SettingsActivity", "Register for talk button pressed");
        new AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage("You are successfully registered for this event.")
                .setPositiveButton("OK", null)
                .show();
    }
}
