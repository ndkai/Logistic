package com.iot.logisticsapp.Model;

public class CungCapNhanLuc {
    public String tenUser;
    public String sdtUser ;
    public String diachiUser ;
    public String noiĐkThamGia ;
    public String vaitro ;

    public CungCapNhanLuc(String tenUser, String sdtUser, String diachiUser, String noiĐkThamGia, String vaitro) {
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diachiUser = diachiUser;
        this.noiĐkThamGia = noiĐkThamGia;
        this.vaitro = vaitro;
    }

    public CungCapNhanLuc() {
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

    public String getNoiĐkThamGia() {
        return noiĐkThamGia;
    }

    public void setNoiĐkThamGia(String noiĐkThamGia) {
        this.noiĐkThamGia = noiĐkThamGia;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }
}
