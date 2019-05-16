package com.sky.listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

import java.util.Random;

public class ListExampleActivity extends AppCompatActivity {

    private TextView title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_example);
        title = findViewById(R.id.layout_fragment_example_title);
        content = findViewById(R.id.layout_fragment_example_content);
    }
    public void setTitleText(String titleText){
        title.setText(titleText);
    }
    public  void setContentText(String contentText){
        content.setText(contentText);
    }
}
