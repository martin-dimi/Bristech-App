package com.bristech.bristech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bristech.bristech.R;
import com.bristech.bristech.entities.User;
import com.bristech.bristech.utils.LoginUtils;
import com.bristech.bristech.utils.UserUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class createAccount extends AppCompatActivity implements LoginUtils.AuthenticationCallback {

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

        Toolbar toolbar = findViewById(R.id.create_toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

                accCreate(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void accCreate(String email, String pass){
        LoginUtils.createAccount(email, pass, this);
    }


    @SuppressWarnings("unused")
    public static boolean isStringValid(String toExamine) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9~#@*+%{}<>\\[\\]|\"\\_^]+");
        Matcher matcher = pattern.matcher(toExamine);
        return !matcher.find();
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPassEqual(String pass1, String pass2) {
        return pass1 == pass2;
    }

    @SuppressWarnings("unused")
    public static boolean isPasswordLengthValid(String pass) {
        if(pass.length() < 9) return false;
        else return pass.length() <= 16;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        try {
            AuthResult r = task.getResult(FirebaseException.class);
            showSnackbar("Successful login");
            UserUtils.getUser(new UserUtils.UserCallback<User>() {
                @Override
                public void onComplete(User user) {
                    // TODO This is the callback function for getting the user from our servers, do something here
                    // TODO or use the function below
                    userHasLoggedIn(user);
                }
            });

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 900);   //1 seconds
        } catch (FirebaseAuthUserCollisionException e) {
            showSnackbar("User already exists with another provider");
        } catch (FirebaseAuthInvalidCredentialsException e) {
            showSnackbar("Please enter a valid email");
        } catch (FirebaseAuthInvalidUserException e) {
            showSnackbar("Account does not exist");
        } catch (FirebaseException e) {
            showSnackbar("An error occurred");
            e.printStackTrace();
        }
    }

    private void showSnackbar(String message) {
        Snackbar
                .make(findViewById(R.id.createAccountCoordinator),
                        message
                        , Snackbar.LENGTH_LONG)
                .show();
    }

    private void userHasLoggedIn(User user){
        // TODO Do something with the user.. like save it to sharedPreferences
        User.currentUser = user;

        Intent startLogin = new Intent(this, MainActivity.class);
        startLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startLogin);
        finish();
    }

    public boolean checkEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean checkPasswords(String firstPassword, String secondPassword) {
        return (Objects.equals(firstPassword, secondPassword));
    }
}
