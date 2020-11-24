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
import com.iot.logisticsapp.Adapter.yeuCauCuuTroAdapter;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;
import com.iot.logisticsapp.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachNguoiCanCuuTroFragment extends Fragment {

    private RecyclerView recyclerView;
    private yeuCauCuuTroAdapter yeuCauCuuTroAdapter;
    private List<NguoiNhanCuuTro> nguoiNhanCuuTroList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference nguoiNhanCuuTroRef = db.collection("NguoiNhanCuuTro");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach_nguoi_can_cuu_tro, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        nguoiNhanCuuTroList = new ArrayList<>();
        yeuCauCuuTroAdapter= new yeuCauCuuTroAdapter(getContext(),nguoiNhanCuuTroList);
        recyclerView.setAdapter(yeuCauCuuTroAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        nguoiNhanCuuTroRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (!value.isEmpty()) {
                    nguoiNhanCuuTroList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value){

                        NguoiNhanCuuTro nguoiNhanCuuTro = documentSnapshot.toObject(NguoiNhanCuuTro.class);
                        if(nguoiNhanCuuTro.getKhoID().equals("67Nzk5ohqY32oBYwRkS6")){
                            nguoiNhanCuuTroList.add(nguoiNhanCuuTro);
                            yeuCauCuuTroAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
}