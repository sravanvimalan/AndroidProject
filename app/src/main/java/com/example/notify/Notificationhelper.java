package com.example.notify;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.notify.MainActivity.CHANNEL_ID;

public class Notificationhelper {

    public static void displayNotification(Context context,String title,String body) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat mNOtificationmgr = NotificationManagerCompat.from(context);
        mNOtificationmgr.notify(1,mBuilder.build());
    }
}
