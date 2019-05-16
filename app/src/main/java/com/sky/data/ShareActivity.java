package com.sky.data;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sky.myapplication.study02.R;

public class ShareActivity extends BaseActivity {
    private EditText share_text, share_text_password;
    private TextView share_text_read, share_text_read_password;
    private Button share_button, share_button_read,share_button_login;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        share_text = findViewById(R.id.share_text);
        share_text_read = findViewById(R.id.share_text_read);
        share_button = findViewById(R.id.share_button);
        share_button_read = findViewById(R.id.share_button_read);
        share_text_password = findViewById(R.id.share_text_password);
        share_text_read_password = findViewById(R.id.share_text_read_password);
        share_button_login = findViewById(R.id.share_button_login);

        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = share_text.getText().toString();
                String password = share_text_password.getText().toString();

                editor = getSharedPreferences("com.sky.test", MODE_PRIVATE).edit();
                editor.putString("account", account);
                editor.putString("password", password);
                editor.apply();
            }
        });

        share_button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("com.sky.test", MODE_PRIVATE);
                String account = sharedPreferences.getString("account", "你好");
                String password = sharedPreferences.getString("password", "123456");
                share_text_read.setText(account);
                share_text_read_password.setText(password);
            }
        });

        share_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = share_text.getText().toString();
                String password = share_text_password.getText().toString();
                if(TextUtils.isEmpty(account)){
                    Toast.makeText(ShareActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(ShareActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    sharedPreferences = getSharedPreferences("com.sky.test", MODE_PRIVATE);
                    String getaccount = sharedPreferences.getString("account", "你好");
                    String getpassword = sharedPreferences.getString("password", "123456");
                    share_text_read.setText(account);
                    share_text_read_password.setText(password);
                    if(account.equals(getaccount) && password.equals(getpassword)){
                        Intent intent = new Intent(ShareActivity.this, IoActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(ShareActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
