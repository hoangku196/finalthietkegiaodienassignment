package com.example.thitkgiaodinassignment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thitkgiaodinassignment.database.DatabaseHelper;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ResultDAO(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(this.context);
        this.sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public double resultIncome() {
        double resultIncome = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select money from KhoanThu", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            resultIncome += cursor.getDouble(0);
            cursor.moveToNext();
        }

        return resultIncome;
    }

    public double resultSpend() {
        double resultSpend = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select money from KhoanChi", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            resultSpend += cursor.getDouble(0);
            cursor.moveToNext();
        }

        return resultSpend;
    }

    public int countIncome() {
        int countIncome = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select count(*) from KhoanThu", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            countIncome = cursor.getInt(0);
            cursor.moveToNext();
        }

        return countIncome;
    }

    public int countSpend() {
        int countSpend = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select count(*) from KhoanChi", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            countSpend = cursor.getInt(0);
            cursor.moveToNext();
        }

        return countSpend;
    }
}
