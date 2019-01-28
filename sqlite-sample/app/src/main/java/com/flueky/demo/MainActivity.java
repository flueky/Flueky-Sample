package com.flueky.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.flueky.demo.db.DBOpenHelper;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.CursorFactory factory = new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    return new SQLiteCursor(masterQuery, editTable, query);
                }
                return null;
            }
        };
        DBOpenHelper dbHelper = new DBOpenHelper(this, "test.db", factory, 1);
        // 用读/写的方式打开数据库文件
        // 第一次打开时，会创建db文件，并执行onCreate/onUpgrade 方法
        // 需要调用 close 方法关闭
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "flueky");
        values.put("age", 28);
        database.insert("user", null, values);

        Cursor cursor = database.query("user", null, null, null, null, null, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age= cursor.getInt(cursor.getColumnIndex("age"));

            Log.d(TAG, "onCreate: "+id+" "+name+" "+age);
        }
        cursor.close();

        database.close();

    }
}