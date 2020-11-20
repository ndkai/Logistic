package com.iot.logisticsapp.Model;

public class NguoiNhanCuuTro {
    public String tenUser ;
    public String sdtUser ;
    public String diaChiUser ;
    public String vanDe ;
    public String canGiupDo ;
    public String chiTietTinhTrang ;

    public NguoiNhanCuuTro(String tenUser, String sdtUser, String diaChiUser, String vanDe, String canGiupDo, String chiTietTinhTrang) {
        this.tenUser = tenUser;
        this.sdtUser = sdtUser;
        this.diaChiUser = diaChiUser;
        this.vanDe = vanDe;
        this.canGiupDo = canGiupDo;
        this.chiTietTinhTrang = chiTietTinhTrang;
    }

    public NguoiNhanCuuTro() {
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
}
