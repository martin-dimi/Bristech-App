package com.bristech.bristech.utils;


import android.support.annotation.NonNull;
import android.util.Log;

import com.bristech.bristech.entities.User;
import com.bristech.bristech.services.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bristech.bristech.utils.LoginUtils.mAuth;

public class UserUtils {

    public static final String TAG = "UserUtils";
    public static User user;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://bristech-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());
    //Retrofit populates interface
    private static Retrofit retrofit = builder.build();
    private static UserService userService = retrofit.create(UserService.class);

    /**
     * Gets the user from the server,
     * if he doesn't exist, creates it
     * @param callback callback to be executed on successful response
     */
    public static void getUser(final UserCallback<User> callback){
        Log.i(TAG, "Getting user");
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser == null)
            return;

        firebaseUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {
                            String idToken = task.getResult().getToken();
                            Log.i(TAG, idToken);

                            // Send token to your backend via HTTPS
                            Call<User> user = userService.loginUserWithToken(idToken);
                            user.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if (response.isSuccessful()){
                                        User user = response.body();
                                        callback.onComplete(user);
                                    }else {
                                        // Todo handle failure better
                                        Log.w(TAG, "Connection failed, Response:" + response.errorBody());
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    // Todo handle failure better
                                    Log.e(TAG, "Error connecting to server");
                                    t.printStackTrace();
                                }
                            });
                        } else {
                            // TODO Handle error -> task.getException();
                        }
                    }
                });
    }

    public static void getCurrentUser() {
        getUser(new UserCallback<User>() {
            @Override
            public void onComplete(User object) {
                User.currentUser = object;
            }
        });
    }

    public interface UserCallback<T> {
        void onComplete(T object);
    }
}
