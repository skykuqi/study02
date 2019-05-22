package com.sky.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sky.myapplication.study02.R;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionActivity extends AppCompatActivity {
    private Button permission_button_call, permission_button_get;
    private EditText permission_edit_phone;
    private ListView permission_listview;
    private Intent intent;
    private List<String> list;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        permission_edit_phone = findViewById(R.id.permission_edit_phone);
        permission_button_call = findViewById(R.id.permission_button_call);
        permission_button_get = findViewById(R.id.permission_button_get);
        permission_listview = findViewById(R.id.permission_listview);
//        boolOpenCarmer();
        list = new ArrayList<String>();
        listViewAdapter = new ListViewAdapter(list, PermissionActivity.this);
        permission_listview.setAdapter(listViewAdapter);


        permission_button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                //PackageManager.PERMISSION_GRANTED 拥有当前权限   PackageManager.PERMISSION_DENIED  未拥有当前权限
                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_DENIED) {
                    //此时已经获取该权限
                    startActivity(intent);
                } else {
                    //申请当前权限
                    ActivityCompat.requestPermissions(PermissionActivity.this,
                            new String[]{
                                    Manifest.permission.CALL_PHONE
                            }, 1);
                }
            }
        });
        permission_button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_DENIED) {
                    addToList();
                } else {
                    //申请当前权限
                    ActivityCompat.requestPermissions(PermissionActivity.this,
                            new String[]{
                                    Manifest.permission.READ_CONTACTS
                            }, 1);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println(requestCode);
        System.out.println(Arrays.toString(permissions));
        System.out.println(Arrays.toString(grantResults));
        if (requestCode == 1 && grantResults[0] < 0) {
            Toast.makeText(PermissionActivity.this, "你取消了授权", Toast.LENGTH_SHORT).show();
        } else {
        }
    }

    private void boolOpenCarmer() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)  //打开相机权限
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)   //可读
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)  //可写
                        != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    private void addToList() {
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, null, null);
        }
        if(cursor != null){
            cursor.moveToFirst();
            do {
                //获取通讯录用户名
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //获取电话号码
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String str = name + " : " + number;
                System.out.println(str);
                list.add(str);
                listViewAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
            cursor.close();
        }


    }
}
