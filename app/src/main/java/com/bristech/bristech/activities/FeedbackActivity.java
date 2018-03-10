package com.bristech.bristech.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bristech.bristech.R;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void submitBtnPress(View view) {
        Log.i("FeedbackActivity", "Submit button pressed");
        TextView textView = findViewById(R.id.txt_thank_for_feedback);
        textView.setText(R.string.thank_for_feedback);
    }

    public void returnToHomepageBtnPress(View view) {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }


}
