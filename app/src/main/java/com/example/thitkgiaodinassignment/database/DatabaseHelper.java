package com.example.thitkgiaodinassignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "dbChiTieu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableLoaiChi = "create table LoaiChi(maLC text primary key, tenLC text)";
        String createTableKhoanChi = "create table KhoanChi(maKC text primary key, tenKC text, maLC text, money decimal, date text)";
        String createTableLoaiThu = "create table LoaiThu(maLT text primary key, tenLT text)";
        String createTableKhoanThu = "create table KhoanThu(maKT text primary key, tenKT text, maLT text, money decimal, date text)";
        db.execSQL(createTableLoaiChi);
        db.execSQL(createTableKhoanChi);
        db.execSQL(createTableLoaiThu);
        db.execSQL(createTableKhoanThu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists KhoanChi");
        db.execSQL("drop table if exists LoaiChi");
        db.execSQL("drop table if exists KhoanThu");
        db.execSQL("drop table if exists LoaiThu");
        onCreate(db);
    }
}
