package com.flueky.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SQLiteDatabase.CursorFactory factory = new SQLiteDatabase.CursorFactory() {
                @Override
                public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        return new SQLiteCursor(masterQuery, editTable, query);
                    } else
                        return new SQLiteCursor(db, masterQuery, editTable, query);
                }
            };
            DatabaseErrorHandler errorHandler = new DatabaseErrorHandler() {
                @Override
                public void onCorruption(SQLiteDatabase dbObj) {
                    dbObj.close();
                }
            };
//            SQLiteDatabase.OpenParams openParams = new SQLiteDatabase.OpenParams.Builder()
//                    .setCursorFactory(factory)
//                    .setErrorHandler(errorHandler)
//                    .addOpenFlags(SQLiteDatabase.CREATE_IF_NECESSARY)
//                    .build();

            DBOpenHelper dbHelper = new DBOpenHelper(this, "test.db", factory, 1);
            // 用读/写的方式打开数据库文件
            // 第一次打开时，会创建db文件，并执行onCreate/onUpgrade 方法
            // 需要调用 close 方法关闭
            SQLiteDatabase database = dbHelper.getWritableDatabase();
//        SQLiteDatabase database = SQLiteDatabase.create(factory);
//            SQLiteDatabase database = SQLiteDatabase.createInMemory(openParams);
//            SQLiteDatabase database = SQLiteDatabase.openDatabase(getDatabasePath("demo.db").getAbsolutePath(), factory, SQLiteDatabase.OPEN_READONLY, errorHandler);

//            String CREATE_SQL = "create table user ( id INTEGER primary key autoincrement,name text,age INTEGER)";
//            database.execSQL(CREATE_SQL);


            ContentValues values = new ContentValues();
            values.put("id", 1);
            values.put("name", "flueky");
            values.put("age", 27);
            // 插入完整的数据，id name age
            database.insert("user", null, values);
            // 插入空数据，将 name 设置为 null
            database.insert("user", "name", null);
            // 替换 id = 1 的数据
            values.put("id", 1);
            values.put("name", "xiaofei");
            values.put("age", 27);
            database.replace("user", null, values);

            Cursor cursor = database.query("user", null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));

                Log.d(TAG, "onCreate: " + id + " " + name + " " + age);
            }
            cursor.close();

            database.close();

        }
    }
}