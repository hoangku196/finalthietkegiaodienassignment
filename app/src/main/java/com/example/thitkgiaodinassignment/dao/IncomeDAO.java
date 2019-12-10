package com.example.thitkgiaodinassignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thitkgiaodinassignment.database.DatabaseHelper;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public IncomeDAO(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(this.context);
        this.sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public boolean insertNewTypeIncome(LoaiThu loaiThu) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("maLT", loaiThu.getMaLT());
            contentValues.put("tenLT", loaiThu.getTenLT());
            long insert = sqLiteDatabase.insert("LoaiThu", null, contentValues);
            if (insert < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertNewIncome(KhoanThu khoanThu) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("maKT", khoanThu.getMaKT());
            contentValues.put("tenKT", khoanThu.getTenKT());
            contentValues.put("money", khoanThu.getMoney());
            contentValues.put("maLT", khoanThu.getLoaiThu().getMaLT());
            contentValues.put("date", khoanThu.getDate());
            long insert = sqLiteDatabase.insert("KhoanThu", null, contentValues);
            if (insert < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<String> listNameTypeIncome() {
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from LoaiThu", null);

        List<String> listNameTypeIncome = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tenLT = cursor.getString(1);
            listNameTypeIncome.add(tenLT);
            cursor.moveToNext();
        }

        return listNameTypeIncome;
    }

    public String searchTenLTByMaLT(String maLT) {
        Cursor cursor = sqLiteDatabase.rawQuery("select tenLT from LoaiThu where maLT=?", new String[]{maLT});
        String tenLT = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tenLT = cursor.getString(0);

            cursor.moveToNext();
        }

        return tenLT;
    }

    public boolean checkLoaiThu(LoaiThu loaiThu) {
        Cursor cursor = sqLiteDatabase.rawQuery("select maLT from KhoanThu where maLT=?", new String[]{loaiThu.getMaLT()});
        String maLT = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            maLT = cursor.getString(0);

            cursor.moveToNext();
        }
        if (maLT != null) {
            return false;
        }

        return true;
    }

    public List<KhoanThu> listKhoanThu() {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from KhoanThu", null);
        List<KhoanThu> listKhoanThu = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maKT = cursor.getString(0);
            String tenKT = cursor.getString(1);
            String maLT = cursor.getString(2);
            double money = cursor.getDouble(3);
            String date = cursor.getString(4);

            listKhoanThu.add(new KhoanThu(maKT, tenKT, date, money, new LoaiThu(maLT, searchTenLTByMaLT(maLT))));

            cursor.moveToNext();
        }

        return listKhoanThu;
    }

    public List<LoaiThu> listLoaiThu() {
        List<LoaiThu> listLoaiThu = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from LoaiThu", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maLT = cursor.getString(0);
            String tenLT = cursor.getString(1);

            listLoaiThu.add(new LoaiThu(maLT, tenLT));
            cursor.moveToNext();
        }

        return listLoaiThu;
    }

    public boolean deleteIncome(String maKT) {

        long check = sqLiteDatabase.delete("khoanThu", "maKT=?", new String[]{maKT});

        if (check < 0) {
            return false;
        }

        return true;
    }


    public boolean deleteTypeIncome(LoaiThu loaiThu) {
        long check = sqLiteDatabase.delete("LoaiThu", "maLT=?", new String[]{loaiThu.getMaLT()});

        if (check < 0) {
            return false;
        }

        return true;
    }

    public boolean updateIncome(KhoanThu khoanThu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maKT", khoanThu.getMaKT());
        contentValues.put("tenKT", khoanThu.getTenKT());
        contentValues.put("money", khoanThu.getMoney());
        contentValues.put("maLT", khoanThu.getLoaiThu().getMaLT());
        contentValues.put("date", khoanThu.getDate());

        long check = sqLiteDatabase.update("KhoanThu", contentValues, "maKT=?", new String[]{khoanThu.getMaKT()});

        if (check < 0) {
            return false;
        }
        return true;
    }

    public boolean updateTypeIncome(LoaiThu loaiThu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maLT", loaiThu.getMaLT());
        contentValues.put("tenLT", loaiThu.getTenLT());
        long check = sqLiteDatabase.update("LoaiThu", contentValues, "maLT=?", new String[]{loaiThu.getMaLT()});
        if (check < 0) {
            return false;
        }
        return true;
    }
}
