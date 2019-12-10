package com.example.thitkgiaodinassignment.object.spend;

public class LoaiChi {

    private String maLC, tenLC;

    public LoaiChi(String maLC, String tenLC) {
        this.maLC = maLC;
        this.tenLC = tenLC;
    }

    public String getMaLC() {
        return maLC;
    }

    public void setMaLC(String maLC) {
        this.maLC = maLC;
    }

    public String getTenLC() {
        return tenLC;
    }

    public void setTenLC(String tenLC) {
        this.tenLC = tenLC;
    }
}
