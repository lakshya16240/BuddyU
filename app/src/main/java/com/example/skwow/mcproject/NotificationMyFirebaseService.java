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
        if(User.currentUser == null)
            return;
        String type = remoteMessage.getData().get("title");
        String sender = remoteMessage.getData().get("sender");
        String msg = remoteMessage.getData().get("body");



        if(User.currentUser.interestedIn(type) || type.equals("default"))
        {
            Notification n = new Notification(sender, type, msg);
            User.currentUser.addNotification(n);

            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pi = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
            notiBuilder.setContentTitle(type.equals("default")?"New event":type);
            notiBuilder.setContentText( sender+ " " + msg);
            notiBuilder.setAutoCancel(true);
            notiBuilder.setSmallIcon(R.drawable.ic_group_black_24dp);
            notiBuilder.setContentIntent(pi);
            NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notiManager.notify(0,notiBuilder.build());
        }
    }
}
