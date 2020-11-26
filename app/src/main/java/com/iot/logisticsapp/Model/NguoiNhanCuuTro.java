package com.iot.logisticsapp.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class NguoiNhanCuuTro {
    public String userID;
    public String tenUser;
    public String sdtUser;
    public String diaChiUser;
    public String vanDe;
    public String canGiupDo;
    public String chiTietTinhTrang;
    public String tinhTrang;
    public String loai;

    public String dotCuuTroID;
    List<String> theoDoiYeuCau;

    public String hangHoaID;
    public String khoID;
    public double kinhDo;
    public double viDo;


    public NguoiNhanCuuTro(String userID, String tenUser, String sdtUser, String diaChiUser, String vanDe, String canGiupDo, String chiTietTinhTrang
            , String tinhTrang, String hangHoaID, String khoID, double kinhDo, double viDo, List<String> theoDoiYeuCau, String loai) {
        this.loai = loai;
        this.userID = userID;
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diaChiUser = diaChiUser;
        this.vanDe = vanDe;
        this.canGiupDo = canGiupDo;
        this.chiTietTinhTrang = chiTietTinhTrang;
        this.tinhTrang = tinhTrang;
        this.hangHoaID = hangHoaID;
        this.khoID = khoID;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.theoDoiYeuCau = theoDoiYeuCau;
    }

    public NguoiNhanCuuTro() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getDotCuuTroID() {
        return dotCuuTroID;
    }

    public void setDotCuuTroID(String dotCuuTroID) {
        this.dotCuuTroID = dotCuuTroID;
    }

    public String getHangHoaID() {
        return hangHoaID;
    }

    public void setHangHoaID(String hangHoaID) {
        this.hangHoaID = hangHoaID;
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

    public String getDiaChiUser() {
        return diaChiUser;
    }

    public void setDiaChiUser(String diaChiUser) {
        this.diaChiUser = diaChiUser;
    }

    public String getVanDe() {
        return vanDe;
    }

    public void setVanDe(String vanDe) {
        this.vanDe = vanDe;
    }

    public String getCanGiupDo() {
        return canGiupDo;
    }

    public void setCanGiupDo(String canGiupDo) {
        this.canGiupDo = canGiupDo;
    }

    public String getChiTietTinhTrang() {
        return chiTietTinhTrang;
    }

    public void setChiTietTinhTrang(String chiTietTinhTrang) {
        this.chiTietTinhTrang = chiTietTinhTrang;
    }

    public List<String> getTheoDoiYeuCau() {
        return theoDoiYeuCau;
    }

    public void setTheoDoiYeuCau(List<String> theoDoiYeuCau) {
        this.theoDoiYeuCau = theoDoiYeuCau;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
    public LatLng getLocation() {
        return new LatLng(kinhDo, viDo);
    }
}
