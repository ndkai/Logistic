package com.iot.logisticsapp.Model;

public class CungCapHangHoa {
    public String cungCapHangHoaID;
    public String userID;
    public String tenUser;
    public String sdtUser;
    public String diachiUser;
    public String thoiGianDuKien;
    public String tenItem;
    public String tenLoaiItem;
    public int khoiLuongItem;
    public String diaChiCCC;
    public String loaiHinhVanChuyen;
    public int tinhTrangVanChuyen;

    public String khoID;
    public double kinhDo;
    public double viDo;


    public CungCapHangHoa(String userID, String tenUser, String sdtUser, String diachiUser, String thoiGianDuKien, String tenItem, String tenLoaiItem, int khoiLuongItem, String diaChiCCC, String loaiHinhVanChuyen, int tinhTrangVanChuyen, String khoID, long kinhDo, long viDo) {
        this.userID = userID;
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diachiUser = diachiUser;
        this.thoiGianDuKien = thoiGianDuKien;
        this.tenItem = tenItem;
        this.tenLoaiItem = tenLoaiItem;
        this.khoiLuongItem = khoiLuongItem;
        this.diaChiCCC = diaChiCCC;
        this.loaiHinhVanChuyen = loaiHinhVanChuyen;
        this.tinhTrangVanChuyen = tinhTrangVanChuyen;
        this.khoID = khoID;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public CungCapHangHoa() {
    }

    public String getCungCapHangHoaID() {
        return cungCapHangHoaID;
    }

    public void setCungCapHangHoaID(String cungCapHangHoaID) {
        this.cungCapHangHoaID = cungCapHangHoaID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public int getTinhTrangVanChuyen() {
        return tinhTrangVanChuyen;
    }

    public void setTinhTrangVanChuyen(int tinhTrangVanChuyen) {
        this.tinhTrangVanChuyen = tinhTrangVanChuyen;
    }

    public String getKhoID() {
        return khoID;
    }

    public void setKhoID(String khoID) {
        this.khoID = khoID;
    }

    public double getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(double kinhDo) {
        this.kinhDo = kinhDo;
    }

    public double getViDo() {
        return viDo;
    }

    public void setViDo(double viDo) {
        this.viDo = viDo;
    }
}
