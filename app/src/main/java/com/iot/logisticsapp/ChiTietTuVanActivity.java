package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.Adapter.tuVanAdapter;
import com.iot.logisticsapp.Model.TuVan;
import com.iot.logisticsapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ChiTietTuVanActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private com.iot.logisticsapp.Adapter.tuVanAdapter tuVanAdapter;
    private List<TuVan> tuVanList;

    public static EditText add_cauHoi;
    public static TextView gui;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String tenUser, sdtUser;

    String chuDe, vaiTro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chi_tiet_tu_van);

        Intent intent = getIntent();
        chuDe = intent.getStringExtra("ChuDe");
        vaiTro = intent.getStringExtra("vaiTro");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(chuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        tuVanList = new ArrayList<>();
        tuVanAdapter = new tuVanAdapter(this,tuVanList);
        recyclerView.setAdapter(tuVanAdapter);

        add_cauHoi = findViewById(R.id.add_cauHoi);
        gui = findViewById(R.id.gui);



        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(add_cauHoi.getText().toString().equals("")){
                    Toast.makeText(ChiTietTuVanActivity.this, "Bạn Chưa Nhập Nội Dung Câu Hỏi", Toast.LENGTH_SHORT).show();
                } else {
                    noidungCauHoi();
                    add_cauHoi.setText("");
                }
            }
        });
        getUser();

        if (!vaiTro.equals("Tư Vấn")){
            add_cauHoi.setVisibility(View.VISIBLE);
            gui.setVisibility(View.VISIBLE);
        }

        Log.d("Hao","error : ");


    }

    @Override
    protected void onStart() {
        super.onStart();

        db.collection("TuVan").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (!value.isEmpty()) {
                    tuVanList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value){
                        TuVan tuVan = documentSnapshot.toObject(TuVan.class);
                        if(vaiTro.equals("Tư Vấn") &&tuVan.getChuDe().equals(chuDe)){
                            tuVanList.add(tuVan);
                            tuVanAdapter.notifyDataSetChanged();
                        }else {

                            if(tuVan.getUserID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    &&tuVan.getChuDe().equals(chuDe)){
                                tuVan.setCauHoiID(documentSnapshot.getId());
                                db.collection("TuVan").document(tuVan.getCauHoiID()).update("cauHoiID",tuVan.getCauHoiID());
                                tuVanList.add(tuVan);
                                tuVanAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                }
            }
        });
    }


    private void noidungCauHoi() {

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String cauHoi = add_cauHoi.getText().toString().trim();
        String cauHoiID = "";
        String chuDeCauHoi = chuDe;
        TuVan tuVan = new TuVan(userID,cauHoi,cauHoiID,chuDeCauHoi,tenUser,sdtUser);
        db.collection("TuVan").add(tuVan).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ChiTietTuVanActivity.this, "Đăng Câu Hỏi Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUser(){
        db.collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (value.exists()) {
                    User user = value.toObject(User.class);
                    tenUser = user.getName();
                    sdtUser = user.getPhone();
                }
            }
        });
    }


}