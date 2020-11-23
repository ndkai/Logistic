package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.Model.Kho;
import com.iot.logisticsapp.services.milk_delivery.MilkDelivery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_sđtUser, tv_tenUser, tv_diachiUser, tv_chuyenVaiTro;
    CheckBox cb_CCHH, cb_CCNNL, cb_CCDVVT;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tenUser = findViewById(R.id.tv_tenUser);
        tv_sđtUser = findViewById(R.id.tv_sđtUser);
        tv_diachiUser = findViewById(R.id.tv_diachiUser);
        tv_chuyenVaiTro = findViewById(R.id.tv_chuyenVaiTro);
//        cb_CCHH = findViewById(R.id.cb_CCHH);
//        cb_CCNNL = findViewById(R.id.cb_CCNNL);
//        cb_CCDVVT = findViewById(R.id.cb_CCDVVT);

        onClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(MainActivity.this, "Error Load", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value.exists()) {
                    tv_tenUser.setText(value.getString("name"));
                    tv_diachiUser.setText(value.getString("address"));
                    tv_sđtUser.setText(value.getString("phone"));

                }
            }
        });

        List<CungCapHangHoa> list = new ArrayList<>();
        CungCapHangHoa c1 = new CungCapHangHoa();
        c1.setKinhDo(0);
        c1.setViDo(12);
        c1.setKhoiLuongItem(48);
        list.add(c1);

        CungCapHangHoa c2 = new CungCapHangHoa();
        c2.setKinhDo(6);
        c2.setViDo(5);
        c2.setKhoiLuongItem(60);
        list.add(c2);

        CungCapHangHoa c3 = new CungCapHangHoa();
        c3.setKinhDo(7);
        c3.setViDo(15);
        c3.setKhoiLuongItem(43);
        list.add(c3);

        CungCapHangHoa c4 = new CungCapHangHoa();
        c4.setKinhDo(9);
        c4.setViDo(12);
        c4.setKhoiLuongItem(92);
        list.add(c4);

        CungCapHangHoa c5 = new CungCapHangHoa();
        c5.setKinhDo(15);
        c5.setViDo(3);
        c5.setKhoiLuongItem(80);
        list.add(c5);

        Kho kho = new Kho(0, 0, 9999);

        CungCapVanTai xe1 = new CungCapVanTai();
        xe1.setTaiTrong(10);
        CungCapVanTai xe2 = new CungCapVanTai();
        xe2.setTaiTrong(10);
        List<CungCapVanTai> xeList = new ArrayList<>();
        xeList.add(xe1);
        xeList.add(xe2);

        MilkDelivery milkDelivery = new MilkDelivery();
        milkDelivery.collect(list, kho, xeList);
    }

    public void onClick() {
        cb_CCHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb_CCDVVT.setChecked(false);
                cb_CCNNL.setChecked(false);
            }
        });
        cb_CCNNL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb_CCHH.setChecked(false);
                cb_CCDVVT.setChecked(false);
            }
        });
        cb_CCDVVT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb_CCHH.setChecked(false);
                cb_CCNNL.setChecked(false);
            }
        });
        tv_chuyenVaiTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Main_NguoiNhanCuuTroActivity.class));
            }
        });

    }

    public void Next(View view) {
        if (cb_CCHH.isChecked()) {
            startActivity(new Intent(getApplicationContext(), CungCapHangHoaAcitivity.class));
        } else if (cb_CCNNL.isChecked()) {
            startActivity(new Intent(getApplicationContext(), CungCapNhanLucActivity.class));
        } else startActivity(new Intent(getApplicationContext(), CungCapVanTaiActivity.class));
    }
}