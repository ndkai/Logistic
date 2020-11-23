package com.iot.logisticsapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iot.logisticsapp.Fragment.DanhSachHangHoaFragment;
import com.iot.logisticsapp.Fragment.DanhSachNguoiCanCuuTroFragment;
import com.iot.logisticsapp.Fragment.DanhSachPhuongTienFragment;

public class KhoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selecFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kho);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new DanhSachHangHoaFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_danhsach_hanghoa:
                            selecFragment = new DanhSachHangHoaFragment();

                            break;
                        case R.id.nav_danhsach_xe:
                            selecFragment = new DanhSachPhuongTienFragment();
                            break;
                        case R.id.nav_danhsach_nguoicancuutro:
                            selecFragment = new DanhSachNguoiCanCuuTroFragment();
                            break;

                    }
                    if(selecFragment!=null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selecFragment).commit();
                    }
                    return true;
                }
            };
}