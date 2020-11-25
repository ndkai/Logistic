package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Adapter.chiTietHangHoaAdapter;
import com.iot.logisticsapp.Adapter.chiTietHang_YeuCauCuuTroAdapter;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;

import java.util.ArrayList;
import java.util.List;

public class chiTietHangHoaActivity extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerView1;
    private chiTietHangHoaAdapter chiTietHangHoaAdapter;
    private List<CungCapHangHoa> cungCapHangHoaList;
    private chiTietHang_YeuCauCuuTroAdapter chiTietHang_yeuCauCuuTroAdapter;
    private List<NguoiNhanCuuTro> nguoiNhanCuuTroList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    String cungCapHangHoaID, dotCuuTroID, chucNang, vaiTro;

    TextView sdtNguoiCungCap, tenNguoiCungCap, diaChiNguoiCungCap;

    TextView tv_danhsachTinhTrang;


    String t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hang_hoa);

        tenNguoiCungCap = findViewById(R.id.tenNguoiCungCap);
        sdtNguoiCungCap = findViewById(R.id.sdtNguoiCungCap);
        diaChiNguoiCungCap = findViewById(R.id.diaChiNguoiCungCap);


        tv_danhsachTinhTrang = findViewById(R.id.tv_danhsachTinhTrang);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết hàng hóa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        cungCapHangHoaList = new ArrayList<>();
        chiTietHangHoaAdapter = new chiTietHangHoaAdapter(getApplicationContext(), cungCapHangHoaList);
        recyclerView.setAdapter(chiTietHangHoaAdapter);

        recyclerView1 = findViewById(R.id.recycle_view1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(linearLayoutManager1);

        nguoiNhanCuuTroList = new ArrayList<>();
        chiTietHang_yeuCauCuuTroAdapter = new chiTietHang_YeuCauCuuTroAdapter(getApplicationContext(), nguoiNhanCuuTroList);
        recyclerView1.setAdapter(chiTietHang_yeuCauCuuTroAdapter);

        Intent intent = getIntent();
        cungCapHangHoaID = intent.getStringExtra("cungCapHangHoaID");
        dotCuuTroID = intent.getStringExtra("dotCuuTroID");
        chucNang = intent.getStringExtra("chucNang");
        vaiTro = intent.getStringExtra("vaiTro");


        Log.d("chiTietHangHoaActivity", "error : ");
    }


    @Override
    public void onStart() {
        super.onStart();
        if (chucNang.equals("cungCapHangHoa")) {
            diaChiNguoiCungCap.setVisibility(View.GONE);
            DocumentReference cungCapHangHoaRef = db.document("CungCapHangHoa/" + cungCapHangHoaID);
            cungCapHangHoaRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    String data = "";
                    int dem = 1;
                    if (error != null) {
                        return;
                    }
                    if (value.exists()) {
                        cungCapHangHoaList.clear();
                        CungCapHangHoa cungCapHangHoa = value.toObject(CungCapHangHoa.class);
                        cungCapHangHoaList.add(cungCapHangHoa);
                        chiTietHangHoaAdapter.notifyDataSetChanged();

                        sdtNguoiCungCap.setText("SĐT : " + cungCapHangHoa.getSdtUser());
                        tenNguoiCungCap.setText("Tên người cung cấp : " + cungCapHangHoa.getTenUser());

                        for (String danhSachTinhTrang : cungCapHangHoa.getChiTietTinhTrang()) {
                            data += dem + "-" + danhSachTinhTrang + "\n";
                            dem++;
                        }
                        tv_danhsachTinhTrang.setText(data);

                    }

                }
            });
        } else {
            DocumentReference yeuCauCuuTro = db.document("NguoiNhanCuuTro/" + dotCuuTroID);
            yeuCauCuuTro.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    String data = "";
                    int dem = 1;
                    if (error != null) {
                        return;
                    }
                    if (value.exists()) {
                        nguoiNhanCuuTroList.clear();
                        NguoiNhanCuuTro nguoiNhanCuuTro = value.toObject(NguoiNhanCuuTro.class);
                        nguoiNhanCuuTroList.add(nguoiNhanCuuTro);
                        chiTietHang_yeuCauCuuTroAdapter.notifyDataSetChanged();

                        sdtNguoiCungCap.setText("SĐT : " + nguoiNhanCuuTro.getSdtUser());
                        tenNguoiCungCap.setText("Tên người yêu cầu : " + nguoiNhanCuuTro.getTenUser());
                        diaChiNguoiCungCap.setText("Vị trí : " + nguoiNhanCuuTro.getDiaChiUser());

                        for (String danhSachTinhTrang : nguoiNhanCuuTro.getTheoDoiYeuCau()) {
                            data += dem + "-" + danhSachTinhTrang + "\n";
                            dem++;
                        }
                        tv_danhsachTinhTrang.setText(data);
                    }

                }
            });
        }

    }

}