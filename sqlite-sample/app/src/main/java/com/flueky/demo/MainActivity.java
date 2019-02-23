package com.flueky.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.flueky.demo.db.DBOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "flueky";
    private EditText edtId, edtName, edtAge;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = findViewById(R.id.activity_main_edt_id);
        edtName = findViewById(R.id.activity_main_edt_name);
        edtAge = findViewById(R.id.activity_main_edt_age);
        SQLiteDatabase.CursorFactory factory = new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {

                Log.i(TAG, query.toString());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    return new SQLiteCursor(masterQuery, editTable, query);
                } else
                    return new SQLiteCursor(db, masterQuery, editTable, query);
            }
        };
        DatabaseErrorHandler errorHandler = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            errorHandler = new DatabaseErrorHandler() {
                @Override
                public void onCorruption(SQLiteDatabase dbObj) {
                    dbObj.close();
                }
            };
        }

        SQLiteOpenHelper dbHelper;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.O_MR1) {
            SQLiteDatabase.OpenParams openParams = new SQLiteDatabase.OpenParams.Builder()
                    .setCursorFactory(factory)
                    .setErrorHandler(errorHandler)
                    .addOpenFlags(SQLiteDatabase.CREATE_IF_NECESSARY)
                    .build();
            dbHelper = new DBOpenHelper(this, "test.db", 1, openParams);
        } else {
            dbHelper = new DBOpenHelper(this, "test.db", factory, 1, errorHandler);
        }

        // 用读/写的方式打开数据库文件
        // 第一次打开时，会创建db文件，并执行onCreate/onUpgrade 方法
        // 需要调用 close 方法关闭
        database = dbHelper.getWritableDatabase();

    }

    public void query(View v) {
        // distinct = true 去重查询
        Log.d(TAG, "query: 全量查询");
        queryAll();
        Log.d(TAG, "query: 去重查询");
        Cursor cursor = database.query(true, "user", new String[]{"name", "age"}, null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "query: 分组查询");
        cursor = database.query(false, "user", new String[]{"id", "name", "age", "count(name)"}, null, null, "name,age", null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "query: 分组过滤"); // name = null 的元组 不参与统计
        cursor = database.query(false, "user", new String[]{"id", "name", "age"}, null, null, "name", "count(name)>2", null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "query: 分页查询,显示前 3 条");
        cursor = database.query(false, "user", new String[]{"id", "name", "age"}, null, null, null, null, null, "3");
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "query: 分页查询,显示第 1 2 3 4 条");
        cursor = database.query(false, "user", new String[]{"id", "name", "age"}, null, null, null, null, null, "1,4");
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "query: 排序查询");
        cursor = database.query(false, "user", new String[]{"id", "name", "age"}, null, null, null, null, "age desc", null);
        logCursor(cursor);
        cursor.close();
    }

    public void queryAll() {
        Cursor cursor = database.query("user", new String[]{"id", "name", "age"}, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
    }

    public void logCursor(Cursor cursor) {
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("id");
            if (index >= 0) {
                buffer.append(" id : ");
                buffer.append(cursor.getInt(index));
            }
            index = cursor.getColumnIndex("name");
            if (index >= 0) {
                buffer.append(" name : ");
                buffer.append(cursor.getString(index));
            }
            index = cursor.getColumnIndex("age");
            if (index >= 0) {
                buffer.append(" age : ");
                buffer.append(cursor.getInt(index));
            }
            index = cursor.getColumnIndex("count(name)");
            if (index >= 0) {
                buffer.append("  count(name) : ");
                buffer.append(cursor.getInt(index));
            }
            buffer.append("\n");
        }
        Log.d(TAG, "query result: " + buffer);
    }

    public void update(View v) {
        Log.d(TAG, "update: 更新数据");
        ContentValues cv = new ContentValues();
        String id = edtId.getText().toString();
        String name = edtName.getText().toString();
        String age = edtAge.getText().toString();
//        if (id != null && !id.equals("")) {
//            cv.put("id", id);
//        }
        if (name != null && !name.equals("")) {
            cv.put("name", name);
        }
        if (age != null && !age.equals("")) {
            cv.put("age", age);
        }
        if (cv.size() > 0) {// ContentValues 不能为空
            int flag = database.update("user", cv, "id < ?", new String[]{"3"});
            Log.d(TAG, "update: result--" + flag);
        }
        queryAll();
    }

    public void delete(View v) {
        Log.d(TAG, "delete: 删除数据");
        String id = edtId.getText().toString();
        String name = edtName.getText().toString();
        String age = edtAge.getText().toString();
        StringBuffer buffer = new StringBuffer();
        List<String> data = new ArrayList<String>();
        if (id != null && !id.equals("")) {
            buffer.append(" or id = ?");
            data.add(id);
        }
        if (name != null && !name.equals("")) {
            buffer.append(" or name = ?");
            data.add(name);
        }
        if (age != null && !age.equals("")) {
            buffer.append(" or age = ?");
            data.add(age);
        }
        String[] args = new String[data.size()];
        data.toArray(args);
        int flag = 0;
        if (buffer.length() > 3) // 删除满足条件的数据
            flag = database.delete("user", buffer.substring(3), args);
        else // 删除全部数据
            flag = database.delete("user", null, null);
        Log.d(TAG, "delete: result-" + flag);
        queryAll();

    }

    public void insert(View v) {
        Log.d(TAG, "insert: 插入一行数据");
        ContentValues cv = new ContentValues();
        String id = edtId.getText().toString();
        if (id != null && !id.equals(""))
            cv.put("id", id);
        String name = edtName.getText().toString();
        if (name != null && !name.equals(""))
            cv.put("name", name);
        String age = edtAge.getText().toString();
        if (age != null && !age.equals(""))
            cv.put("age", age);
        long flag = 0;
        if (name == null || name.equals(""))
            // 演示 nullColumnHack 参数的使用
            flag = database.insert("user", "name", cv);
        else
            flag = database.insert("user", null, cv);
        Log.d(TAG, "insert: result-" + flag);
        queryAll();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null)
            database.close();
    }
}