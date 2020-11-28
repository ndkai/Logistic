package com.iot.logisticsapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.GeoLocation;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.R;


public class TrangChu_CungCapVanTaiFragment extends Fragment {

    EditText tv_tenTaiXe, tv_cmndTaiXe, tv_gplxTaiXe, tv_bienSo, tv_tuNgay, tv_denNgay, tv_taiTrong, tv_diachiUser , tv_kinhdo, tv_vido;
    Spinner spn_loaiXe;
    String arr[] = {"Thông Thường",   "Đặc Biệt"};
    Button btn_xacnhan;
    CheckBox cb_xacnhanDiaChi;

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
        tv_taiTrong = view.findViewById(R.id.tv_taiTrong);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);

        tv_diachiUser = view.findViewById(R.id.tv_diachiUser);
        tv_kinhdo = view.findViewById(R.id.tv_kinhdo);
        tv_vido = view.findViewById(R.id.tv_vido);
        cb_xacnhanDiaChi = view.findViewById(R.id.cb_xacnhanDiaChi);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_loaiXe.setAdapter(adapter);

        cb_xacnhanDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(tv_diachiUser.getText().toString(), getContext(), new TrangChu_CungCapVanTaiFragment.GeoHandler());
            }
        });

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cb_xacnhanDiaChi.isChecked() || tv_tenTaiXe.getText().toString().equals("") || tv_cmndTaiXe.getText().toString().equals("") || tv_gplxTaiXe.getText().toString().equals("") ||
                        tv_bienSo.getText().toString().equals("") || tv_tuNgay.getText().toString().equals("") || tv_denNgay.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    addSave();
                }
            }
        });


        return view;
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String vido, kinhdo;
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    vido = bundle.getString("vido");
                    kinhdo = bundle.getString("kinhdo");
                    break;
                default:
                    vido = null;
                    kinhdo = null;
            }
            tv_vido.setText(vido);
            tv_kinhdo.setText(kinhdo);
            if(tv_vido.getText().toString().equals("") || tv_kinhdo.getText().toString().equals("")){
                Toast.makeText(getContext(), "Không định vị được vị trí - Vui lòng khởi độnng wifi", Toast.LENGTH_SHORT).show();
                btn_xacnhan.setEnabled(false);
            } else {
                Toast.makeText(getContext(), "Kinh Độ : " + tv_vido.getText().toString() + "\n" + "Vĩ Độ" + tv_kinhdo.getText().toString(), Toast.LENGTH_SHORT).show();
                btn_xacnhan.setEnabled(true);
            }

        }
    }

    public void onStart() {
        super.onStart();

        cungCapVanTaiRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (!value.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot : value){
                        CungCapVanTai cungCapVanTai = documentSnapshot.toObject(CungCapVanTai.class);
                        cungCapVanTai.setXeID(documentSnapshot.getId());
                        cungCapVanTaiRef.document(cungCapVanTai.getXeID()).update("xeID",cungCapVanTai.getXeID());

                    }
                }
            }
        });
    }

    public void addSave(){
        String tenTaiXe = tv_tenTaiXe.getText().toString();
        String cmndTaiXe = tv_cmndTaiXe.getText().toString();
        String gplxTaiXe = tv_gplxTaiXe.getText().toString();
        String loaiXe = spn_loaiXe.getSelectedItem().toString();
        String bienSo = tv_bienSo.getText().toString();
        String tuNgay = tv_tuNgay.getText().toString();
        String denNgay = tv_denNgay.getText().toString();
        int taiTrong = Integer.valueOf(tv_taiTrong.getText().toString());

        CungCapVanTai cungCapVanTai = new CungCapVanTai(FirebaseAuth.getInstance().getCurrentUser().getUid(),tenTaiXe,cmndTaiXe,gplxTaiXe,loaiXe,bienSo
                                                        ,tuNgay,denNgay,taiTrong,"67Nzk5ohqY32oBYwRkS6",Double.parseDouble(tv_vido.getText().toString()),Double.parseDouble(tv_kinhdo.getText().toString()));
        cungCapVanTaiRef.add(cungCapVanTai).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "Cung cấp vận tải thành công", Toast.LENGTH_SHORT).show();
                tv_tenTaiXe.setText("");
                tv_cmndTaiXe.setText("");
                tv_gplxTaiXe.setText("");
                tv_tuNgay.setText("");
                tv_denNgay.setText("");
            }
        });
    }

}