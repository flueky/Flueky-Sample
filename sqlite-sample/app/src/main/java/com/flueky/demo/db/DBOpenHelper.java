package com.flueky.demo.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * 管理数据库创建和版本
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    // 相当于 SQLiteDatabase openDatabase(String, CursorFactory)
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 相当于 SQLiteDatabase openDatabase(String, CursorFactory, DatabaseErrorHandler)
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // 相当于 SQLiteDatabase openDatabase(String , OpenParams);
    @TargetApi(Build.VERSION_CODES.P)
    public DBOpenHelper(Context context, String name, int version, SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    // 创建数据文件时调用，此时适合创建新表
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SQL = "create table user( id INTEGER primary key autoincrement,name text,age INTEGER)";
        db.execSQL(CREATE_SQL);
    }

    // 更新数据库版本时调用，适合更新表结构或创建新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
