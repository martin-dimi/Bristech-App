package com.bristech.bristech.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bristech.bristech.R;
import com.bristech.bristech.utils.LoginUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void logOutBtnPress(View view) {
        Log.i("SettingsActivity", "Log out button pressed");
        LoginUtils.signOut();
        Intent loginActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(loginActivityIntent);
        finish();
    }

    public void modifyInterestsBtnPress(View view) {
        Log.i("SettingsActivity", "Modify Interests button pressed");
    }

    public void editUserSettingsBtnPress(View view) {
        Log.i("SettingsActivity", "Edit user settings button pressed");
    }

}