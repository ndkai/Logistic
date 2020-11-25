package com.iot.logisticsapp.Model;

public class TuVan {
    String userID;
    String cauHoi;
    String cauHoiID;
    String chuDe;
    String tenUser;
    String sdtUser;

    public TuVan(String userID, String cauHoi, String cauHoiID, String chuDe, String tenUser, String sdtUser) {
        this.userID = userID;
        this.cauHoi = cauHoi;
        this.cauHoiID = cauHoiID;
        this.chuDe = chuDe;
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
    }

    public TuVan() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getCauHoiID() {
        return cauHoiID;
    }

    public void setCauHoiID(String cauHoiID) {
        this.cauHoiID = cauHoiID;
    }

    public String getChuDe() {
        return chuDe;
    }

    public void setChuDe(String chuDe) {
        this.chuDe = chuDe;
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
}
