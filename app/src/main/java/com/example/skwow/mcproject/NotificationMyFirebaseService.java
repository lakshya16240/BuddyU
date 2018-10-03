package com.example.skwow.mcproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationMyFirebaseService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        System.out.println("___________________________________________**********************");
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
        notiBuilder.setContentTitle(remoteMessage.getData().get("title"));
        System.out.println(remoteMessage.getData());
        notiBuilder.setContentText(remoteMessage.getData().get("body"));
        notiBuilder.setAutoCancel(true);
        notiBuilder.setSmallIcon(R.drawable.ic_group_black_24dp);
        notiBuilder.setContentIntent(pi);
        NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notiManager.notify(0,notiBuilder.build());
    }


}
