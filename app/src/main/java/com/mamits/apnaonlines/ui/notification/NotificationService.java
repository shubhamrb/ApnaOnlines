package com.mamits.apnaonlines.ui.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.ui.activity.MainActivity;

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG = "NotificationService";
    String title, message, action;
    private String CHANNEL_ID;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            action = remoteMessage.getData().get("type");
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            try {
                title = remoteMessage.getNotification().getTitle(); //get title
                message = remoteMessage.getNotification().getBody(); //get message
                if (title == null) {
                    title = "";
                }
                if (message == null) {
                    message = "";
                }
                if (action == null) {
                    action = "";
                }
                Log.d("title ", title);
                Log.d("message ", message);
                Log.d("action ", action);
                notificationManager(title, message, action);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void notificationManager(String title, String notificationMsg, String action) {
        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e("log : ", "if");
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.WHITE);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);


            Intent resultIntent = new Intent(this, MainActivity.class);
            resultIntent.putExtra("action", action);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setColor(ContextCompat.getColor(this, R.color.sky_blue))
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMsg))
                    .setContentText(notificationMsg)
                    .setAutoCancel(true);
            builder.setContentIntent(resultPendingIntent);
            notificationManager.notify(NOTIFICATION_ID, builder.build());

        } else {
            Intent resultIntent = new Intent(this, MainActivity.class);
            resultIntent.putExtra("action", action);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);
            Notification n = new Notification.Builder(this)
                    .setContentTitle(title)
                    .setStyle(new Notification.BigTextStyle().bigText(notificationMsg))
                    .setContentText(notificationMsg)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setSmallIcon(R.drawable.logo)
                    .build();
            NotificationManager notificationManager1 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager1.notify(NOTIFICATION_ID, n);

        }
    }
}
