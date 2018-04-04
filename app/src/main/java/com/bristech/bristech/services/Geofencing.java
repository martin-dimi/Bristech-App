package com.bristech.bristech.services;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class Geofencing implements OnSuccessListener<Void>, OnFailureListener {

    // Constants
    private static final String TAG = Geofencing.class.getSimpleName();
    private static final float GEOFENCE_RADIUS = 50; // 50 meters
    private static final long GEOFENCE_TIMEOUT = 7 * 24 * 60 * 60 * 1000; // 1 week
    private static final String ENGINE_SHED_ID = "157457";
    private static final double ENGINE_SHED_LAT = 51.448916;
    private static final double ENGINE_SHED_LNG = -2.583420;

    private Geofence mGeofence;
    private PendingIntent mGeofencePendingIntent;
    private Context mContext;


    public Geofencing(Context context) {
        mContext = context;
        mGeofencePendingIntent = null;

        mGeofence = new Geofence.Builder()
                .setRequestId(ENGINE_SHED_ID)
                .setExpirationDuration(GEOFENCE_TIMEOUT)
                .setCircularRegion(ENGINE_SHED_LAT, ENGINE_SHED_LNG, GEOFENCE_RADIUS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                .build();
    }

    public void registerGeofence() {
        // Check that the API client is connected and that the Geofence is valid
        if (mGeofence == null) {
            return;
        }
        try {

            LocationServices.getGeofencingClient(mContext)
                    .addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                    .addOnSuccessListener(this)
                    .addOnFailureListener(this);

        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
            Log.e(TAG, securityException.getMessage());
        }
    }

    /***
     * Creates a GeofencingRequest object using the mGeofenceList ArrayList of Geofences
     * Used by {@code #registerGeofences}
     *
     * @return the GeofencingRequest object
     */
    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(mGeofence);
        return builder.build();
    }

    /***
     * Creates a PendingIntent object using the GeofenceTransitionsIntentService class
     * Used by {@code #registerGeofences}
     *
     * @return the PendingIntent object
     */
    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(mContext, GeofenceBroadcastReceiver.class);
        mGeofencePendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }

    @Override
    public void onSuccess(Void aVoid) {
        Log.i(TAG, "Successfully added geofence!");
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Couldn't add geofence!");
        Log.e(TAG, e.getMessage());
    }
}
