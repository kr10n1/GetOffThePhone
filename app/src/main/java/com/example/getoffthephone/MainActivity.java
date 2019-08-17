package com.example.getoffthephone;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Action;
import androidx.core.app.NotificationCompat.BigTextStyle;
import androidx.core.content.ContextCompat;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;
import static com.example.getoffthephone.R.drawable.ic_drink_notification;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNELID = "channelid";
    public static final int ID = 9999;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button b = (Button) findViewById(R.id.button);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notify(View view) {

        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNELID)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSmallIcon(ic_drink_notification)
//                .setLargeIcon(largeIcon(context))
                .setContentTitle(this.getString(R.string.charging_reminder_notification_title))
                .setContentText(this.getString(R.string.charging_reminder_notification_body))
                .setStyle(new BigTextStyle().bigText(this.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(DEFAULT_SOUND)
                .setContentText("blablabla")
                .setContentIntent(getPendingIntent())
                .addAction(action())
                .setAutoCancel(true);


        NotificationChannel mChannel = new NotificationChannel(
                "channel",
                this.getString(R.string.main_notification_channel_name),
                IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(mChannel);
        notificationManager.notify(ID, builder.build());
    }

    private Action action() {
        return new Action(ic_drink_notification, "test", getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        return PendingIntent.getService(this, REQUEST_CODE, intent(), FLAG_UPDATE_CURRENT);
    }

    private Intent intent() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("action!");
        return intent;
    }
}
