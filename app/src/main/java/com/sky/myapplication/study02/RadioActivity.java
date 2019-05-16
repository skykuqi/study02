package com.sky.myapplication.study02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RadioActivity extends AppCompatActivity {
    private NetWork netWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        netWork = new NetWork();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        //注册一个接收者
        registerReceiver(netWork, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWork);
    }

    class NetWork extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //这是一个网络状态监听
            ConnectivityManager manager = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            //            //涉及到了系统的隐私状态
            //注册清单中添加 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> 该方式为静态添加
            //android8.0 以上已经禁止了静态注册
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "接收到了网络状态", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "未接收到网络状态", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
