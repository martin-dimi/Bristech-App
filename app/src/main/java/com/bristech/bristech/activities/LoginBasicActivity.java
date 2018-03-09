package com.bristech.bristech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bristech.bristech.R;
import com.bristech.bristech.utils.LoginUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import static com.bristech.bristech.utils.LoginUtils.mAuth;


public class LoginBasicActivity extends AppCompatActivity {


    private static final String TAG = "LOG_IN ACTIVITY";
    private static final int GOOGLE_SIGN_IN = 100;
    private static final int FACEBOOK_SIGN_IN = 101;

    //views
    private EditText mEmailField;
    private EditText mPasswordField;
    private SignInButton mGoogleSignInButton;
    private Button mSignInButton;
    private Button mLogout;
    private TextView mCreateAccTextView;

    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_basic);


        mLogout = findViewById(R.id.btn_logout);
        mEmailField = findViewById(R.id.username);
        mPasswordField = findViewById(R.id.password);
        mGoogleSignInButton = findViewById(R.id.btn_google_sign_in);
        mSignInButton = findViewById(R.id.bt_sign_in);
        mCreateAccTextView = findViewById(R.id.tv_create_account);

        updateButtonListeners();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.btn_facebook_sign_in);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                LoginUtils.loginWithFacebook(loginResult.getAccessToken(), getApplicationContext());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    public void signInEmail() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (!email.isEmpty() && !password.isEmpty())
            LoginUtils.signinWithAccount(email, password, this);
        else {
            View content = findViewById(android.R.id.content);
            Snackbar.make(content, "Please enter login information!", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void createAccount() {
        Intent createAccountIntent = new Intent(this, createAccount.class);
        startActivity(createAccountIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GOOGLE_SIGN_IN:
                LoginUtils.loginWithGoogle(data, this);
                break;
            default:
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void updateButtonListeners() {
        mGoogleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInGoogle();
            }
        });

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInEmail();
            }
        });

        mCreateAccTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }

}
