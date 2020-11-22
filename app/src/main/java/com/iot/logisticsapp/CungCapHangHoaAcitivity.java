package com.iot.logisticsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iot.logisticsapp.Fragment.TinhTrang_CungCapHangHoaFragment;
import com.iot.logisticsapp.Fragment.TrangChu_CungCapHangHoaFragment;

public class CungCapHangHoaAcitivity extends AppCompatActivity {

    private static final String TAG = "CungCapHangHoaActivity";

    BottomNavigationView bottomNavigationView;
    Fragment selecFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cung_cap_hang_hoa_acitivity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TrangChu_CungCapHangHoaFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_trangchu:
                            selecFragment = new TrangChu_CungCapHangHoaFragment();
                            break;
                        case R.id.nav_tinhtrang:
                            selecFragment = new TinhTrang_CungCapHangHoaFragment();
                            Log.d(TAG,"error : " );
                            break;
                        case R.id.nav_feedback:
                            Toast.makeText(CungCapHangHoaAcitivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                        case R.id.nav_hotro:
                            Toast.makeText(CungCapHangHoaAcitivity.this, "Danh Sách Nhà Hảo Tâm", Toast.LENGTH_SHORT).show();

                    }
                    if(selecFragment!=null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selecFragment).commit();
                    }
                    return true;
                }
            };


}