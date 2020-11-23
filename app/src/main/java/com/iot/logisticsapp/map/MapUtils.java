package com.iot.logisticsapp.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapUtils {

    public static void addVertex(GoogleMap map, LatLng vert, String name){
        MarkerOptions  place = new MarkerOptions().position(vert).title(name);
        map.addMarker(place);
    }


    public static String getRouting(LatLng origin, LatLng dest) {
        String directionMode = "driving";
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyA24y-SkYjKWPUXv_qLnnyyP3jfZzB4oU0";
        return url;
    }
}
