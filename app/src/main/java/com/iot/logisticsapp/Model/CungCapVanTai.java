package com.iot.logisticsapp.Model;

public class CungCapVanTai {
    public String tenTaiXe;
    public String cmndTaiXe ;
    public String gplxTaiXe;
    public String loaiXe ;
    public String bienSo ;
    public String tuNgay ;
    public String denNgay ;
    public int taiTrong ;

    public CungCapVanTai(String tenTaiXe, String cmndTaiXe, String gplxTaiXe, String loaiXe, String bienSo, String tuNgay, String denNgay, int taiTrong) {
        this.tenTaiXe = tenTaiXe;
        this.cmndTaiXe = cmndTaiXe;
        this.gplxTaiXe = gplxTaiXe;
        this.loaiXe = loaiXe;
        this.bienSo = bienSo;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.taiTrong = taiTrong;
    }

    public CungCapVanTai() {
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
}
