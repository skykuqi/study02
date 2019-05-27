package com.sky.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetWorkActivity extends AppCompatActivity {
    private TextView network_text, network_text_frame, network_text_post;
    private Button network_button, network_button_frame, network_button_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);
        {
            network_text = findViewById(R.id.network_text);
            network_button = findViewById(R.id.network_button);
            network_button_frame = findViewById(R.id.network_button_frame);
            network_text_frame = findViewById(R.id.network_text_frame);
            network_text_post = findViewById(R.id.network_text_post);
            network_button_post = findViewById(R.id.network_button_post);
        }
        network_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttpMessage();
            }
        });
        network_button_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnNewThread();
            }
        });
    }

    //无法在主线程中使用网络请求

    /**
     * 获取网络请求数据
     * 无法在主线程中使用网络请求,也无法更新界面
     */
    private void getHttpMessage() {

        //子线程的一个简单的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //原声的提供类 初始化一个网络连接
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                StringBuilder stringBuilder = null;
                try {
                    URL url = new URL("http://open.drea.cc/chat/get?keyWord=125&userName=type%3Dbbs");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    //请求方式
                    httpURLConnection.setRequestMethod("GET");
                    //设置请求的时间
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setConnectTimeout(8000);
                    //获取输入流
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    //请求完成,获取数据
                    String len = null;
                    stringBuilder = new StringBuilder();
                    while ((len = bufferedReader.readLine()) != null) {
                        stringBuilder.append(len);
                    }
                    System.out.println(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        //关闭网络连接
                        httpURLConnection.disconnect();
                    }
                }
                if (stringBuilder != null) {
                    updateText(stringBuilder.toString());
                } else {
                    updateText("未获取到数据");
                }
            }
        }).start();
    }

    /**
     * 在主线程中赋值数据
     */
    private void updateText(final String str) {
        //
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                network_text.setText(str);
            }
        });
    }

    /**
     * 给指定的text更新数据
     * @param tv    TextView
     * @param str   所需更新的数据内容
     */
    private void updateText(final TextView tv, final String str) {
        //
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(str);
            }
        });
    }

    /**
     * 获取网络请求数据
     * 无法在主线程中使用网络请求,也无法更新界面
     */
    private void getHttpMessagePost() {

        //子线程的一个简单的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //原声的提供类 初始化一个网络连接
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                StringBuilder stringBuilder = null;
                try {
                    URL url = new URL("http://open.drea.cc/chat/get?keyWord=125&userName=type%3Dbbs");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    //请求方式
                    httpURLConnection.setRequestMethod("POST");

                    //设置请求 写入参数
//                    DataOutputStream dataOutputStream = new DataOutputStream();
//                    dataOutputStream.write();

                    //设置请求的时间
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setConnectTimeout(8000);

                    //获取输入流 获取数据
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    //请求完成,获取数据
                    String len = null;
                    stringBuilder = new StringBuilder();
                    while ((len = bufferedReader.readLine()) != null) {
                        stringBuilder.append(len);
                    }
                    System.out.println(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        //关闭网络连接
                        httpURLConnection.disconnect();
                    }
                }
                if (stringBuilder != null) {
                    updateText(stringBuilder.toString());
                } else {
                    updateText("未获取到数据");
                }
            }
        }).start();
    }

    /**
     * OkHttp框架
     */
    private void getOkHttpMessagePost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String data = null;
        Request request = new Request.Builder()
//                .method()       //默认为get请求
                .url("http://open.drea.cc/chat/get?keyWord=125&userName=type%3Dbbs").build();
        try {
            //把网络连接类和request关联
            Response response = okHttpClient.newCall(request).execute();
            //拿到了回调数据
            ResponseBody body = response.body();
            data = body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data != null){
            updateText(network_text_frame,data);
        }else {
            updateText(network_text_frame,"未获取到数据");
        }
    }

    /**
     * 异步执行
     */
    private void runOnNewThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getOkHttpMessagePost();
            }
        }).start();
    }
}
