package com.example.skwow.mcproject;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.example.skwow.mcproject.MainActivity.TAG;

public class NotificationMyFirebaseInstanceIDService extends FirebaseInstanceIdService
{
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase Service", "Refreshed token: " + refreshedToken);

        //sendRegistrationToServer(refreshedToken);
    }

}
