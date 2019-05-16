package com.sky.listexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ListExampleContentActivity extends AppCompatActivity {
    private TextView layout_fragment_example_title,layout_fragment_example_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_example);

        layout_fragment_example_title = findViewById(R.id.layout_fragment_example_title);
        layout_fragment_example_content = findViewById(R.id.layout_fragment_example_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        layout_fragment_example_content.setText(content);
        layout_fragment_example_title.setText(title);
    }
}
