package com.sky.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接收到了广播");
        Toast.makeText(context, "自定义广播", Toast.LENGTH_SHORT).show();
        if ("my_first_radio".equals(intent.getAction())) {
            Toast.makeText(context, "自定义广播", Toast.LENGTH_SHORT).show();
        }
        if ("MY_BROADCAST".equals(intent.getAction())){
            Toast.makeText(context,"自定义静态广播",Toast.LENGTH_SHORT).show();
        }
    }
}
