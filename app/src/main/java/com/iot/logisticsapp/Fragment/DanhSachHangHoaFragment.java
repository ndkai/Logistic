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
import com.iot.logisticsapp.Adapter.tinhTrangHangHoaAdapter;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachHangHoaFragment extends Fragment {

    private RecyclerView recyclerView;
    private tinhTrangHangHoaAdapter tinhTrangHangHoaAdapter;
    private List<CungCapHangHoa> cungCapHangHoaList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cungCapHangHoaRef = db.collection("CungCapHangHoa");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_danh_sach_hang_hoa, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        cungCapHangHoaList = new ArrayList<>();
        tinhTrangHangHoaAdapter= new tinhTrangHangHoaAdapter(getContext(),cungCapHangHoaList);
        recyclerView.setAdapter(tinhTrangHangHoaAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        cungCapHangHoaRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (!value.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot : value){
                        cungCapHangHoaList.clear();
                        CungCapHangHoa cungCapHangHoa = documentSnapshot.toObject(CungCapHangHoa.class);
                        if(cungCapHangHoa.getKhoID().equals("67Nzk5ohqY32oBYwRkS6")){
                            cungCapHangHoaList.add(cungCapHangHoa);
                            tinhTrangHangHoaAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }



}