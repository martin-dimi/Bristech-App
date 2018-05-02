package com.bristech.bristech.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bristech.bristech.R;
import com.bristech.bristech.activities.FeedbackActivity;
import com.bristech.bristech.activities.MainActivity;
import com.bristech.bristech.entities.Event;
import com.bristech.bristech.entities.User;
import com.bristech.bristech.utils.UserUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = GeofenceBroadcastReceiver.class.getSimpleName();
    public static final String CHANNEL_ID = "notify_001";
    public static final String CHANNEL_NAME = "Bristech-channel";

    private Event mEvent;
    /***
     * Handles the Broadcast message sent when the Geofence Transition is triggered
     * Make the call to the SERVER here.
     *
     * @param context cxt
     * @param intent int
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the Geofence Event from the Intent sent through

        Log.i(TAG, "GEOFENCE CALL");

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.e(TAG, String.format("Error code : %d", geofencingEvent.getErrorCode()));
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        Log.i(TAG, " CALL");


        // Check which transition type has triggered this event
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // Send the notification
            // TODO Probably the call to the server should be here
            Log.i(TAG, "Sending notif");
            sendNotification(context);

        } else {
            // Log the error.
            Log.e(TAG, String.format("Unknown transition : %d", geofenceTransition));
        }

    }

    private void userAttendsEvents(final Context context){
        if(mEvent == null && User.currentUser == null)
            return;

        UserUtils.attendEvent(mEvent.getId(), User.currentUser.getEmail(), new UserUtils.UserCallback<Boolean>() {
            @Override
            public void onComplete(final Boolean isGoing) {
                UserUtils.getUser(new UserUtils.UserCallback<User>() {
                    @Override
                    public void onComplete(User uobject) {
                        Log.i(TAG, "User attending event call");
                    }
                });
            }
        });
    }


    /**
     * Posts a notification in the notification bar when a transition is detected
     * If the user clicks the notification, control goes to the MainActivity
     *
     * @param context The calling context for building a task stack
     */
    private void sendNotification(Context context) {
        Intent notificationIntent = new Intent(context, FeedbackActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        Log.i(TAG, "Showing Notification");

        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        // Get a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.bristech_banner)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);

        // Get an instance of the Notification manager
        manager.notify(0, builder.build());

    }

}
