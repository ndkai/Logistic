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
import com.iot.logisticsapp.Adapter.traLoiTuVanAdapter;
import com.iot.logisticsapp.Model.TraLoiTuVan;
import com.iot.logisticsapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class TraLoiTuVanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private traLoiTuVanAdapter traLoiTuVanAdapter;
    private List<TraLoiTuVan> traLoiTuVanList;

    public static EditText add_cauHoi;
    public static TextView gui;

    String cauHoiId;
    String tenUser;
    TextView tenNguoiDatCauHoi, sdtNguoiDatCauHoi, cauHoiNguoiDatCauHoi;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tra_loi_tu_van);

        tenNguoiDatCauHoi = findViewById(R.id.tenNguoiDatCauHoi);
        sdtNguoiDatCauHoi = findViewById(R.id.sdtNguoiDatCauHoi);
        cauHoiNguoiDatCauHoi = findViewById(R.id.cauHoiNguoiDatCauHoi);

        Intent intent = getIntent();
        cauHoiId = intent.getStringExtra("CauHoiID");
        tenNguoiDatCauHoi.setText(intent.getStringExtra("tenNguoiDatCauHoi"));
        sdtNguoiDatCauHoi.setText(intent.getStringExtra("sdtNguoiDatCauHoi"));
        cauHoiNguoiDatCauHoi.setText("Câu Hỏi : " + intent.getStringExtra("cauHoiNguoiDatCauHoi"));




        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        traLoiTuVanList = new ArrayList<>();
        traLoiTuVanAdapter = new traLoiTuVanAdapter(this, traLoiTuVanList);
        recyclerView.setAdapter(traLoiTuVanAdapter);

        add_cauHoi = findViewById(R.id.add_cauHoi);
        gui = findViewById(R.id.gui);


        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add_cauHoi.getText().toString().equals("")) {
                    Toast.makeText(TraLoiTuVanActivity.this, "Bạn Chưa Nhập Nội Dung Câu Hỏi", Toast.LENGTH_SHORT).show();
                } else {
                    noidungCauHoi();
                    add_cauHoi.setText("");
                }
            }
        });
        getUser();

        Log.d("TAG", "error : ");


    }

    @Override
    protected void onStart() {
        super.onStart();

        db.collection("TraLoiTuVan").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    traLoiTuVanList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        TraLoiTuVan traLoiTuVan = documentSnapshot.toObject(TraLoiTuVan.class);


                        if (traLoiTuVan.getCauTraLoiID().equals(cauHoiId)) {
                            traLoiTuVanList.add(traLoiTuVan);
                            traLoiTuVanAdapter.notifyDataSetChanged();

                        }

                    }
                }
            }
        });
    }


    private void noidungCauHoi() {

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String cauTraLoi = add_cauHoi.getText().toString().trim();
        TraLoiTuVan traLoiTuVan = new TraLoiTuVan(userID, cauTraLoi, cauHoiId, tenUser);
        db.collection("TraLoiTuVan").add(traLoiTuVan).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(TraLoiTuVanActivity.this, "Đăng Câu Trả Lời Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUser() {
        db.collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (value.exists()) {
                    User user = value.toObject(User.class);
                    tenUser = user.getName();
                }
            }
        });
    }


}