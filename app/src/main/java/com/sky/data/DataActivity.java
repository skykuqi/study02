package com.sky.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.myapplication.study02.R;

public class DataActivity extends AppCompatActivity {
    private Button data_button_io,data_button_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        data_button_io = findViewById(R.id.data_button_io);
        data_button_io.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this,IoActivity.class);
                startActivity(intent);
            }
        });

        data_button_share = findViewById(R.id.data_button_share);
        data_button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this,ShareActivity.class);
                startActivity(intent);
            }
        });
    }
}
