package com.sky.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitepalActivity extends AppCompatActivity {
    private Button litepal_button_create, litepal_button_insert, litepal_button_update, litepal_button_delete
            ,litepal_button_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal);
        litepal_button_create = findViewById(R.id.litepal_button_create);
        litepal_button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建数据库
                LitePalApplication.initialize(LitepalActivity.this);
                Connector.getWritableDatabase();
            }
        });

        litepal_button_insert = findViewById(R.id.litepal_button_insert);
        litepal_button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigBook bigBook = new BigBook();
                bigBook.setName("西游记");
                bigBook.setAuthor("吴承恩");
                bigBook.setPrice(100);
                //存储数据
                bigBook.save();
            }
        });

        litepal_button_update = findViewById(R.id.litepal_button_update);
        litepal_button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePalApplication.initialize(LitepalActivity.this);
                BigBook bigBook = new BigBook();
                bigBook.setAuthor("施耐庵");
//                bigBook.update(2);
//                bigBook.updateAll("name = ?","西游记");
                bigBook.updateAll("name = ? and id >2", "水浒传");
            }
        });

        litepal_button_delete = findViewById(R.id.litepal_button_delete);
        litepal_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePalApplication.initialize(LitepalActivity.this);
                DataSupport.deleteAll("bigbook","id > 5 and name = '水浒传'");
//
            }
        });

        litepal_button_query = findViewById(R.id.litepal_button_query);
        litepal_button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePalApplication.initialize(LitepalActivity.this);
                /*DataSupport.findAll  查询所有数据
                * DataSupport.findFirst()   第一条数据
                * DataSupport.findLast() 最后一条数据
                * DataSupport.where() 查询
                * .order()      排序
                * .limit(2).find()  从头开始取2条
                * .limit(2).offset(3).find() 从索引为3的位置开始,取2条
                * */

                List<BigBook> books = DataSupport.findAll(BigBook.class);
                System.out.println(books.toString());
                List<BigBook> books1 = DataSupport.select("name").find(BigBook.class);
                System.out.println(books1.toString());
            }
        });

    }
}
