package com.example.thitkgiaodinassignment.object.income;

public class LoaiThu {
    private String maLT, tenLT;

    public LoaiThu(String maLT, String tenLT) {
        this.maLT = maLT;
        this.tenLT = tenLT;
    }

    public String getMaLT() {
        return maLT;
    }

    public void setMaLT(String maLT) {
        this.maLT = maLT;
    }

    public String getTenLT() {
        return tenLT;
    }

    public void setTenLT(String tenLT) {
        this.tenLT = tenLT;
    }
}
