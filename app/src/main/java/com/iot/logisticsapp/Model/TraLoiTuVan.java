package com.iot.logisticsapp.Model;

public class TraLoiTuVan {
    String userId;
    String cauTraLoi;
    String cauTraLoiID;
    String  tenUser;

    public TraLoiTuVan(String userId, String cauTraLoi, String cauTraLoiID, String tenUser) {
        this.userId = userId;
        this.cauTraLoi = cauTraLoi;
        this.cauTraLoiID = cauTraLoiID;
        this.tenUser = tenUser;
    }

    public TraLoiTuVan() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }

    public String getCauTraLoiID() {
        return cauTraLoiID;
    }

    public void setCauTraLoiID(String cauTraLoiID) {
        this.cauTraLoiID = cauTraLoiID;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }
}
