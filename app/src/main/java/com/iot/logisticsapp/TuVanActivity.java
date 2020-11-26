package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Model.User;

public class TuVanActivity extends AppCompatActivity {
    TextView tv_TuVanPhapLy, tv_GiaiPhapTaiChinh, tv_KienThucNongNghiep;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String vaiTro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tu_van);

        tv_TuVanPhapLy = findViewById(R.id.tv_TuVanPhapLy);
        tv_GiaiPhapTaiChinh = findViewById(R.id.tv_GiaiPhapTaiChinh);
        tv_KienThucNongNghiep = findViewById(R.id.tv_KienThucNongNghiep);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh Mục Chủ Đề Tư Vấn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        onclick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (value.exists()) {
                    User user = value.toObject(User.class);
                        vaiTro = user.getVaiTro().trim();
                }
            }
        });
    }

    private void onclick() {
        tv_TuVanPhapLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChiTietTuVanActivity.class);
                intent.putExtra("ChuDe","Tư Vấn Pháp Lý");
                intent.putExtra("vaiTro",vaiTro);
                startActivity(intent);
            }
        });
        tv_GiaiPhapTaiChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChiTietTuVanActivity.class);
                intent.putExtra("ChuDe","Giải Pháp Tài Chính");
                intent.putExtra("vaiTro",vaiTro);
                startActivity(intent);
            }
        });
        tv_KienThucNongNghiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChiTietTuVanActivity.class);
                intent.putExtra("ChuDe","Kiến Thức Nông Nghiệp");
                intent.putExtra("vaiTro",vaiTro);
                startActivity(intent);
            }
        });
    }
}