package com.iot.logisticsapp.Model;

public class DuAn {
    String id;
    String ten;
    int tongCuuTro;
    int soLuongHienTai;

    public DuAn(String id, String ten, int tongCuuTro, int soLuongHienTai) {
        this.id = id;
        this.ten = ten;
        this.tongCuuTro = tongCuuTro;
        this.soLuongHienTai = soLuongHienTai;
    }

    public DuAn() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTongCuuTro() {
        return tongCuuTro;
    }

    public void setTongCuuTro(int tongCuuTro) {
        this.tongCuuTro = tongCuuTro;
    }

    public int getSoLuongHienTai() {
        return soLuongHienTai;
    }

    public void setSoLuongHienTai(int soLuongHienTai) {
        this.soLuongHienTai = soLuongHienTai;
    }
}
