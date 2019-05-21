package com.sky.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sky.myapplication.study02.R;

import java.security.Permission;

public class PermissionActivity extends AppCompatActivity {
    private Button permission_button_call;
    private EditText permission_edit_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        permission_edit_phone = findViewById(R.id.permission_edit_phone);
        permission_button_call = findViewById(R.id.permission_button_call);

        permission_button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("10086"));
                if((ActivityCompat.checkSelfPermission(PermissionActivity.this
                        ,Manifest.permission.CALL_PHONE) != Permission.)){
                    ActivityCompat.requestPermissions(PermissionActivity.this,new String[]{
                            Manifest.permission.CALL_PHONE
                    },1);
                }
                startActivity(intent);*/
                }
        });
    }
}
