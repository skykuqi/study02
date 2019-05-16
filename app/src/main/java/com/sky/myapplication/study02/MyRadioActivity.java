package com.sky.myapplication.study02;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.receiver.MyReceiver;

/**
 * 自定义动态广播
 */
public class MyRadioActivity extends AppCompatActivity {
    private MyReceiver myReceiver;
    private IntentFilter intentFilter;
    private Button my_radio_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_radio);

        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("my_first_radio");
        //注册广播
        registerReceiver(myReceiver, intentFilter);

        my_radio_button = findViewById(R.id.my_radio_button);
        my_radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("my_first_radio");
                sendBroadcast(intent);
            }
        });
    }
    //动态广播必须注销,否则会报错
    //重写onDestory()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

}
