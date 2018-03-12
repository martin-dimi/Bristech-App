package com.bristech.bristech.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
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

    public interface AuthenticationCallback extends OnCompleteListener<AuthResult> {
        @Override
        void onComplete(@NonNull Task<AuthResult> task);
    }

    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private static final String TAG = LoginUtils.class.getSimpleName();

    public static void signInWithGoogle(Intent data, AuthenticationCallback callback) {
        Log.i(TAG, "Authorising in with Google");

        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            firebaseProviderAuth(credential, callback);

        } catch (ApiException e) {
            Log.w(TAG, "Google sign-in failed", e);
        }
    }

    public static void signInWithFacebook(CallbackManager manager, int requestCode, int resultCode, Intent data){
        manager.onActivityResult(requestCode, resultCode, data);
    }

    public static void signInWithEmail(String email, String password, AuthenticationCallback callback){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(callback);
    }

    public static void createAccount(String email, String password, AuthenticationCallback callback){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(callback);
    }

    public static void signOut() {
        mAuth.signOut();
    }

    public static boolean isLoggedIn(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return !(currentUser == null);
    }

    public static void handleFacebookToken(AccessToken token, AuthenticationCallback callback) {
        Log.i(TAG, "Authorising in with Facebook");

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseProviderAuth(credential, callback);
    }

    private static void firebaseProviderAuth(AuthCredential credential, AuthenticationCallback callback) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(callback);
    }

}
