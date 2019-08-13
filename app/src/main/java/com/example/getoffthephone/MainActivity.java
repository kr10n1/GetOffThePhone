package com.example.getoffthephone;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNELID = "channelid";
    public static final int ID = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button b = (Button) findViewById(R.id.button);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notify(View view) {

        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNELID)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
//                .setLargeIcon(largeIcon(context))
                .setContentTitle(this.getString(R.string.charging_reminder_notification_title))
                .setContentText(this.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(this.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentText("blablabla")
                .setAutoCancel(true);


        NotificationChannel mChannel = new NotificationChannel(
                "",
                this.getString(R.string.main_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(mChannel);
        notificationManager.notify(ID, builder.build());
    }
}
