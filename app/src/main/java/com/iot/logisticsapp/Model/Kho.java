package com.iot.logisticsapp.Model;

import com.google.android.gms.maps.model.LatLng;

public class Kho {
    public double kinhDo;
    public double viDo;
    public int sucChua;
    public String khoID;
    public String loai;

    public Kho(double kinhDo, double viDo, int sucChua, String khoID, String loai) {
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.sucChua = sucChua;
        this.khoID = khoID;
        this.loai = loai;
    }

    public Kho() {
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

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public String getKhoID() {
        return khoID;
    }

    public void setKhoID(String khoID) {
        this.khoID = khoID;
    }

    public LatLng getLocation() {
        return new LatLng(kinhDo, viDo);
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
