package com.example.kcanel.alarma.MessageCloud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.kcanel.alarma.MainActivity;
import com.example.kcanel.alarma.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by kcanel on 28/08/2018.
 */

public class MyFirebaseMessageService extends FirebaseMessagingService{

    /*Aqui llegan los mensajes perrosw >:v */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        if(remoteMessage.getNotification() != null){
            notificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        if(remoteMessage.getData() != null){
            Log.d("ACA" , "" + remoteMessage.getData());
        }

    }

    private void notificacion(String title, String body) {
        // pantalla que va abrir y mostrar las fotos
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        // sonido de notificacion
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //creacion de notificacion cerrandola con tap y abriendo pantalla pa muestra de fotos
        NotificationCompat.Builder  notificacionBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificacionBuilder.build());
    }
}
