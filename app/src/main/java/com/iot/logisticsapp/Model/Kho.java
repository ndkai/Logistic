package com.iot.logisticsapp.Model;

public class Kho {
    private double KinhDo;
    private double ViDo;
    private int sucChua;

    public Kho(double kinhDo, double viDo, int sucChua) {
        KinhDo = kinhDo;
        ViDo = viDo;
        this.sucChua = sucChua;
    }

    public double getKinhDo() {
        return KinhDo;
    }

    public void setKinhDo(double kinhDo) {
        KinhDo = kinhDo;
    }

    public double getViDo() {
        return ViDo;
    }

    public void setViDo(double viDo) {
        ViDo = viDo;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }
}
