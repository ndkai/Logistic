package com.iot.logisticsapp;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.Adapter.duAnAdapter;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.DuAn;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDuAnActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tv_gtcn;
    private TextView tv_gttc;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference duAnRef = db.collection("DuAn");
    private CollectionReference cungCapHangHoaRef = db.collection("CungCapHangHoa");
    private DocumentReference userRef = db.document("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

    int tongGiaTriCaNhan = 0;
    int tongGiaTriTatCa = 0;
    List<DuAn> duAnList;
    List<CungCapHangHoa> cungCapHangHoaList;
    List<Integer> soLuongThamGiaList;
    duAnAdapter duAnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_danh_sach_du_an);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        duAnList = new ArrayList<>();
        cungCapHangHoaList = new ArrayList<>();
        soLuongThamGiaList = new ArrayList<>();

        duAnAdapter = new duAnAdapter(this, duAnList, soLuongThamGiaList);
        recyclerView.setAdapter(duAnAdapter);

        tv_gtcn = findViewById(R.id.tv_quyenGopCaNhan);
        tv_gttc = findViewById(R.id.tv_quyenGopTatCa);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cungCapHangHoaRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    tongGiaTriCaNhan = 0;
                    tongGiaTriTatCa = 0;
                    cungCapHangHoaList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        CungCapHangHoa cungCapHangHoa = documentSnapshot.toObject(CungCapHangHoa.class);
                        cungCapHangHoa.setCungCapHangHoaID(documentSnapshot.getId());
                        cungCapHangHoaRef.document(cungCapHangHoa.getCungCapHangHoaID()).update("cungCapHangHoaID", cungCapHangHoa.getCungCapHangHoaID());
                        if (cungCapHangHoa.getUserID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                            cungCapHangHoaList.add(cungCapHangHoa);
                    }
                }
                runDuAnRef();
            }
        });


    }

    private void runDuAnRef() {
        duAnRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    duAnList.clear();
                    soLuongThamGiaList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        soLuongThamGiaList.add(0);
                        DuAn duAn = documentSnapshot.toObject(DuAn.class);
                        duAn.setId(documentSnapshot.getId());
                        duAnRef.document(duAn.getId()).update("Id", duAn.getId());
                        tongGiaTriTatCa += duAn.getSoLuongHienTai();
                        for (CungCapHangHoa cungCapHangHoa : cungCapHangHoaList) {
                            if (cungCapHangHoa.getDuAnId().equals(duAn.getId())) {
                                tongGiaTriCaNhan += cungCapHangHoa.getSoLuong();
                                soLuongThamGiaList.set(soLuongThamGiaList.size() - 1, cungCapHangHoa.getSoLuong());
                            }
                        }
                        duAnList.add(duAn);
                    }
                    duAnAdapter.notifyDataSetChanged();
                    tv_gttc.setText(tongGiaTriTatCa + "");
                    tv_gtcn.setText(tongGiaTriCaNhan + "");
                }
            }
        });
    }
}