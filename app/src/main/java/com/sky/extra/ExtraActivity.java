package com.sky.extra;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.sky.myapplication.study02.R;

public class ExtraActivity extends AppCompatActivity {
    private Toolbar extra_tool;
    private ActionBar actionBar;
    private Button extra_button_myActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        {
            extra_tool = findViewById(R.id.extra_tool);
            extra_button_myActionBar = findViewById(R.id.extra_button_myActionBar);
        }
        //赋值ction Bar
        setSupportActionBar(extra_tool);

        //获取ActionBar
       /* android.app.ActionBar actionBar = getActionBar();
        try {
            System.out.println(actionBar.getThemedContext());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        extra_button_myActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraActivity.this, MyActionBarActivity.class);
                startActivity(intent);
            }
        });

    }
    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate('菜单的布局');
        return super.onCreateOptionsMenu(menu);
    }


}
