package com.iot.logisticsapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.Adapter.danhSachPhuongTienAdapter;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachPhuongTienFragment extends Fragment {


    private RecyclerView recyclerView;
    private danhSachPhuongTienAdapter danhSachPhuongTienAdapter;
    private List<CungCapVanTai> cungCapVanTaiList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cungCapVanTaiRef = db.collection("CungCapVanTai");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach_phuong_tien, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        cungCapVanTaiList = new ArrayList<>();
        danhSachPhuongTienAdapter= new danhSachPhuongTienAdapter(getContext(),cungCapVanTaiList);
        recyclerView.setAdapter(danhSachPhuongTienAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        cungCapVanTaiRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (!value.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot : value){
                        cungCapVanTaiList.clear();
                        CungCapVanTai cungCapVanTai = documentSnapshot.toObject(CungCapVanTai.class);
                        if(cungCapVanTai.getKhoID().equals("67Nzk5ohqY32oBYwRkS6")){
                            cungCapVanTaiList.add(cungCapVanTai);
                            danhSachPhuongTienAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

}