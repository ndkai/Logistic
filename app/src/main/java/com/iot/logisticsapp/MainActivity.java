package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    TextView tv_sđtUser, tv_tenUser, tv_diachiUser, tv_chuyenVaiTro;
    CheckBox cb_CCHH, cb_CCNNL, cb_CCDVVT;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tenUser = findViewById(R.id.tv_tenUser);
        tv_sđtUser = findViewById(R.id.tv_sđtUser);
        tv_diachiUser = findViewById(R.id.tv_diachiUser);
        tv_chuyenVaiTro = findViewById(R.id.tv_chuyenVaiTro);
        cb_CCHH = findViewById(R.id.cb_CCHH);
        cb_CCNNL = findViewById(R.id.cb_CCNNL);
        cb_CCDVVT = findViewById(R.id.cb_CCDVVT);

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
    }

    public void onClick(){
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
                startActivity(new Intent(getApplicationContext(),Main_NguoiNhanCuuTroActivity.class));
            }
        });

    }

    public void Next(View view){
        if(cb_CCHH.isChecked()){
            startActivity(new Intent(getApplicationContext(),CungCapHangHoaAcitivity.class));
        } else if(cb_CCNNL.isChecked()){
            startActivity(new Intent(getApplicationContext(),CungCapNhanLucActivity.class));
        } else  startActivity(new Intent(getApplicationContext(),CungCapVanTaiActivity.class));
    }
}