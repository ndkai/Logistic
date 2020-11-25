package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
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
import java.util.Arrays;
import java.util.List;

public class Kho_ChiTietHangHoaActivity extends AppCompatActivity {
    private RecyclerView recyclerView, recyclerView1;
    private chiTietHangHoaAdapter chiTietHangHoaAdapter;
    private List<CungCapHangHoa> cungCapHangHoaList;
    private chiTietHang_YeuCauCuuTroAdapter chiTietHang_yeuCauCuuTroAdapter;
    private List<NguoiNhanCuuTro> nguoiNhanCuuTroList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    String cungCapHangHoaID, dotCuuTroID, chucNang, vaiTro;

    TextView sdtNguoiCungCap, tenNguoiCungCap, diaChiNguoiCungCap;

    TextView tv_capNhatTinhTrang, tv_danhsachTinhTrang,tv_theoDoiYeuCau;
    Button btn_capNhatTinhTrang, btn_theoDoiYeuCau;
    LinearLayout ln_theoDoiYeuCau, ln_capNhatTinhTrang;
    private CollectionReference cungCapHangHoaRef = db.collection("CungCapHangHoa");
    private CollectionReference nguoiNhanCuuTroRef = db.collection("NguoiNhanCuuTro");

    String t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kho__chi_tiet_hang_hoa);

        tenNguoiCungCap = findViewById(R.id.tenNguoiCungCap);
        sdtNguoiCungCap = findViewById(R.id.sdtNguoiCungCap);
        diaChiNguoiCungCap = findViewById(R.id.diaChiNguoiCungCap);


        tv_capNhatTinhTrang = findViewById(R.id.tv_capNhatTinhTrang);
        ln_capNhatTinhTrang = findViewById(R.id.ln_capNhatTinhTrang);
        tv_danhsachTinhTrang = findViewById(R.id.tv_danhsachTinhTrang);
        btn_capNhatTinhTrang = findViewById(R.id.btn_capNhatTinhTrang);

        tv_theoDoiYeuCau = findViewById(R.id.tv_theoDoiYeuCau);
        btn_theoDoiYeuCau = findViewById(R.id.btn_theoDoiYeuCau);
        ln_theoDoiYeuCau = findViewById(R.id.ln_theoDoiYeuCau);



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
        chiTietHangHoaAdapter= new chiTietHangHoaAdapter(getApplicationContext(),cungCapHangHoaList);
        recyclerView.setAdapter(chiTietHangHoaAdapter);

        recyclerView1 = findViewById(R.id.recycle_view1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(linearLayoutManager1);

        nguoiNhanCuuTroList = new ArrayList<>();
        chiTietHang_yeuCauCuuTroAdapter= new chiTietHang_YeuCauCuuTroAdapter(getApplicationContext(),nguoiNhanCuuTroList);
        recyclerView1.setAdapter(chiTietHang_yeuCauCuuTroAdapter);

        Intent intent = getIntent();
        cungCapHangHoaID = intent.getStringExtra("cungCapHangHoaID");
        dotCuuTroID = intent.getStringExtra("dotCuuTroID");
        chucNang = intent.getStringExtra("chucNang");
        vaiTro = intent.getStringExtra("vaiTro");

        onClick();


        Log.d("chiTietHangHoaActivity", "error : ");
    }

    private void onClick() {

        btn_capNhatTinhTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    switch (tv_capNhatTinhTrang.getText().toString().trim()){
                        case "Đã Xử Lý": t = "Đang Xử Lý, Đã Xử Lý";
                            break;
                        case "Đã Nhận" : t = "Đang Xử Lý, Đã Xử Lý, Đã Nhận";
                            break;
                        case "Đã Nhập Kho" : t = "Đang Xử Lý, Đã Xử Lý, Đã Nhận, Đã Nhập Kho";
                            break;
                        case "Đã Hoàn Thành" : t = "Đang Xử Lý, Đã Xử Lý, Đã Nhận, Đã Nhập Kho, Đã Hoàn Thành";
                            break;

                    }
                    String[] chiTiet = t.split("\\s*,\\s*");
                    List<String> chiTietTinhTrang = Arrays.asList(chiTiet);
                    cungCapHangHoaRef.document(cungCapHangHoaID).update("tinhTrangVanChuyen", tv_capNhatTinhTrang.getText().toString().trim());
                    cungCapHangHoaRef.document(cungCapHangHoaID).update("chiTietTinhTrang", chiTietTinhTrang);
            }
        });

        btn_theoDoiYeuCau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (tv_theoDoiYeuCau.getText().toString().trim()){
                    case "Đã Xử Lý": t = "Đang Xử Lý, Đã Xử Lý";
                        break;
                    case "Đã Nhận Hàng" : t = "Đang Xử Lý, Đã Xử Lý, Đã Nhận Hàng";
                        break;

                }
                String[] theoDoi = t.split("\\s*,\\s*");
                List<String> theoDoiYeuCau = Arrays.asList(theoDoi);
                nguoiNhanCuuTroRef.document(dotCuuTroID).update("tinhTrang", tv_theoDoiYeuCau.getText().toString().trim());
                nguoiNhanCuuTroRef.document(dotCuuTroID).update("theoDoiYeuCau", theoDoiYeuCau);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if(chucNang.equals("cungCapHangHoa")){
            diaChiNguoiCungCap.setVisibility(View.GONE);
            ln_theoDoiYeuCau.setVisibility(View.GONE);
            ln_capNhatTinhTrang.setVisibility(View.VISIBLE);
            DocumentReference cungCapHangHoaRef = db.document("CungCapHangHoa/"+cungCapHangHoaID);
            cungCapHangHoaRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    String data = "";
                    int dem = 1 ;
                    if(error!=null){return;}
                    if (value.exists()) {
                        cungCapHangHoaList.clear();
                        CungCapHangHoa cungCapHangHoa = value.toObject(CungCapHangHoa.class);
                        cungCapHangHoaList.add(cungCapHangHoa);
                        chiTietHangHoaAdapter.notifyDataSetChanged();

                        sdtNguoiCungCap.setText("SĐT : " + cungCapHangHoa.getSdtUser());
                        tenNguoiCungCap.setText("Tên người cung cấp : " + cungCapHangHoa.getTenUser());
                        switch (cungCapHangHoa.getTinhTrangVanChuyen()){
                            case "Đang Xử Lý" : tv_capNhatTinhTrang.setText("Đã Xử Lý");
                                break;
                            case "Đã Xử Lý" : tv_capNhatTinhTrang.setText("Đã Nhận");
                                break;
                            case "Đã Nhận" : tv_capNhatTinhTrang.setText("Đã Nhập Kho");
                                break;
                            case "Đã Nhập Kho" : tv_capNhatTinhTrang.setText("Đã Hoàn Thành");
                                break;
                            case "Đã Hoàn Thành" :  ln_capNhatTinhTrang.setVisibility(View.GONE);

                        }

                        for (String danhSachTinhTrang : cungCapHangHoa.getChiTietTinhTrang()){
                            data += dem + "    ---    " + danhSachTinhTrang + "\n\n";
                            dem++;
                        }
                        tv_danhsachTinhTrang.setText(data);

                    }

                }
            });
        } else {
            ln_capNhatTinhTrang.setVisibility(View.GONE);
            ln_theoDoiYeuCau.setVisibility(View.VISIBLE);
            DocumentReference yeuCauCuuTro = db.document("NguoiNhanCuuTro/"+dotCuuTroID);
            yeuCauCuuTro.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    String data = "";
                    int dem = 1;
                    if(error!=null){return;}
                    if (value.exists()) {
                        nguoiNhanCuuTroList.clear();
                        NguoiNhanCuuTro nguoiNhanCuuTro = value.toObject(NguoiNhanCuuTro.class);
                        nguoiNhanCuuTroList.add(nguoiNhanCuuTro);
                        chiTietHang_yeuCauCuuTroAdapter.notifyDataSetChanged();

                        sdtNguoiCungCap.setText("SĐT : " + nguoiNhanCuuTro.getSdtUser());
                        tenNguoiCungCap.setText("Tên người yêu cầu : " + nguoiNhanCuuTro.getTenUser());
                        diaChiNguoiCungCap.setText("Vị trí : " + nguoiNhanCuuTro.getDiaChiUser());

                        switch (nguoiNhanCuuTro.getTinhTrang()){
                            case "Đang Xử Lý" : tv_theoDoiYeuCau.setText("Đã Xử Lý");
                                break;
                            case "Đã Xử Lý" : tv_theoDoiYeuCau.setText("Đã Nhận Hàng");
                                break;
                            case "Đã Nhận Hàng" : ln_theoDoiYeuCau.setVisibility(View.GONE);
                                break;

                        }

                        for (String danhSachTinhTrang : nguoiNhanCuuTro.getTheoDoiYeuCau()){
                            data += dem + "    ---    " + danhSachTinhTrang + "\n\n";
                            dem++;
                        }
                        tv_danhsachTinhTrang.setText(data);


                    }

                }
            });
        }

    }

}