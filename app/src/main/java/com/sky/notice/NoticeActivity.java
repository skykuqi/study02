package com.sky.notice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.myapplication.study02.MainActivity;
import com.sky.myapplication.study02.R;

import java.io.File;

public class NoticeActivity extends AppCompatActivity {
    private Button notice_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        {
            notice_button = findViewById(R.id.notice_button);
        }
        notice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Notification.Builder builder = new Notification.Builder(NoticeActivity.this)
                        .setContentIntent(PendingIntent.getActivity(this, 0, intent1, 0)) // 设置PendingIntent
                        .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText("正在播放音乐") // 设置上下文内容
                        .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    //修改安卓8.1以上系统报错
                    NotificationChannel notificationChannel = new NotificationChannel("sky123", "skyNotification", NotificationManager.IMPORTANCE_MIN);
                    notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
                    notificationChannel.setShowBadge(false);//是否显示角标
                    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(notificationChannel);
                    builder.setChannelId("sky123");
                    Notification notification = builder.build(); // 获取构建好的Notification
                    notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
                    //启动通知
                    builder.startForeground(1, notification);
                }*/
                Intent intent = new Intent(NoticeActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NoticeActivity.this,0,intent,0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(NoticeActivity.this)
                        .setContentTitle("来自星星的你")
                        /* .setContentText("新闻十三分新闻十三分新闻十三分新闻十三分新闻十三分" +
                                 "新闻十三分新闻十三分新闻十三分新闻十三分新闻十三分")    //默认无法显示过长的text*/
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("新闻十三分新闻十三分新闻十三分新闻十三分新闻十三分" +
                                "新闻十三分新闻十三分新闻十三分新闻十三分新闻十三分"))       //可以显示所有的通知
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                BitmapFactory.decodeResource(getResources(), R.drawable.github) //这只大图片显示
                        ))
//                        .setAutoCancel(true)        //点击关闭
//                        .setSound(Uri.fromFile(new File("raw/play.mp3")))       //寻找通知音乐
                        .setVibrate(new long[]{
                                0, 1000, 0, 1000
                        })
                        .setContentIntent(pendingIntent)
                        .build();
                manager.notify(8, notification);
            }
        });
    }
}
