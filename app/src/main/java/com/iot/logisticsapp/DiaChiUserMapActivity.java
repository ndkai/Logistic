package com.iot.logisticsapp;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DiaChiUserMapActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 2;
    Button btnShowlocation;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Location_Tracker gps;

    double la, longi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_user_map);
       /* try {
            if(ActivityCompat.checkSelfPermission(this,mPermission)!= PackageManager.PERMISSION_GRANTED)
            {  ActivityCompat.requestPermissions(this,new String[]{mPermission},REQUEST_CODE_PERMISSION); }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;
        locationManager.requestLocationUpdates(provider, 1000, 20, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                la = location.getLatitude();
                longi = location.getLongitude();
            }
        });
*/
        btnShowlocation = findViewById(R.id.button);
        TextView tx = findViewById(R.id.textView2);
        btnShowlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  gps = new Location_Tracker(DiaChiUserMapActivity.this) {
                    @Nullable
                    @Override
                    public IBinder onBind(Intent intent) {
                        return null;
                    }
                };
                if(gps.canGetLocation()){
                    double lattitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Toast.makeText(DiaChiUserMapActivity.this, "This is your location"+lattitude+" + " +longitude, Toast.LENGTH_SHORT).show();
                }
                else{
                    gps.showSetting();
                }

                Toast.makeText(getApplicationContext(), ""+la+"hehe"+longi, Toast.LENGTH_SHORT).show();
      */        String address = tx.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(address,getApplicationContext(),new GeoHandler());
                Log.d("TAG","error : ");
            }
        });


    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            switch (msg.what){
                case 1: Bundle bundle = msg.getData();
                address = bundle.getString("address");
                break;
                default:
                    address = null; 
            }
            Toast.makeText(getApplicationContext(), "hehe "+address, Toast.LENGTH_SHORT).show();
        }
    }
}