package com.example.cubelearner.processing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
 * If it is the notification's time, prints it on the screen.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.createNotification();
    }

}
