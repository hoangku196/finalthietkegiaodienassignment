package com.example.thitkgiaodinassignment.object.income;

public class KhoanThu {

        private String maKT, tenKT, date;
        private double money;
        private LoaiThu loaiThu;

        public KhoanThu(String maKT, String tenKT, String date, double money, LoaiThu loaiThu) {
            this.maKT = maKT;
            this.tenKT = tenKT;
            this.date = date;
            this.money = money;
            this.loaiThu = loaiThu;
        }

        public String getMaKT() {
            return maKT;
        }

        public void setMaKT(String maKT) {
            this.maKT = maKT;
        }

        public String getTenKT() {
            return tenKT;
        }

        public void setTenKT(String tenKT) {
            this.tenKT = tenKT;
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

        public LoaiThu getLoaiThu() {
            return loaiThu;
        }

        public void setLoaiThu(LoaiThu loaiThu) {
            this.loaiThu = loaiThu;
        }

}
