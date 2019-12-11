package com.example.thitkgiaodinassignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thitkgiaodinassignment.database.DatabaseHelper;
import com.example.thitkgiaodinassignment.object.spend.KhoanChi;
import com.example.thitkgiaodinassignment.object.spend.LoaiChi;

import java.util.ArrayList;
import java.util.List;

public class SpendDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SpendDAO(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(this.context);
        this.sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public boolean insertNewTypeSpend(LoaiChi loaiChi) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("maLC", loaiChi.getMaLC());
            contentValues.put("tenLC", loaiChi.getTenLC());
            long insert = sqLiteDatabase.insert("LoaiChi", null, contentValues);
            if (insert < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertNewSpend(KhoanChi khoanChi) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("maKC", khoanChi.getMaKC());
            contentValues.put("tenKC", khoanChi.getTenKC());
            contentValues.put("money", khoanChi.getMoney());
            contentValues.put("maLC", khoanChi.getLoaiChi().getMaLC());
            contentValues.put("date", khoanChi.getDate());
            long insert = sqLiteDatabase.insert("KhoanChi", null, contentValues);
            if (insert < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<String> listNameTypeSpend() {
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from LoaiChi", null);

        List<String> listNameTypeSpend = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tenLC = cursor.getString(1);
            listNameTypeSpend.add(tenLC);
            cursor.moveToNext();
        }

        return listNameTypeSpend;
    }

    public String searchMaLCByTenLC(String tenLC) {
        Cursor cursor = sqLiteDatabase.rawQuery("select maLC from LoaiChi where tenLC=?", new String[]{tenLC});
        String maLC = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            maLC = cursor.getString(0);

            cursor.moveToNext();
        }

        return maLC;
    }

    public String searchTenLCByMaLC(String maLC) {
        Cursor cursor = sqLiteDatabase.rawQuery("select tenLC from LoaiChi where maLC=?", new String[]{maLC});
        String tenLC = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tenLC = cursor.getString(0);

            cursor.moveToNext();
        }

        return tenLC;
    }

    public boolean checkLoaiChi(LoaiChi loaiChi) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from KhoanChi where maLC=?", new String[]{loaiChi.getMaLC()});
        String maLC = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            maLC = cursor.getString(2);

            cursor.moveToNext();
        }
        if (maLC != null) {
            return false;
        }

        return true;
    }

    public List<KhoanChi> listKhoanChi() {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from KhoanChi", null);
        List<KhoanChi> listKhoanChi = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maKC = cursor.getString(0);
            String tenKC = cursor.getString(1);
            String maLC = cursor.getString(2);
            double money = cursor.getDouble(3);
            String date = cursor.getString(4);

            listKhoanChi.add(new KhoanChi(maKC, tenKC, date, money, new LoaiChi(maLC, searchTenLCByMaLC(maLC))));

            cursor.moveToNext();
        }

        return listKhoanChi;
    }

    public List<LoaiChi> listLoaiChi() {
        List<LoaiChi> listLoaiChi = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from LoaiChi", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maLC = cursor.getString(0);
            String tenLC = cursor.getString(1);

            listLoaiChi.add(new LoaiChi(maLC, tenLC));
            cursor.moveToNext();
        }

        return listLoaiChi;
    }

    public boolean deleteSpend(String maKC) {

        long check = sqLiteDatabase.delete("KhoanChi", "maKC=?", new String[]{maKC});

        if (check < 0) {
            return false;
        }

        return true;
    }


    public boolean deleteTypeSpend(LoaiChi loaiChi) {
        long check = sqLiteDatabase.delete("LoaiChi", "maLC=?", new String[]{loaiChi.getMaLC()});

        if (check < 0) {
            return false;
        }

        return true;
    }

    public boolean updateSpend(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maKC", khoanChi.getMaKC());
        contentValues.put("tenKC", khoanChi.getTenKC());
        contentValues.put("money", khoanChi.getMoney());
        contentValues.put("maLC", khoanChi.getLoaiChi().getMaLC());
        contentValues.put("date", khoanChi.getDate());

        long check = sqLiteDatabase.update("KhoanChi", contentValues, "maKC=?", new String[]{khoanChi.getMaKC()});

        if (check < 0) {
            return false;
        }
        return true;
    }

    public boolean updateTypeSpend(LoaiChi loaiChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maLC", loaiChi.getMaLC());
        contentValues.put("tenLC", loaiChi.getTenLC());
        long check = sqLiteDatabase.update("LoaiChi", contentValues, "maLC=?", new String[]{loaiChi.getMaLC()});
        if (check < 0) {
            return false;
        }
        return true;
    }
}
