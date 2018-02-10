package com.bristech.bristech.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bristech.bristech.R;
import com.bristech.bristech.utils.EventUtils;
import com.bristech.bristech.utils.UserUtils;


public class LoginBasicActivity extends AppCompatActivity implements UserUtils.UserUtilsCallback {

    private EditText mUsernameField;
    private EditText mPasswordField;

    //JWT TOKEN used for authentication
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_basic);

        mUsernameField = findViewById(R.id.username);
        mPasswordField = findViewById(R.id.password);
    }

    public void login(View view){
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();

        UserUtils.login(this, username, password, this);
    }



    public void getevents(View view){
        EventUtils.getAllEvents(this, token);
    }


    /**
     * Callback for successful login
     * @param token JWT token used for authentication
     */
    @Override
    public void getToken(String token) {
        this.token = token;
    }
}
