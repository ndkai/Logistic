package com.iot.logisticsapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iot.logisticsapp.Fragment.TrangChu_NguoiNhanCuuTroFragment;
import com.iot.logisticsapp.Fragment.YeuCau_NguoiNhanCuuTroFragment;

public class Main_NguoiNhanCuuTroActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selecFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__nguoi_nhan_cuu_tro);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TrangChu_NguoiNhanCuuTroFragment()).commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_trangchu:
                            selecFragment = new TrangChu_NguoiNhanCuuTroFragment();
                            break;
                        case R.id.nav_yeucau:
                            selecFragment = new YeuCau_NguoiNhanCuuTroFragment();
                            break;
                        case R.id.nav_feedback:
                            Toast.makeText(Main_NguoiNhanCuuTroActivity.this, "Feedback", Toast.LENGTH_SHORT).show();

                    }
                    if(selecFragment!=null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selecFragment).commit();
                    }
                    return true;
                }
            };



}