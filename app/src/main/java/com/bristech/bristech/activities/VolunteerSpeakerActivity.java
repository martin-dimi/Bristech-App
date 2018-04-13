package com.bristech.bristech.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import com.bristech.bristech.R;

public class VolunteerSpeakerActivity extends AppCompatActivity {
    private EditText mSpeakerNameField;
    private EditText mSpeakerTopicField;
    private EditText mSpeakerEmailField;
    private View hiddenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_speaker);
        hiddenText = findViewById(R.id.thank_for_speaker_volunteer);
        hiddenText.setVisibility(View.GONE);
        mSpeakerNameField = findViewById(R.id.speaker_name);
        mSpeakerTopicField = findViewById(R.id.speaker_topic);
        mSpeakerEmailField = findViewById(R.id.speaker_email);
        mSpeakerNameField.setGravity(Gravity.CENTER);
        mSpeakerTopicField.setGravity(Gravity.CENTER);
        mSpeakerEmailField.setGravity(Gravity.CENTER);


    }

    public void submitBtnPress(View view) {
        Log.i("VolunteerSpeakerActivit", "Submit button pressed");
        String name = mSpeakerNameField.getText().toString();
        String topic = mSpeakerTopicField.getText().toString();
        String email = mSpeakerEmailField.getText().toString();
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("Notice")
                .setMessage(String.format(getString(R.string.txt_thank_for_speaker_volunteer), name))
                .setPositiveButton("GO BACK", null)
                .show();

        hiddenText.setVisibility(View.VISIBLE);
    }



}
