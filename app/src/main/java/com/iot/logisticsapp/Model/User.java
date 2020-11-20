package com.iot.logisticsapp.Model;

public class User {
    public String userID;
    public String name;
    public String phone;
    public String address;

    public User(String userID, String name, String phone, String address) {
        this.userID = userID;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
