package com.iot.logisticsapp.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class CungCapHangHoa {
    public String cungCapHangHoaID;
    public String userID;
    public String tenUser;
    public String sdtUser;
    public String diachiUser;
    public String thoiGianGuiHang;
    public String tenItem;
    public String tenLoaiItem;
    public int soLuong;
    public String diaChiCCC;
    public String loaiHinhVanChuyen;
    public String tinhTrangVanChuyen;
    public String duAnId;
    List<String> chiTietTinhTrang;

    public String khoID;
    public double kinhDo;
    public double viDo;

    public CungCapHangHoa(String userID, String tenUser, String sdtUser, String diachiUser, String thoiGianGuiHang, String tenItem, String tenLoaiItem, int soLuong
            , String diaChiCCC, String loaiHinhVanChuyen, String tinhTrangVanChuyen, String khoID, double kinhDo, double viDo, List<String> chiTietTinhTrang) {
        this.userID = userID;
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diachiUser = diachiUser;
        this.thoiGianGuiHang = thoiGianGuiHang;
        this.tenItem = tenItem;
        this.tenLoaiItem = tenLoaiItem;
        this.soLuong = soLuong;
        this.diaChiCCC = diaChiCCC;
        this.loaiHinhVanChuyen = loaiHinhVanChuyen;
        this.tinhTrangVanChuyen = tinhTrangVanChuyen;
        this.khoID = khoID;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.chiTietTinhTrang = chiTietTinhTrang;
    }

    public CungCapHangHoa(String cungCapHangHoaID, String userID, String tenUser, String sdtUser, String diachiUser, String thoiGianGuiHang, String tenItem, String tenLoaiItem, int soLuong, String diaChiCCC, String loaiHinhVanChuyen, String tinhTrangVanChuyen, String duAnId, List<String> chiTietTinhTrang, String khoID, double kinhDo, double viDo) {
        this.cungCapHangHoaID = cungCapHangHoaID;
        this.userID = userID;
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diachiUser = diachiUser;
        this.thoiGianGuiHang = thoiGianGuiHang;
        this.tenItem = tenItem;
        this.tenLoaiItem = tenLoaiItem;
        this.soLuong = soLuong;
        this.diaChiCCC = diaChiCCC;
        this.loaiHinhVanChuyen = loaiHinhVanChuyen;
        this.tinhTrangVanChuyen = tinhTrangVanChuyen;
        this.duAnId = duAnId;
        this.chiTietTinhTrang = chiTietTinhTrang;
        this.khoID = khoID;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public String getDuAnId() {
        return duAnId;
    }

    public void setDuAnId(String duAnId) {
        this.duAnId = duAnId;
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

    public String getThoiGianGuiHang() {
        return thoiGianGuiHang;
    }

    public void setThoiGianGuiHang(String thoiGianGuiHang) {
        this.thoiGianGuiHang = thoiGianGuiHang;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public String getTinhTrangVanChuyen() {
        return tinhTrangVanChuyen;
    }

    public void setTinhTrangVanChuyen(String tinhTrangVanChuyen) {
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

    public List<String> getChiTietTinhTrang() {
        return chiTietTinhTrang;
    }

    public void setChiTietTinhTrang(List<String> chiTietTinhTrang) {
        this.chiTietTinhTrang = chiTietTinhTrang;
    }

    public LatLng getLocation() {
        return new LatLng(kinhDo, viDo);
    }
}
