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

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://bristech-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    public static User user;

    //Retrofit populates interface
    private static Retrofit retrofit = builder.build();
    private static UserService userService = retrofit.create(UserService.class);


    public static void getUser(){
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
                                        if (user != null) {
                                            Log.i(TAG, "Successfully requested user:" + user.getEmail());
                                            UserUtils.user = user;
                                        }
                                    }else {
                                        Log.w(TAG, "Connection failed, Response:" + response.errorBody());
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.e(TAG, "Error connecting to server");
                                    t.printStackTrace();
                                }
                            });
                        } else {
                            // Handle error -> task.getException();
                        }
                    }
                });
    }


}
