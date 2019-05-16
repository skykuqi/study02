package com.sky.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IoActivity extends BaseActivity {
    private EditText io_text;
    private TextView io_text_read;
    private Button io_button,io_button_read,io_button_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);

        io_text = findViewById(R.id.io_text);
        io_button = findViewById(R.id.io_button);
        io_text_read = findViewById(R.id.io_text_read);
        io_button_read = findViewById(R.id.io_button_read);
        io_button_exit = findViewById(R.id.io_button_exit);

        io_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(io_text.getText().toString());
            }
        });
        io_button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = getData();
                io_text_read.setText(data);
            }
        });

        io_button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("loginOut");
                sendBroadcast(intent);
            }
        });



    }

    private void saveData(String content) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter writer = null;
        try {
            //设定输入流的模式
            fileOutputStream = openFileOutput("test", MODE_PRIVATE);
            //声明流
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            //实例化画笔
            writer = new BufferedWriter(outputStreamWriter);
            //写入
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getData() {
        FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        try {
            //输出流,获取文件的标识
            fileInputStream = openFileInput("test");
            //声明流
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            //相当于画板
            reader = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
