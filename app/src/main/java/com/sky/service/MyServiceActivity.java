package com.sky.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.myapplication.study02.R;

public class MyServiceActivity extends AppCompatActivity {
    private Button myservice_button_start, myservice_button_close, myservice_button_start_music, myservice_button_pause_music;
    private Intent intent;
    private MusicBind musicBind;
    private ServiceConnection serviceConnection;
    private boolean isUnbindService = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        myservice_button_start = findViewById(R.id.myservice_button_start);
        myservice_button_close = findViewById(R.id.myservice_button_close);
        myservice_button_start_music = findViewById(R.id.myservice_button_start_music);
        myservice_button_pause_music = findViewById(R.id.myservice_button_pause_music);
        //绑定跳转
        onClick();
        //意图,跳转(传值)  跳转Activity  发送广播  启动服务
        intent = new Intent(this, MyService.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ///创建一个联系,关联服务和事件
//                onStartMusic();
                //获取Bind操作对象
                musicBind = (MusicBind) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        /**
         * 绑定bind   BIND_AUTO_CREATE 只要应用开启,打开当前Activty,服务就被开启
         */
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void onClick() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        myservice_button_start.setOnClickListener(myOnClickListener);
        myservice_button_close.setOnClickListener(myOnClickListener);
        myservice_button_start_music.setOnClickListener(myOnClickListener);
        myservice_button_pause_music.setOnClickListener(myOnClickListener);
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myservice_button_start:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        //android8.0以上通过startForegroundService启动service
                        startForegroundService(intent);
                    } else {
                        startService(intent);
                    }
                    break;
                case R.id.myservice_button_close:
                    //关闭音乐
                    musicBind.onPauseMusic();
                    //解绑操作
                    if(isUnbindService){
                        unbindService(serviceConnection);
                        isUnbindService = false;
                    }
                    //关闭一个服务
                    stopService(intent);
                    break;
                case R.id.myservice_button_start_music:
                    musicBind.onStartMusic(MyServiceActivity.this);
                    //打开音乐
                    break;
                case R.id.myservice_button_pause_music:
                    //暂停音乐
                    musicBind.onPauseMusic();
                    break;
                default:
                    break;
            }
        }
    }
}
