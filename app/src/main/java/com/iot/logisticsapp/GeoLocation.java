package com.iot.logisticsapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GeoLocation {
    public static void getAddress(String locationAddress, Context context, Handler handler){
        Thread thread =new Thread(){
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String vido = null;
                String kinhdo = null;
                try {
                    List addressList = geocoder.getFromLocationName(locationAddress,1);
                    if(addressList!=null && addressList.size()>0){
                        Address address = (Address) addressList.get(0);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(address.getLatitude());
                        vido = stringBuilder.toString();
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append(address.getLongitude());
                        kinhdo = stringBuilder1.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if(vido!=null&&kinhdo!=null){
                        message.what =1 ;
                        Bundle bundle = new Bundle();
                        bundle.putString("vido",vido);
                        bundle.putString("kinhdo",kinhdo);
                        message.setData(bundle);
                    }
                    message.sendToTarget();

                }
            }
        };
        thread.start();
    }
}
