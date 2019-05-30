package com.sky.extra;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.sky.myapplication.study02.R;

import java.lang.reflect.Method;

/**
 * actionBar详解
 * 1.可以在AndroidManifest.xml(以下统称注册文件) 当前activity下
 * android:logo="@drawable/hamburger" 属性可以自定义actionBar的图标
 * 使用自定义toolBar来顶替actionBar时属性似乎不生效,需要在Activity中动态声明
 *
 * supportActionBar.setLogo(R.drawable.hamburger);
 * 2.注册文件中android:label标签则可以修改actionBar的标题为自定义标题
 *
 * 3.隐藏标题和图标
 * supportActionBar.setDisplayShowTitleEnabled(true);
 * supportActionBar.setDisplayShowHomeEnabled(true);
 *
 * 4.ActionBar还可以根据应用程序当前的功能来提供与其相关的Action按钮，
 * 这些按钮都会以图标或文字的形式直接显示在ActionBar上。当然，如果按钮过多，
 * ActionBar上显示不完，多出的一些按钮可以隐藏在overflow里面（最右边的三个点就是overflow按钮），
 * 点击一下overflow按钮就可以看到全部的Action按钮了。
 * 当Activity启动的时候，系统会调用Activity的onCreateOptionsMenu()方法来取出所有的Action按钮，
 * 我们只需要在这个方法中去加载一个menu资源，并把所有的Action按钮都定义在资源文件里面就可以了。
 *
 * 5.自定义菜单文件在layout中新建menu文件夹,自定义菜单文件menu_layout.xml
 *
 * 6.当用户点击Action按钮的时候，系统会调用Activity的onOptionsItemSelected()方法，
 * 通过方法传入的MenuItem参数，我们可以调用它的getItemId()方法和menu资源中的id进行比较，
 * 从而辨别出用户点击的是哪一个Action按钮。
 *
 * 7.overflow显示的menu菜单会出现在ActionBar上方,在style中设置可以解决
 * 默认overflow中的菜单选项不自带图标,当然也可以自行设置其显示图标需要用JAVA代码动态设置
 * 重写onMenuOpened方法,需要使用到反射,但是现在禁止了反射的使用
 *可以使用PopupWindow来达到目的
*/
public class MyActionBarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_action_bar);
        {
            toolbar = findViewById(R.id.toolbar);
        }
        setSupportActionBar(toolbar);
        //设置是否有返回箭头
        try {
            supportActionBar = getSupportActionBar();
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            //设置自定义图标
            supportActionBar.setLogo(R.drawable.hamburger);

            //隐藏标题和图标
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(MyActionBarActivity.this, "点击了返回键", Toast.LENGTH_SHORT).show();
                return false;
            case R.id.add:
                Toast.makeText(MyActionBarActivity.this, "点击了添加按键", Toast.LENGTH_SHORT).show();
                return false;
            case R.id.remove:
                Toast.makeText(MyActionBarActivity.this, "删除按键", Toast.LENGTH_SHORT).show();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {//现在无法通过反射获取到内部的API
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取MenuInflater
        MenuInflater menuInflater = getMenuInflater();
        //声明布局文件
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setActionBar() {
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
//        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);
        //设置自定义视图
//        supportActionBar.setCustomView(mActionBarView, lp);
        //如果已设置自定义视图，则显示该视图。
        supportActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //设置是否应显示自定义视图（如果设置）。
        supportActionBar.setDisplayShowCustomEnabled(true);
        //设置是否在操作栏中包含应用程序家庭负担。主页显示为活动图标或徽标。
        supportActionBar.setDisplayShowHomeEnabled(true);
        //设置是否应显示活动标题/副标题。
        supportActionBar.setDisplayShowTitleEnabled(true);
    }
}
