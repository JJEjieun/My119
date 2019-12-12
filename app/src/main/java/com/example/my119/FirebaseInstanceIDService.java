package com.example.my119;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class FirebaseInstanceIDService extends FirebaseMessagingService {
    private  static final  String TAG = "FCM";
    @Override
    public void onNewToken(String s){
        super.onNewToken(s);
        Log.e(TAG,"FirevaseInstanceIDService : "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        if(remoteMessage != null && remoteMessage.getData().size()>0){
            Log.d("Firebase","Message data payload: "+remoteMessage.getData());

            PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP,"My:wakeLock");
            wakeLock.acquire();



            sendNotification(remoteMessage);
        }else{

            sendNotification(remoteMessage);
        }
    }

    private void sendNotification(RemoteMessage remoteMessage){
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            String channel = "channel";
            String channel_nm = "channel name";

            NotificationManager notichannel = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channelMessage = new NotificationChannel(channel, channel_nm, NotificationManager.IMPORTANCE_DEFAULT);
            channelMessage.setDescription("this is channel");
            channelMessage.enableLights(true);
            channelMessage.enableVibration(true);
            channelMessage.setShowBadge(false);
            channelMessage.setVibrationPattern(new long[]{100, 200, 100, 200});
            notichannel.createNotificationChannel(channelMessage);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channel)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title).setContentText(message)
                    .setChannelId(channel).setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(9999, notificationBuilder.build());
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title).setContentText(message)
                    .setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(9999, notificationBuilder.build());

        }
    }
}
