package com.example.getoffthephone;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static com.example.getoffthephone.R.drawable.ic_drink_notification;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNELID = "channelid";
    public static final int ID = 9999;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dupa();
            }
        });
    }
//    @RequiresApi(api = Build.VERSION_CODES.O)


    public void dupa() {

        NotificationManager mNotificationManager;

        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(getApplicationContext(), "notify_001");
        Intent ii = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, ii, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("big text");
        bigText.setBigContentTitle("Today's Bible Verse");
        bigText.setSummaryText("Text in detail");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Your Title");
        mBuilder.setContentText("Your text");
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "Your_channel_id";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel =
                new NotificationChannel(
                    channelId,
                    "Channel human readable title", IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
        }
        mBuilder.setChannelId(channelId);

        mNotificationManager.notify(0, mBuilder.build());

    }

    public void notify2() {

        NotificationManager notificationManager = (NotificationManager)
            this.getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNELID)
            .setSmallIcon(ic_drink_notification)
            .setContentTitle("text")
            .setContentText("content text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNELID)
//                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
//                .setSmallIcon(ic_drink_notification)
////                .setLargeIcon(largeIcon(context))
//                .setContentTitle(this.getString(R.string.charging_reminder_notification_title))
//                .setContentText(this.getString(R.string.charging_reminder_notification_body))
//                .setStyle(new BigTextStyle().bigText(this.getString(R.string.charging_reminder_notification_body)))
//                .setDefaults(DEFAULT_SOUND)
//                .setContentText("blablabla")
//                .setContentIntent(getPendingIntent())
//                .addAction(action())
//                .setAutoCancel(true)
//            ;


        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(
                "channel",
                this.getString(R.string.main_notification_channel_name),
                IMPORTANCE_HIGH);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(ID, builder.build());
    }

//    private Action action() {
//        return new Action(ic_drink_notification, "test", getPendingIntent());
//    }
//
//    private PendingIntent getPendingIntent() {
//        return PendingIntent.getService(this, REQUEST_CODE, intent(), FLAG_UPDATE_CURRENT);
//    }
//
//    private Intent intent() {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setAction("action!");
//        return intent;
//    }
}
