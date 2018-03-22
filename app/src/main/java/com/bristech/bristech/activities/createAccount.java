package com.bristech.bristech.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bristech.bristech.R;
import com.bristech.bristech.utils.LoginUtils;

public class createAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final EditText email = findViewById(R.id.txtEmail);
        EditText username = findViewById(R.id.txtUsername);
        EditText firstname = findViewById(R.id.txtFirstName);
        EditText lastname = findViewById(R.id.txtOtherNames);
        Spinner gender = findViewById(R.id.spiGender);
        final EditText password = findViewById(R.id.txtPassword);
        final EditText retypePassword = findViewById(R.id.txtRetypePassword);

        final Button createAccount = findViewById(R.id.btnCreateAccount);
        final TextView infoText = findViewById(R.id.txtInfoText);

        // create account button
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Check fields are valid.
                boolean allFieldsValid = true;
                if (!checkEmail(email.getText().toString())) {
                    allFieldsValid = false;
                    infoText.setText("Invalid email format"); }
                else if (!checkPasswords(password.getText().toString(), retypePassword.getText().toString())) {
                    allFieldsValid = false;
                    infoText.setText("Passwords do not match"); }

                // If so, send request to server, which sends a verification email

                // set info text
                if (!allFieldsValid) {
                    infoText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.maroon));
                    infoText.setVisibility(View.VISIBLE);
                }
                else {
                    infoText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    infoText.setText("Verification email sent to " + email.getText().toString());
                    infoText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public boolean checkEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean checkPasswords(String firstPassword, String secondPassword) {
        return (firstPassword == secondPassword);
    }
}
