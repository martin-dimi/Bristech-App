package com.bristech.bristech.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bristech.bristech.R;

public class EventDetailActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        setText("event_title", R.id.event_title);
        setText("event_date", R.id.event_date);
        setText("event_time", R.id.event_time);
        setText("event_location", R.id.event_location);
        setText("event_description", R.id.event_description);

        ImageView testImage = findViewById(R.id.event_image);
        testImage.setImageResource(R.drawable.test_event_image_2);
    }

    private void setText(String key, int textViewID) {
        String string = getIntent().getExtras().getString(key);
        TextView textView = findViewById(textViewID);
        textView.setText(string);
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
