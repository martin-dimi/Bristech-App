package com.bristech.bristech.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;

import com.bristech.bristech.R;
import com.bristech.bristech.entities.Event;
import com.bristech.bristech.entities.User;
import com.bristech.bristech.utils.EventUtils;
import com.bristech.bristech.utils.UserUtils;

import static com.bristech.bristech.fragments.EventsFragment.EVENT;

public class EventDetailActivity extends AppCompatActivity {
    public static final String TAG = "EventDetails";

    Event mEvent;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        mEvent = (Event) getIntent().getSerializableExtra(EVENT);
        setText(mEvent.getName(), R.id.event_title);
        setText(mEvent.getDateStr(), R.id.event_date);
        setText(mEvent.getTimeStr(), R.id.event_time);
//        setText(mEvent.getLocation(), R.id.event_location);

        FrameLayout myFrame = findViewById(R.id.event_colour_frame);
        myFrame.setBackgroundColor(mEvent.getTileColour());

        TextView textView = findViewById(R.id.event_description);
        textView.setText(mEvent.getDescriptionHtml());

        Toolbar toolbar = findViewById(R.id.event_toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button registerButton = findViewById(R.id.btn_register_for_event);
        if(mEvent != null && mEvent.getStatus().equals("past")) {
            registerButton.setVisibility(View.GONE);
        }

        if(User.currentUser == null || User.currentUser.getEmail() == null) {
            registerButton.setVisibility(View.GONE);
        }

    }

    private void setText(String key, int textViewID) {
        TextView textView = findViewById(textViewID);
        textView.setText(key);
    }

    public void registerForTalkBtnPress(View view) {
        register();
    }

    void register(){
        EventUtils.attendEvent(mEvent.getId(), User.currentUser.getEmail(), new EventUtils.EventsCallback<Boolean>() {
            @Override
            public void onComplete(final Boolean object) {
                UserUtils.getUser(new UserUtils.UserCallback<User>() {
                    @Override
                    public void onComplete(User uobject) {
                        User.currentUser = uobject;
                        if( object ) {
                            Snackbar.make(findViewById(R.id.activity_event_coordinator),
                                    "Registered"
                                    , Snackbar.LENGTH_LONG).show();
                        }
                        else {
                            Snackbar.make(findViewById(R.id.activity_event_coordinator),
                                    "Unregistered"
                                    , Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
