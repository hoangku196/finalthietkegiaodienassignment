package com.example.thitkgiaodinassignment.object.spend;

public class KhoanChi {
    private String maKC, tenKC, date;
    private double money;
    private LoaiChi loaiChi;

    public KhoanChi(String maKC, String tenKC, String date, double money, LoaiChi loaiChi) {
        this.maKC = maKC;
        this.tenKC = tenKC;
        this.date = date;
        this.money = money;
        this.loaiChi = loaiChi;
    }

    public String getMaKC() {
        return maKC;
    }

    public void setMaKC(String maKC) {
        this.maKC = maKC;
    }

    public String getTenKC() {
        return tenKC;
    }

    public void setTenKC(String tenKC) {
        this.tenKC = tenKC;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LoaiChi getLoaiChi() {
        return loaiChi;
    }

    public void setLoaiChi(LoaiChi loaiChi) {
        this.loaiChi = loaiChi;
    }
}
