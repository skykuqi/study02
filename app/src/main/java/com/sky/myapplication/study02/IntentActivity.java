package com.sky.myapplication.study02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity {
    private Button intent_button_intent;
    //唯一返回码
    public static final int RESULT_CODE = 666;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        final Intent intent = getIntent();
        String hello = intent.getStringExtra("hello");
        Toast.makeText(this,hello,Toast.LENGTH_SHORT).show();
        intent.putExtra("ok","我收到了");
        intent_button_intent = findViewById(R.id.intent_button_intent);
        intent_button_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在匿名内部类中使用外部变量,外部变量需要声明为final
                //                //1.返回码         2.意图,返回的数据
                setResult(RESULT_CODE,intent);
                //当前activity的销毁
                finish();
            }
        });

    }
}
