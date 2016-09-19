package com.blueeagle.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init view
        Button btnSendSimpleNoti = (Button) findViewById(R.id.btnSendNotiOff);
        Button btnSendExpandedNoti = (Button) findViewById(R.id.btnSendNotiOn);
        Button btnSendProgressNoti = (Button) findViewById(R.id.btnSendProgressNoti);
        Button btnSendCustomNoti = (Button) findViewById(R.id.btnSendCustomNoti);
        Button btnRemoveNoti = (Button) findViewById(R.id.btnRemoveNoti);

        // Button set on click listener
        btnSendSimpleNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleNoti();
            }
        });

        btnSendExpandedNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExpandedNoti();
            }
        });

        btnSendProgressNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressNoti();
            }
        });

        btnSendCustomNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomLayoutNoti();
            }
        });

        btnRemoveNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeNoti();
            }
        });

        // Create NotificationManager object
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showCustomLayoutNoti() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_layout_notification);

        // Set notification content
        remoteViews.setTextViewText(R.id.tvContentTitle, "Notification from Sieu Phuot");
        remoteViews.setTextViewText(R.id.tvContentText, "Hahaha... Let do that!");
        remoteViews.setImageViewResource(R.id.imvLargeIcon, R.drawable.avatar1);

        // Create pending intent for action on notification
        // Reply message
        Intent replyIntent = new Intent(MainActivity.this, ReplyActivity.class);
        PendingIntent piReply = PendingIntent.getActivity(this, 0, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Discard message
        Intent discardIntent = new Intent(MainActivity.this, ResultActivity.class);
        PendingIntent piDiscard = PendingIntent.getActivity(this, 0, discardIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.btnReply, piReply);
        remoteViews.setOnClickPendingIntent(R.id.btnDiscard, piDiscard);

        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_heart)
                .setCustomBigContentView(remoteViews)
                .setPriority(2);

        notificationManager.notify(4, notiBuilder.build());
    }

    public void showSimpleNoti() {

        // Tao mot notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        // Set cac thuoc tinh
        mBuilder.setContentTitle("Simple notification");
        mBuilder.setContentText("Simple notification. Click me to open activity.");
        mBuilder.setNumber(2);
        mBuilder.setSmallIcon(R.drawable.ic_notification);
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

        Intent resultIntent = new Intent(this, ResultActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent pi = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pi1 = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pi);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

    public void showExpandedNoti() {

        // Create new notification
        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Expanded notification")
                .setContentText("Expanded notification content text")
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

        // Using inboxstyle to show notification
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(notiBuilder);
        inboxStyle.setBigContentTitle("Expanded notification - Inbox style");

        for (int i = 1; i <= 6; i++) {
            inboxStyle.addLine(i + " We don't talk anymore");
        }

        // Set style for notification
        notiBuilder.setStyle(inboxStyle);
        notiBuilder.setAutoCancel(true);

        Intent notiIntent = new Intent(this, ResultActivity2.class);
        notiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi =
                PendingIntent.getActivity(
                        this,
                        0,
                        notiIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        notiBuilder.setContentIntent(pi);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notiBuilder.build());
    }

    public void showProgressNoti() {

        // Pending intent for action
        Intent intent = new Intent(this, ResultActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create large icon
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification);

        // Create new notification
        final NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_heart)
                .setLargeIcon(largeIcon)
                .setContentTitle("Progress notification")
                .setContentText("Downloading PhuotDi App")
                .setAutoCancel(true)
                .addAction(R.drawable.ic_heart, "Cancel", pi)
                .addAction(R.drawable.ic_hangout, "Stop", pi);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i = i + 10) {
                    notiBuilder.setProgress(100, i, false);
                    notificationManager.notify(3, notiBuilder.build());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                notiBuilder.setProgress(100, 100, false);
                notiBuilder.setContentText("Download conplete");
                notificationManager.notify(3, notiBuilder.build());
            }
        }).start();
    }

    public void removeNoti() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //notificationManager.cancel(1);
        notificationManager.cancelAll();
    }
}
