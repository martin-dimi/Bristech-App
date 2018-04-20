package com.bristech.bristech.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.TextView;

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

        Toolbar toolbar = findViewById(R.id.volunteer_toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white);

        toolbar.setTitle(R.string.volunteerToSpeak);
        toolbar.setTitleTextColor(getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView textView = findViewById(R.id.txt_poll_link);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "Click this <a href='https://docs.google.com/forms/d/e/1FAIpQLSf-9t-zSt4x2qGpkw17yr50i8hOZ_ShrIKvMZA6B0JmyoPVLw/viewform'>Google Poll</a> link to volunteer a speaker";
        textView.setText(Html.fromHtml(text));



    }


}
