package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;

import java.util.Random;

public class NotificationActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        findViewById(R.id.button_show_notification).setOnClickListener(this);
        findViewById(R.id.button_show_multi_notification).setOnClickListener(this);
        findViewById(R.id.button_remove_notification).setOnClickListener(this);
        findViewById(R.id.button_remove_all_notification).setOnClickListener(this);

        IntentFilter filter = new IntentFilter("com.example.notify.dismissIntent");
        registerReceiver(receiver,filter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_show_notification) {
            showNotification(HELLO_ID);
        } else if (id == R.id.button_show_multi_notification) {
            showNotification(new Random().nextInt(10));
        } else if (id == R.id.button_remove_notification) {
            removeNotification(HELLO_ID);
        } else if (id == R.id.button_remove_all_notification) {
            removeNotification(-1);
        }
    }

    public void removeNotification(int id) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (id <= 0) {
            manager.cancelAll();
        } else {
            manager.cancel(id);
        }
    }

    private static final int HELLO_ID = 1;

    int count = 0;

    private void showNotification(int id) {
        count++;
        final NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        //define action
        Intent dismissIntent = new Intent("com.example.notify.dismissIntent");
        dismissIntent.putExtra("notify_id", id);
        PendingIntent dismissPendingIntent =
                PendingIntent.getBroadcast(this, 1, dismissIntent, 0);

        NotificationCompat.Action dismiss = new
                NotificationCompat.Action(R.mipmap.ic_launcher,"Dismiss",dismissPendingIntent);

        Intent intent = new Intent(this, NotifyResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("My Notification " + id)
                .setContentText("Hello Notify " + count)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
                ))
                .addAction(dismiss)
                ;


//                .setStyle(new NotificationCompat.BigTextStyle().bigText("java - Notifications Builder in android 2.3 - Stack Overflow\n" +
//                        "stackoverflow.com/.../notifications-builder-in-android-2-3\n" +
//                        "Dịch trang này\n" +
//                        "19-02-2014 - Pretty new in android here :). I have a notification builder which works ... Implement your Notification like. Notification noti = new ..."))

        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = 100;
                int progress = 1;
                while (progress < max) {

                    progress += 10;
                    builder.setProgress(max, progress, false);
                    manager.notify(HELLO_ID,builder.build());
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Notification notification = builder.build();


        manager.notify(id, notification);
    }


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra("notify_id", -1);
            removeNotification(id);
        }
    };
}
