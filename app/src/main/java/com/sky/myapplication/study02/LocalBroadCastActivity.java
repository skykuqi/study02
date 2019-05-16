package com.sky.myapplication.study02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LocalBroadCastActivity extends AppCompatActivity {
    private Button activity_broad_cast_button;
    private LocalBroadcastManager instance;
    private MyLocalBroadCast myLocalBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broad_cast);
        instance = LocalBroadcastManager.getInstance(this);

        activity_broad_cast_button = findViewById(R.id.activity_broad_cast_button);
        activity_broad_cast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("myLocalBroadCast");
                instance.sendBroadcast(intent);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("myLocalBroadCast");
                myLocalBroadCast = new MyLocalBroadCast();
                instance.registerReceiver(myLocalBroadCast, intentFilter);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance.unregisterReceiver(myLocalBroadCast);
    }

    private class MyLocalBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到了本地广播", Toast.LENGTH_SHORT).show();
        }
    }
}
