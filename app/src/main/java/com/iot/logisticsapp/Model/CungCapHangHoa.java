package com.iot.logisticsapp.Model;

public class CungCapHangHoa {
    public String tenUser;
    public String sdtUser;
    public String diachiUser;
    public String thoiGianDuKien;
    public String tenItem;
    public String tenLoaiItem;
    public int khoiLuongItem;
    public String diaChiCCC;
    public String loaiHinhVanChuyen;

    public CungCapHangHoa(String tenUser, String sdtUser, String diachiUser, String thoiGianDuKien, String tenItem, String tenLoaiItem, int khoiLuongItem, String diaChiCCC, String loaiHinhVanChuyen) {
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diachiUser = diachiUser;
        this.thoiGianDuKien = thoiGianDuKien;
        this.tenItem = tenItem;
        this.tenLoaiItem = tenLoaiItem;
        this.khoiLuongItem = khoiLuongItem;
        this.diaChiCCC = diaChiCCC;
        this.loaiHinhVanChuyen = loaiHinhVanChuyen;
    }

    public CungCapHangHoa() {
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getSdtUser() {
        return sdtUser;
    }

    public void setSdtUser(String sdtUser) {
        this.sdtUser = sdtUser;
    }

    public String getDiachiUser() {
        return diachiUser;
    }

    public void setDiachiUser(String diachiUser) {
        this.diachiUser = diachiUser;
    }

    public String getThoiGianDuKien() {
        return thoiGianDuKien;
    }

    public void setThoiGianDuKien(String thoiGianDuKien) {
        this.thoiGianDuKien = thoiGianDuKien;
    }

    public String getTenItem() {
        return tenItem;
    }

    public void setTenItem(String tenItem) {
        this.tenItem = tenItem;
    }

    public String getTenLoaiItem() {
        return tenLoaiItem;
    }

    public void setTenLoaiItem(String tenLoaiItem) {
        this.tenLoaiItem = tenLoaiItem;
    }

    public int getKhoiLuongItem() {
        return khoiLuongItem;
    }

    public void setKhoiLuongItem(int khoiLuongItem) {
        this.khoiLuongItem = khoiLuongItem;
    }

    public String getDiaChiCCC() {
        return diaChiCCC;
    }

    public void setDiaChiCCC(String diaChiCCC) {
        this.diaChiCCC = diaChiCCC;
    }

    public String getLoaiHinhVanChuyen() {
        return loaiHinhVanChuyen;
    }

    public void setLoaiHinhVanChuyen(String loaiHinhVanChuyen) {
        this.loaiHinhVanChuyen = loaiHinhVanChuyen;
    }
}
