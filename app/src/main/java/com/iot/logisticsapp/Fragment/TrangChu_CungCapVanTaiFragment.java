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
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.R;


public class TrangChu_CungCapVanTaiFragment extends Fragment {

    EditText tv_tenTaiXe, tv_cmndTaiXe, tv_gplxTaiXe, tv_bienSo, tv_tuNgay, tv_denNgay;
    Spinner spn_loaiXe;
    String arr[] = {"Xe Thông Thường",   "Xe Đặc Biệt"};
    Button btn_xacnhan;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cungCapVanTaiRef = db.collection("CungCapVanTai");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu__cung_cap_van_tai, container, false);
        tv_tenTaiXe = view.findViewById(R.id.tv_tenTaiXe);
        tv_cmndTaiXe = view.findViewById(R.id.tv_cmndTaiXe);
        tv_gplxTaiXe = view.findViewById(R.id.tv_gplxTaiXe);
        spn_loaiXe = view.findViewById(R.id.spn_loaiXe);
        tv_bienSo = view.findViewById(R.id.tv_bienSo);
        tv_tuNgay = view.findViewById(R.id.tv_tuNgay);
        tv_denNgay = view.findViewById(R.id.tv_denNgay);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_loaiXe.setAdapter(adapter);

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_tenTaiXe.getText().toString().equals("") || tv_cmndTaiXe.getText().toString().equals("") || tv_gplxTaiXe.getText().toString().equals("") ||
                        tv_bienSo.getText().toString().equals("") || tv_tuNgay.getText().toString().equals("") || tv_denNgay.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    addSave();
                }
            }
        });


        return view;
    }

    public void addSave(){
        String tenTaiXe = tv_tenTaiXe.getText().toString();
        String cmndTaiXe = tv_cmndTaiXe.getText().toString();
        String gplxTaiXe = tv_gplxTaiXe.getText().toString();
        String loaiXe = spn_loaiXe.getSelectedItem().toString();
        String bienSo = tv_bienSo.getText().toString();
        String tuNgay = tv_tuNgay.getText().toString();
        String denNgay = tv_denNgay.getText().toString();

        CungCapVanTai cungCapVanTai = new CungCapVanTai(tenTaiXe,cmndTaiXe,gplxTaiXe,loaiXe,bienSo,tuNgay,denNgay);
        cungCapVanTaiRef.add(cungCapVanTai).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                tv_tenTaiXe.setText("");
                tv_cmndTaiXe.setText("");
                tv_gplxTaiXe.setText("");
                tv_tuNgay.setText("");
                tv_denNgay.setText("");
            }
        });
    }

}