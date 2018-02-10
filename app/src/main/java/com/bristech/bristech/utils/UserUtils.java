package com.bristech.bristech.utils;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bristech.bristech.activities.LoginBasicActivity;
import com.bristech.bristech.entities.Account;
import com.bristech.bristech.services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserUtils {


    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = builder.build();
    //Retrofit populates interface
    static UserService userService = retrofit.create(UserService.class);

    /**
     * Callback interface
     */
    public interface UserUtilsCallback{
        void getToken(String token);
    }


    /**
     * Authorises the user and uses the callback to return the JWT token
     * @param context app context
     * @param username user username
     * @param password user password
     * @param callback login callback
     */
    public static void login(final Context context, String username, String password, final UserUtilsCallback callback){
        Account account = new Account(username, password);
        Call<Void> call = userService.login(account);
        System.out.println(call.toString());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    callback.getToken(response.headers().get("token"));
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Wrong cred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Simply starts the login activity
     * @param context app context
     */
    public static final void isLoggedIn(Context context){
        //TODO check if token exists
        //TODO check if token is valid

        Intent startLogin = new Intent(context, LoginBasicActivity.class);
        context.startActivity(startLogin);
    }

}
