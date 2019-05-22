package com.sky.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.sky.sqlite.DataBaseHelper;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class TestProvider extends ContentProvider {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;
    private static UriMatcher uriMatcher;
    private static final int BOOK_ALL = 0;
    private static final int BOOK_ONE = 1;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.sky.contentProvide", "book", BOOK_ALL);
        uriMatcher.addURI("com.sky.contentProvide", "book/#", BOOK_ONE);
    }

    @Override
    //执行初始化操作
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHelper(getContext(), "bookStore.db", null, 1);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@Nullable Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //创建表
        database = dataBaseHelper.getWritableDatabase();
        Cursor cursor = null;
        String type = getType(uri);
        System.out.println("type: " + type);
        if (type.equals("all")) {
            cursor = database.query("book", new String[]{
                    "author", "name", "price", "id"
            }, null, null, null, null, null);
        } else if (type.equals("one")) {
            String id = uri.getPathSegments().get(1);
            cursor = database.query("book", new String[]{
                    "author", "name", "price", "id"
            }, "id = ?", new String[]{
                    id
            }, null, null, null);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@Nullable Uri uri) {
        String type = "";
        //通过判断返回当前需要返回的类型
        switch (uriMatcher.match(uri)) {
            case BOOK_ALL:
                type = "all";
                break;
            case BOOK_ONE:
                type = "one";
                break;
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(@Nullable Uri uri, @Nullable ContentValues values) {
        //创建表
        database = dataBaseHelper.getWritableDatabase();
        database.insert("book",null,values);
        return null;
    }

    @Override
    public int delete(@Nullable Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@Nullable Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
