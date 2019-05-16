package com.sky.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class BaseActivity extends Activity {
    LoginOutBroadcast loginOutBroadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.saveActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //实例化广播
        loginOutBroadcast = new LoginOutBroadcast();
        //添加拦截器
        IntentFilter intentFilter = new IntentFilter("loginOut");
        //注册广播
        registerReceiver(loginOutBroadcast,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    class LoginOutBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("温馨提示")
                    .setMessage("你确定退出吗?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityController.finishActivty();
                        }
                    });
            builder.show();
        }
    }


}
