package com.iot.logisticsapp.Model;

import com.google.android.gms.maps.model.LatLng;

public class CungCapVanTai {
    public String xeID;
    public String userID;
    public String tenTaiXe;
    public String cmndTaiXe ;
    public String gplxTaiXe;
    public String loaiXe ;
    public String bienSo ;
    public String tuNgay ;
    public String denNgay ;
    public int taiTrong ;

    public String khoID;
    public double kinhDo;
    public double viDo;


    public CungCapVanTai(String userID, String tenTaiXe, String cmndTaiXe, String gplxTaiXe, String loaiXe, String bienSo, String tuNgay, String denNgay, int taiTrong, String khoID, double kinhDo, double viDo) {
        this.userID = userID;
        this.tenTaiXe = tenTaiXe;
        this.cmndTaiXe = cmndTaiXe;
        this.gplxTaiXe = gplxTaiXe;
        this.loaiXe = loaiXe;
        this.bienSo = bienSo;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.taiTrong = taiTrong;
        this.khoID = khoID;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public CungCapVanTai() {
    }

    public String getXeID() {
        return xeID;
    }

    public void setXeID(String xeID) {
        this.xeID = xeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTenTaiXe() {
        return tenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
    }

    public String getCmndTaiXe() {
        return cmndTaiXe;
    }

    public void setCmndTaiXe(String cmndTaiXe) {
        this.cmndTaiXe = cmndTaiXe;
    }

    public String getGplxTaiXe() {
        return gplxTaiXe;
    }

    public void setGplxTaiXe(String gplxTaiXe) {
        this.gplxTaiXe = gplxTaiXe;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    public int getTaiTrong() {
        return taiTrong;
    }

    public void setTaiTrong(int taiTrong) {
        this.taiTrong = taiTrong;
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
    public LatLng getLocation() {
        return new LatLng(kinhDo, viDo);
    }
}
