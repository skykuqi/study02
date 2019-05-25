package com.sky.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sky.myapplication.study02.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView web_view;
    private Button web_button;
    private TextView web_text_title;
    private ProgressBar web_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        {
            web_view = findViewById(R.id.web_view);
            web_button = findViewById(R.id.web_button);
            web_text_title = findViewById(R.id.web_text_title);
            web_progress = findViewById(R.id.web_progress);
        }
        //首先需要赋予网络权限
        //打开网页是空白,如下设置
        WebSettings settings = web_view.getSettings();
        //打开Js支持
        settings.setJavaScriptEnabled(true);
        //打开缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //在本地跳转,而不是调用外部浏览器打开
        web_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //拦截了当前的弹窗
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取拦截当前web页面的标题
                web_text_title.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //监听当前页面加载的进度
                super.onProgressChanged(view, newProgress);


                if(newProgress == 100){
                    web_progress.setVisibility(View.GONE);
                }else {
                    if(web_progress.getVisibility() == View.GONE){
                        web_progress.setVisibility(View.VISIBLE);
                    }
                    web_progress.setProgress(newProgress);
                }
            }
        });
        //监听了当前页面加载的状况
        web_view.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定页面结束的操作
                super.onPageFinished(view, url);

            }
        });
        web_view.loadUrl("https://www.baidu.com");
        web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //会跳转至浏览器外部
                web_view.loadUrl("https://www.163.com");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //修改系统默认设置,使返回键对网页有效
        if(keyCode == KeyEvent.KEYCODE_BACK && web_view.canGoBack()){
            web_view.goBack();
            return true;
        }else {
            Toast.makeText(WebViewActivity.this,"无法返回",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

