package com.sky.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sky.myapplication.study02.R;

public class SqliteActivity extends AppCompatActivity {
    private Button sqlite_button_create, sqlite_button_insert,sqlite_button_insert_sql,sqlite_button_update
            ,sqlite_button_delete,sqlite_button_query;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;
    private EditText sqlite_edit_name, sqlite_edit_author, sqlite_edit_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        sqlite_button_create = findViewById(R.id.sqlite_button_create);
        sqlite_button_insert = findViewById(R.id.sqlite_button_insert);
        sqlite_edit_name = findViewById(R.id.sqlite_edit_name);
        sqlite_edit_author = findViewById(R.id.sqlite_edit_author);
        sqlite_edit_price = findViewById(R.id.sqlite_edit_price);
        sqlite_button_insert_sql = findViewById(R.id.sqlite_button_insert_sql);
        sqlite_button_update =findViewById(R.id.sqlite_button_update);
        sqlite_button_delete = findViewById(R.id.sqlite_button_delete);
        sqlite_button_query = findViewById(R.id.sqlite_button_query);

        dataBaseHelper = new DataBaseHelper(SqliteActivity.this, "bookStore.db", null, 1);
        //获取SQLiteDatabase
        database = dataBaseHelper.getWritableDatabase();
        onClick();
    }

    private void onClick() {
        MyOnclickListener myOnclickListener = new MyOnclickListener();
        sqlite_button_create.setOnClickListener(myOnclickListener);
        sqlite_button_insert.setOnClickListener(myOnclickListener);
        sqlite_button_insert_sql.setOnClickListener(myOnclickListener);
        sqlite_button_update.setOnClickListener(myOnclickListener);
        sqlite_button_delete.setOnClickListener(myOnclickListener);
        sqlite_button_query.setOnClickListener(myOnclickListener);
    }

    class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sqlite_button_create:

                    break;
                case R.id.sqlite_button_insert: //添加内容

                    String name = sqlite_edit_name.getText().toString().trim();
                    String author = sqlite_edit_author.getText().toString().trim();
                    String price = sqlite_edit_price.getText().toString().trim();
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(author) || TextUtils.isEmpty(price)) {
                        Toast.makeText(SqliteActivity.this, "请填写完所有数据后提交", Toast.LENGTH_SHORT).show();
                    } else {
                        //声明 ContentValues
                        ContentValues values = new ContentValues();
                        values.put("name", name);
                        values.put("author", author);
                        values.put("price", price);
                        database.insert("book", null, values);
                        //若想多次添加可以调用 values.clear(); 方法清空ContentValues
                        Toast.makeText(SqliteActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.sqlite_button_insert_sql:
                    database.execSQL("insert into book (name,author,price) values (?,?,?)",new String[]{
                            "三国演义","罗贯中","25"
                    });
                    break;
                case R.id.sqlite_button_update:
                    //修改数据
                    database.execSQL("update book set name = ?,author = ? where id in(?,?,?)",new String[]{
                            "水浒传","施耐庵","2","3","4"
                    });
                    break;
                case R.id.sqlite_button_delete:
                    //删除数据
                    database.execSQL("delete from book where id between ? and ?",new String[]{
                            "2","4"
                    });
                    break;
                case R.id.sqlite_button_query:
                    Cursor cursor = database.query("book",new String[]{
                            "author","name","price","id"
                    },"id > 6",null,null,null,null);
                    cursor.moveToFirst();
                    do{
                        String name1 = cursor.getString(cursor.getColumnIndex("name"));
                        String author1 = cursor.getString(cursor.getColumnIndex("author"));
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String price1 = cursor.getString(cursor.getColumnIndex("price"));
                        System.out.println("name1: " + name1 + " id: " + id + " author1: " + author1 +
                        " price1: " + price1);
                    }while (cursor.moveToNext());
                    break;
            }
        }
    }
}
