package com.tvo.htc.service;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.android.lib.util.Logger;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.NotificationEntity;
import com.tvo.htc.model.event.EventNumberNotification;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.account.AccountActivity;
import com.tvo.htc.view.main.MainActivity;

import java.util.Map;
import java.util.Random;

import static com.tvo.htc.util.AppConstants.EXTRAS_NOTIFICATION_CONTENT;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        Logger.d("onNewToken: " + token);
        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Logger.d("remoteMessage: " + remoteMessage);

        pushNotification(remoteMessage);
    }

    private void pushNotification(RemoteMessage remoteMessage) {
//        if (Utils.isAppRunningForground(getApplicationContext())) {
//            return;
//        }

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title = notification.getTitle();
        Logger.e("title: " + title);
        String body = notification.getBody();
        Logger.e("body: " + body);

        Logger.e("Content: " + remoteMessage.getData());
//
        String channelId = getString(R.string.default_notification_channel_id);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_logo_notification) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(body) // message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent;
        if (Utils.isAppRunningForeground(this)) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, AccountActivity.class);
        }
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra(AppConstants.EXTRAS_NOTIFICATION, createNotificationExtras(remoteMessage.getData()));

        Random random = new Random();
        int notification_id = random.nextInt(9999 - 1000) + 1000;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notification_id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notification_id, mBuilder.build());

        // plus unread notifications
        EventBusUtils.postEvent(new EventNumberNotification(LocalDataManager.getInstance(this).plusUnreadNotification()));
    }

    private NotificationEntity createNotificationExtras(Map<String, String> params) {
        NotificationEntity entity = new Gson().fromJson(params.get(EXTRAS_NOTIFICATION_CONTENT), NotificationEntity.class);
        return entity;
    }


    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        LocalDataManager.getInstance(getApplicationContext()).saveDeviceID(token);

        // TODO: Implement this method to send token to your app server.
    }
}
