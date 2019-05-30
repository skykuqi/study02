package com.sky.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.myapplication.study02.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerActivity extends AppCompatActivity {
    private Button viewPager_button;
    private ViewPager viewPager_pager;
    private List<Fragment> fragments;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        {
            viewPager_button = findViewById(R.id.viewPager_button);
            viewPager_pager = findViewById(R.id.viewPager_pager);
            fragments = new ArrayList<Fragment>();
            LeftFragment leftFragment = new LeftFragment();
            RightFragment rightFragment = new RightFragment();
            fragments.add(leftFragment);
            fragments.add(rightFragment);
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            viewPager_pager.setAdapter(myPagerAdapter);
            viewPager_pager.setCurrentItem(0);
            //预加载页面
//            viewPager_pager.setOffscreenPageLimit(3);
        }
        viewPager_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myPagerAdapter.get(1);
                if (viewPager_pager.getCurrentItem() == 0) {
                    viewPager_pager.setCurrentItem(1);
                } else {
                    viewPager_pager.setCurrentItem(0);
                }
            }
        });
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
