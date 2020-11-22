package com.iot.logisticsapp.services.milk_delivery;

import android.util.Log;

import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.Model.Kho;

import java.util.List;

public class MilkDelivery {
    //    public void delivery(List<> ){
//
//    }
    private double[][] savingsMatrix;
    private int soLuongNoi;
    private int soLuongXe;
    private List<CungCapHangHoa> noiLays;
    private List<CungCapVanTai> xeList;
    private Kho kho;

    public void collect(List<CungCapHangHoa> _noiLays, Kho _kho, List<CungCapVanTai> _xeList) {
        noiLays = _noiLays;
        xeList = _xeList;
        kho = _kho;
        soLuongNoi = _noiLays.size();
        savingsMatrix = new double[soLuongNoi][soLuongNoi];
        computeSavingsMatrix();
        showSavingsMatrix();
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private double saving(double x1, double y1, double x2, double y2, double xRoot, double yRoot) {
        return distance(x1, y1, xRoot, yRoot) + distance(x2, y2, xRoot, yRoot) - distance(x1, y1, x2, y2);
    }

    private void computeSavingsMatrix() {
        for (int i = 0; i < soLuongNoi; i++) {
            for (int j = i + 1; j < soLuongNoi; j++) {
                savingsMatrix[i][j] = saving(noiLays.get(i).kinhDo, noiLays.get(i).viDo, noiLays.get(j).kinhDo, noiLays.get(j).viDo, kho.getKinhDo(), kho.getViDo());
            }
        }
    }

    public void showSavingsMatrix() {
        String s = "";
        for (int i = 0; i < soLuongNoi; i++) {
            for (int j = 0; j < soLuongNoi; j++) {
                s += Math.round(savingsMatrix[i][j]) + "\t";
            }
            Log.d("TESTLIGHT", s + "\n");
            s = "";
        }
    }
}
