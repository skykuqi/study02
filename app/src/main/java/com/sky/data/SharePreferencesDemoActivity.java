package com.sky.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sky.myapplication.study02.R;
import com.sky.util.ShareUtil;

public class SharePreferencesDemoActivity extends AppCompatActivity {
    private EditText sqlite_text, sqlite_text_password;
    private Button sqlite_button_login,sqlite_button_save;
    private CheckBox sqlite_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences_demo);

        sqlite_text = findViewById(R.id.sqlite_text);
        sqlite_text_password = findViewById(R.id.sqlite_text_password);
        sqlite_button_login = findViewById(R.id.sqlite_button_login);
        sqlite_button_save = findViewById(R.id.sqlite_button_save);
        sqlite_check = findViewById(R.id.sqlite_check);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SharePreferencesDemoActivity.this);
        String account1 = preferences.getString("account","NO");
        String password1 = preferences.getString("password", "NO");
        String isChecked = preferences.getString("isChecked","NO");

        if(!account1.equals("NO") && !password1.equals("NO") && !isChecked.equals("NO")){
            if(isChecked.equals("true")){
                sqlite_text.setText(account1);
                sqlite_text_password.setText(password1);
            }
        }
        onClick();
    }

    private void onClick(){
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        sqlite_button_login.setOnClickListener(myOnClickListener);
        sqlite_button_save.setOnClickListener(myOnClickListener);
    }
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sqlite_button_login:
                    break;
                case R.id.sqlite_button_save:
                    String account = sqlite_text.getText().toString();
                    String password = sqlite_text_password.getText().toString();
                    Boolean checked = sqlite_check.isChecked();
                    ShareUtil.shareSaveData(SharePreferencesDemoActivity.this,account,password,checked.toString());
                    break;
                default:
                    break;
            }
        }
    }

}
