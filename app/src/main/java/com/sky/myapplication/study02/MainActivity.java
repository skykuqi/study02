package com.sky.myapplication.study02;

import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sky.data.DataActivity;
import com.sky.data.SharePreferencesDemoActivity;
import com.sky.listexample.ListExampleActivity;
import com.sky.permission.PermissionActivity;
import com.sky.service.MyServiceActivity;
import com.sky.sqlite.LitepalActivity;
import com.sky.sqlite.SqliteActivity;

import static com.sky.myapplication.study02.IntentActivity.RESULT_CODE;

public class MainActivity extends AppCompatActivity {

    private Button main_button_intent, main_button_fragment, main_button_radio
            , main_button_normal,main_button_local,main_button_example
            ,main_button_myradio,main_button_service,main_button_data,main_button_sqlite,main_button_share_demo
            ,main_button_litepal,main_button_permission;
    //唯一请求码
    public static final int REQUEST_CODE = 55;

    //告诉系统我有一个菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu 绑定xml    getMenuInflater()菜单加载器
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        //true显示菜单  false   隐藏菜单
        return true;
    }

    //绑定menu事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_button_intent = findViewById(R.id.main_button_intent);
        main_button_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                intent.putExtra("hello", "你好,欢迎跳转");
                ///带有回调的传值  连个参数,
                // 1.意图 2.请求码
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        main_button_fragment = findViewById(R.id.main_button_fragment);
        main_button_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        main_button_radio = findViewById(R.id.main_button_radio);
        main_button_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RadioActivity.class);
                startActivity(intent);
            }
        });

        main_button_normal = findViewById(R.id.main_button_normal);
        main_button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("MY_BROADCAST");
                intent.setComponent(new ComponentName("com.sky.receiver","com.sky.receiver.MyReceiver"));
                sendBroadcast(intent);
//                有序广播
//                sendOrderedBroadcast(intent);
            }
        });
        main_button_myradio = findViewById(R.id.main_button_myradio);
        main_button_myradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyRadioActivity.class);
                startActivity(intent);
            }
        });

        main_button_local = findViewById(R.id.main_button_local);
        main_button_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocalBroadCastActivity.class);
                startActivity(intent);

            }
        });
        main_button_example = findViewById(R.id.main_button_example);
        main_button_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListExampleActivity.class);
                startActivity(intent);
            }
        });

        main_button_service = findViewById(R.id.main_button_service);
        main_button_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyServiceActivity.class);
                startActivity(intent);
            }
        });

        main_button_data = findViewById(R.id.main_button_data);
        main_button_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataActivity.class);
                startActivity(intent);
            }
        });

        main_button_share_demo = findViewById(R.id.main_button_share_demo);
        main_button_share_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SharePreferencesDemoActivity.class);
                startActivity(intent);
            }
        });

        main_button_sqlite = findViewById(R.id.main_button_sqlite);
        main_button_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
                startActivity(intent);
            }
        });

        main_button_litepal = findViewById(R.id.main_button_litepal);
        main_button_litepal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LitepalActivity.class);
                startActivity(intent);
            }
        });

        main_button_permission = findViewById(R.id.main_button_permission);
        main_button_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //请求码和返回码都是对应请求 的
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
            String ok = data.getStringExtra("ok");
            Toast.makeText(MainActivity.this, ok, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
