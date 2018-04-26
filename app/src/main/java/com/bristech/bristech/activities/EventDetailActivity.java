package com.bristech.bristech.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bristech.bristech.R;
import com.bristech.bristech.entities.Event;
import com.bristech.bristech.entities.User;
import com.bristech.bristech.utils.UserUtils;

import java.util.List;

import static com.bristech.bristech.fragments.EventsFragment.EVENT;

public class EventDetailActivity extends AppCompatActivity {
    public static final String TAG = "EventDetails";
    private int flag = 0;

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
        toolbar.setNavigationIcon(R.drawable.arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final Button registerButton = findViewById(R.id.btn_register_for_event);

        if(mEvent != null && mEvent.getStatus().equals("past")) {
            registerButton.setVisibility(View.GONE);
        }

        if(User.currentUser == null || User.currentUser.getEmail() == null) {
            registerButton.setVisibility(View.GONE);
        }
        else {
            if(User.currentUser.getEvents().contains(mEvent.getId())) {
                registerButton.setBackgroundResource(R.drawable.clr_pressed);
            }
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag==0){
                    flag=1;
                }
                else{
                    flag=0;
                }

                // 1 means user is going and the color of btn shows grey
                if (flag == 1){
                    registerButton.setBackgroundResource(R.drawable.clr_pressed);
//                registerForTalkBtnPress(v);
                }
                // 0 means user is not going and the color of btn shows red
                else if (flag==0){
                    registerButton.setBackgroundResource(R.drawable.clr_normal);
                }
            }
        });

    }

    private void setText(String key, int textViewID) {
        TextView textView = findViewById(textViewID);
        textView.setText(key);
    }

    public void registerForTalkBtnPress(View view) {
        register();
    }

    void register(){
//        Log.i(TAG, "lkfgghfgkhjcghghb");
        UserUtils.attendEvent(mEvent.getId(), User.currentUser.getEmail(), new UserUtils.UserCallback<Boolean>() {
            @Override
            public void onComplete(final Boolean isGoing) {
                UserUtils.getUser(new UserUtils.UserCallback<User>() {
                    @Override
                    public void onComplete(User uobject) {
                        User.currentUser = uobject;
                        if( isGoing ) {
                            Snackbar.make(findViewById(R.id.activity_event_coordinator),
                                    "Registered"
                                    , Snackbar.LENGTH_LONG).show();


                            // USER IS GOING
//                            isGoing();
                        }
                        else {
                            Snackbar.make(findViewById(R.id.activity_event_coordinator),
                                    "Unregistered"
                                    , Snackbar.LENGTH_LONG).show();

                            // USER IS NOT GOING
//                            isNotGoing();
                        }
                    }
                });
            }
        });
    }

//    //If going the color of the register btn shows grey.
//    private void isGoing(){
//
//    }
//
//    //If not going the color of the btn shows red.
//    private void isNotGoing(){
//
//    }
}