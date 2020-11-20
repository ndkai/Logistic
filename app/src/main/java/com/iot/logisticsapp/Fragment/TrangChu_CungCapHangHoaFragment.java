package com.iot.logisticsapp.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.R;


public class TrangChu_CungCapHangHoaFragment extends Fragment {

    EditText tv_tenUser, tv_sđtUser, tv_diachiUser, tv_tenHang, tv_khoiLuongHang, tv_thoiGianDuKien, tv_viTriKho;
    TextView tv_loaiHinhVanChuyen;
    Button tv_tuMangDenCCC, tv_cccDenLay;
    Button btn_xacnhan;
    LinearLayout ln_tuMangDenCCC;
    Spinner spn_loaiHang;
    String arr[] = {"Hàng Thông Thường",   "Hàng Đặc Biệt"};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
    private CollectionReference cungCapHangHoaRef = db.collection("CungCapHangHoa");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu__cung_cap_hang_hoa, container, false);

        tv_tenUser = view.findViewById(R.id.tv_tenUser);
        tv_sđtUser = view.findViewById(R.id.tv_sđtUser);
        tv_diachiUser = view.findViewById(R.id.tv_diachiUser);
        spn_loaiHang = view.findViewById(R.id.spn_loaiHang);
        tv_tenHang = view.findViewById(R.id.tv_tenHang);
        tv_khoiLuongHang = view.findViewById(R.id.tv_khoiLuongHang);
        tv_thoiGianDuKien = view.findViewById(R.id.tv_thoiGianDuKien);
        tv_viTriKho = view.findViewById(R.id.tv_viTriKho);
        tv_loaiHinhVanChuyen = view.findViewById(R.id.tv_loaiHinhVanChuyen);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);
        tv_tuMangDenCCC = view.findViewById(R.id.tv_tuMangDenCCC);
        tv_cccDenLay = view.findViewById(R.id.tv_cccDenLay);
        ln_tuMangDenCCC = view.findViewById(R.id.ln_tuMangDenCCC);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_loaiHang.setAdapter(adapter);

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_loaiHinhVanChuyen.getText().toString().equals("Tự Vận Chuyển")){
                    if(tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                            tv_tenHang.getText().toString().equals("") || tv_khoiLuongHang.getText().toString().equals("") || tv_thoiGianDuKien.getText().toString().equals("") ||
                            tv_viTriKho.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                    } else {
                        addSave();
                    }
                }
                else {
                    if(tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                            tv_tenHang.getText().toString().equals("") || tv_khoiLuongHang.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                    } else {
                        addSave();
                    }
                }
            }
        });

        tv_tuMangDenCCC.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                ln_tuMangDenCCC.setVisibility(View.VISIBLE);
                tv_tuMangDenCCC.setBackgroundColor(Color.parseColor("#00BCD4"));
                tv_cccDenLay.setBackgroundColor(Color.parseColor("#FBFBFB"));
                tv_loaiHinhVanChuyen.setText("Tự Vận Chuyển");
            }
        });

        tv_cccDenLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ln_tuMangDenCCC.setVisibility(View.GONE);
                tv_cccDenLay.setBackgroundColor(Color.parseColor("#00BCD4"));
                tv_tuMangDenCCC.setBackgroundColor(Color.parseColor("#FBFBFB"));
                tv_loaiHinhVanChuyen.setText("Cần Lấy Hàng");
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
        String thoiGianDuKien = tv_thoiGianDuKien.getText().toString();
        String tenItem = tv_tenHang.getText().toString();
        String tenLoaiItem = spn_loaiHang.getSelectedItem().toString();
        int khoiLuongItem = Integer.valueOf(tv_khoiLuongHang.getText().toString());
        String diaChiCCC = tv_viTriKho.getText().toString();
        String loaiHinhVanChuyen = tv_loaiHinhVanChuyen.getText().toString();
        CungCapHangHoa cungCapHangHoa = new CungCapHangHoa(tenUser,sdtUser,diachiUser,thoiGianDuKien,tenItem,tenLoaiItem,khoiLuongItem,diaChiCCC,loaiHinhVanChuyen);
        cungCapHangHoaRef.add(cungCapHangHoa).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                tv_thoiGianDuKien.setText("");
                tv_tenHang.setText("");
                tv_khoiLuongHang.setText("");
                tv_viTriKho.setText("");
            }
        });;
    }



}