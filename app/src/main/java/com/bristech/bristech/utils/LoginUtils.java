package com.bristech.bristech.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.bristech.bristech.activities.LoginBasicActivity;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginUtils {

    private static final String TAG = "Login";
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static void loginWithGoogle(Intent data, final Context context) {
        Log.i(TAG, "Loggin in with Google");

        //Handles google authorization
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account = task.getResult();

        //Handles firebase
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                handleFirebase(task, context);
            }
        });
    }

    public static void loginWithFacebook(AccessToken accessToken, final Context context) {

        //Handles Facebook authorization
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        //Handles firebase
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                handleFirebase(task, context);
            }
        });
    }

    public static void signinWithAccount(String email, String password, final Context context) {
        Log.i(TAG, "Loggin in with username and password");

        //Handles firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                handleFirebase(task, context);
            }
        });
    }

    public static void signOut(){
        mAuth.signOut();
    }

    private static void handleFirebase(@NonNull Task<AuthResult> task, Context context) {
        if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            FirebaseUser user = mAuth.getCurrentUser();
            Log.i(TAG, "uId: " + user.getUid());
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

}
