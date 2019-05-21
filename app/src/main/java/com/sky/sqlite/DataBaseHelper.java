package com.sky.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String CREATE_BOOK = "create table book" +
            "(id integer primary key autoincrement,name TEXT,author text,price integer);";
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;

    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行CREATE_BOOK sql语句
        db.execSQL(CREATE_BOOK);
        Toast.makeText(context,"创建数据表成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
