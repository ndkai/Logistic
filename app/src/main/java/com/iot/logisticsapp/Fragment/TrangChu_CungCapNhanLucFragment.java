package com.iot.logisticsapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Model.CungCapNhanLuc;
import com.iot.logisticsapp.R;


public class TrangChu_CungCapNhanLucFragment extends Fragment {
    EditText tv_tenUser, tv_sđtUser, tv_diachiUser, tv_noiĐangKyThamGia;
    Button btn_xacnhan;
    Spinner spn_vaitro;
    String arr[] = {"Tài Xế",   "NV Xếp Hàng",   "NV Gom Hàng", "NV Đi Cứu Trợ"};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
    private CollectionReference cungCapNhanLucRef = db.collection("CungCapNhanLuc");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu__cung_cap_nhan_luc, container, false);

        tv_tenUser = view.findViewById(R.id.tv_tenUser);
        tv_sđtUser = view.findViewById(R.id.tv_sđtUser);
        tv_diachiUser = view.findViewById(R.id.tv_diachiUser);
        tv_noiĐangKyThamGia = view.findViewById(R.id.tv_noiĐangKyThamGia);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);
        spn_vaitro = view.findViewById(R.id.spn_vaitro);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_vaitro.setAdapter(adapter);

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                        tv_noiĐangKyThamGia.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    addSave();
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        userRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getActivity(),"Error Load", Toast.LENGTH_SHORT).show();
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

    public void addSave(){
        String tenUser = tv_tenUser.getText().toString();
        String sdtUser = tv_sđtUser.getText().toString();
        String diachiUser = tv_diachiUser.getText().toString();
        String noiĐkThamGia = tv_noiĐangKyThamGia.getText().toString();
        String vaitro = spn_vaitro.getSelectedItem().toString();


       CungCapNhanLuc cungCapNhanLuc = new CungCapNhanLuc(tenUser,sdtUser,diachiUser,noiĐkThamGia,vaitro);
       cungCapNhanLucRef.add(cungCapNhanLuc).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                tv_tenUser.setText("");
                tv_sđtUser.setText("");
                tv_diachiUser.setText("");
                tv_noiĐangKyThamGia.setText("");
            }
        });;
    }



}