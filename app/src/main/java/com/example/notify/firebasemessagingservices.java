package com.example.notify;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class firebasemessagingservices extends FirebaseMessagingService {

    //here comes the incoming messages
    //so override a method

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification() != null)
        {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            Notificationhelper.displayNotification(getApplicationContext(),title,body);

            Intent intent = new Intent(firebasemessagingservices.this,profileActivity.class);
            intent.putExtra("Name",title);
            intent.putExtra("Description",body);
            startActivity(intent);



        }



    }



}
