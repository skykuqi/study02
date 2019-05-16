package com.sky.myapplication.study02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.fragment.LeftFragment;
import com.sky.fragment.RightFragment;

public class FragmentActivity extends AppCompatActivity {
    public Button fragment_button_left, fragment_button_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        fragment_button_left = findViewById(R.id.fragment_button_left);
        fragment_button_right = findViewById(R.id.fragment_button_right);
        onClick();

    }

    class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_button_left:
                    LeftFragment leftFragment = new LeftFragment();
                    replaceFragmentManager(leftFragment);
                    break;
                case R.id.fragment_button_right:
                    RightFragment rightFragment = new RightFragment();
                    replaceFragmentManager(rightFragment);
                    break;
            }
        }
    }

    public void replaceFragmentManager(Fragment fragment) {
        //事务管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_linear_fragment_left, fragment);
        fragmentTransaction.commit();
    }
    public void onClick(){
        MyOnclickListener myOnclickListener = new MyOnclickListener();
        fragment_button_left.setOnClickListener(myOnclickListener);
        fragment_button_right.setOnClickListener(myOnclickListener);
    }
}

