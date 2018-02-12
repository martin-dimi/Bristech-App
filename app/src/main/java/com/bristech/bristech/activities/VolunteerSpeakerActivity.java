package com.bristech.bristech.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bristech.bristech.R;

public class VolunteerSpeakerActivity extends AppCompatActivity {
    private EditText mSpeakerNameField;
    private EditText mSpeakerTopicField;
    private EditText mSpeakerEmailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_speaker);
        mSpeakerNameField = findViewById(R.id.speaker_name);
        mSpeakerTopicField = findViewById(R.id.speaker_topic);
        mSpeakerEmailField = findViewById(R.id.speaker_email);
    }

    public void submitBtnPress(View view) {
        Log.i("VolunteerSpeakerActivit", "Submit button pressed");
        TextView textView = findViewById(R.id.thank_for_speaker_volunteer);
        textView.setText(R.string.txt_thank_for_speaker_volunteer);
        String name = mSpeakerNameField.getText().toString();
        String topic = mSpeakerTopicField.getText().toString();
        String email = mSpeakerEmailField.getText().toString();
    }
}
