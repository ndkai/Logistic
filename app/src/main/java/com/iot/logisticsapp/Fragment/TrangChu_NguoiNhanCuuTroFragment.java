package com.iot.logisticsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.GeoLocation;
import com.iot.logisticsapp.MainActivity;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;
import com.iot.logisticsapp.R;

import java.util.Arrays;
import java.util.List;

public class TrangChu_NguoiNhanCuuTroFragment extends Fragment {

    TextView tv_sđtUser, tv_tenUser, tv_diachiUser, tv_chuyenVaiTro, tv_kinhdo, tv_vido;
    EditText tv_chiTietTinhTrang;
    Spinner spn_vande_cangiupdo, spn_GiaoDuc ,spn_Yte , spn_NoiCuTru, spn_NhuYeuPham;
    String arr[] = {"Giáo dục", "Y tế", "Nơi cư trú", "Nhu yếu phẩm"};
    String arr_giaoduc[] = {"Học Phí", "Tiếp cận dịch vụ", "Định hướng"};
    String arr_yte[] = {"Chi Phí", "Tiếp cận dịch vụ", "Vật tư", "Nhân lực"};
    String arr_noicutru[] = {"Tạm Thời", "Dài hạn"};
    String arr4_nhuyeupham[] = {"Nước", "Dinh dưỡng", "Vệ sinh", "Thuốc men"};

    Button btn_xacnhan;

    CheckBox cb_xacnhanDiaChi;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
    private CollectionReference nguoiNhanCuuTroRef = db.collection("NguoiNhanCuuTro");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_trang_chu__nguoi_nhan_cuu_tro, container, false);
        tv_tenUser = view.findViewById(R.id.tv_tenUser);
        tv_sđtUser = view.findViewById(R.id.tv_sđtUser);
        tv_diachiUser = view.findViewById(R.id.tv_diachiUser);
        tv_chuyenVaiTro = view.findViewById(R.id.tv_chuyenVaiTro);
        spn_vande_cangiupdo = view.findViewById(R.id.spn_vande_cangiupdo);

        spn_GiaoDuc = view.findViewById(R.id.spn_GiaoDuc);
        spn_Yte = view.findViewById(R.id.spn_Yte);
        spn_NoiCuTru = view.findViewById(R.id.spn_NoiCuTru);
        spn_NhuYeuPham = view.findViewById(R.id.spn_NhuYeuPham);

        tv_chiTietTinhTrang = view.findViewById(R.id.tv_chiTietTinhTrang);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);

        tv_kinhdo = view.findViewById(R.id.tv_kinhdo);
        tv_vido = view.findViewById(R.id.tv_vido);
        cb_xacnhanDiaChi = view.findViewById(R.id.cb_xacnhanDiaChi);

        cb_xacnhanDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(tv_diachiUser.getText().toString(),getContext(),new GeoHandler());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_vande_cangiupdo.setAdapter(adapter);


        spn_vande_cangiupdo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Giáo dục"))
                {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr_giaoduc);
                    adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spn_GiaoDuc.setAdapter(adapter1);
                    spn_GiaoDuc.setVisibility(View.VISIBLE);
                    spn_NhuYeuPham.setVisibility(View.GONE);
                    spn_NoiCuTru.setVisibility(View.GONE);
                    spn_Yte.setVisibility(View.GONE);
                } else  if(selectedItem.equals("Y tế")) {
                    spn_GiaoDuc.setVisibility(View.GONE);
                    spn_NhuYeuPham.setVisibility(View.GONE);
                    spn_NoiCuTru.setVisibility(View.GONE);
                    spn_Yte.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr_yte);
                    adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spn_Yte.setAdapter(adapter1);
                }else  if(selectedItem.equals("Nơi cư trú")) {
                    spn_GiaoDuc.setVisibility(View.GONE);
                    spn_NhuYeuPham.setVisibility(View.GONE);
                    spn_NoiCuTru.setVisibility(View.VISIBLE);
                    spn_Yte.setVisibility(View.GONE);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr_noicutru);
                    adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spn_NoiCuTru.setAdapter(adapter1);
                } else {
                    spn_GiaoDuc.setVisibility(View.GONE);
                    spn_NhuYeuPham.setVisibility(View.VISIBLE);
                    spn_NoiCuTru.setVisibility(View.GONE);
                    spn_Yte.setVisibility(View.GONE);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr4_nhuyeupham);
                    adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spn_NhuYeuPham.setAdapter(adapter1);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });




        onClick();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        userRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getActivity(), "Error Load", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value.exists()) {
                    tv_tenUser.setText(value.getString("name"));
                    tv_diachiUser.setText(value.getString("address"));
                    tv_sđtUser.setText(value.getString("phone"));

                }
            }
        });

        nguoiNhanCuuTroRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (!value.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot : value){
                        NguoiNhanCuuTro nguoiNhanCuuTro = documentSnapshot.toObject(NguoiNhanCuuTro.class);
                        nguoiNhanCuuTro.setDotCuuTroID(documentSnapshot.getId());
                        nguoiNhanCuuTroRef.document(nguoiNhanCuuTro.getDotCuuTroID()).update("dotCuuTroID",nguoiNhanCuuTro.getDotCuuTroID());

                    }
                }
            }
        });
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String vido, kinhdo;
            switch (msg.what){
                case 1: Bundle bundle = msg.getData();
                    vido = bundle.getString("vido");
                    kinhdo = bundle.getString("kinhdo");
                    break;
                default:
                    vido = null;
                    kinhdo = null;
            }
            tv_vido.setText(vido);
            tv_kinhdo.setText(kinhdo);

        }
    }

    public void onClick(){
        tv_chuyenVaiTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cb_xacnhanDiaChi.isChecked()||tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(spn_vande_cangiupdo.getSelectedItem().toString().equals("Giáo dục")){
                        addSave_GiaoDuc(spn_GiaoDuc.getSelectedItem().toString().trim());
                    } else if(spn_vande_cangiupdo.getSelectedItem().toString().equals("Y tế")){
                        addSave_GiaoDuc(spn_Yte.getSelectedItem().toString().trim());
                    } else if(spn_vande_cangiupdo.getSelectedItem().toString().equals("Nơi cư trú")){
                        addSave_GiaoDuc(spn_NoiCuTru.getSelectedItem().toString().trim());
                    } else {
                        addSave_GiaoDuc(spn_NhuYeuPham.getSelectedItem().toString().trim());
                    }

                }
            }
        });
    }

    public void addSave_GiaoDuc(String nhuCauCuThe){
        String tenUser = tv_tenUser.getText().toString();
        String sdtUser = tv_sđtUser.getText().toString();
        String diaChiUser = tv_diachiUser.getText().toString();
        String nhuCau = spn_vande_cangiupdo.getSelectedItem().toString();


        String chiTietTinhTrang = tv_chiTietTinhTrang.getText().toString();

        String theoDoi = "Đang Xử Lý";
        String[] theoDoiYeuCauArray = theoDoi.split("\\s*,\\s*");
        List<String> theoDoiYeuCau = Arrays.asList(theoDoiYeuCauArray);

        NguoiNhanCuuTro nguoiNhanCuuTro = new NguoiNhanCuuTro(FirebaseAuth.getInstance().getCurrentUser().getUid(),tenUser,sdtUser,diaChiUser,nhuCau,nhuCauCuThe
                    ,chiTietTinhTrang,"Đang Xử Lý","","",Double.parseDouble(tv_vido.getText().toString()),Double.parseDouble(tv_kinhdo.getText().toString()),theoDoiYeuCau, "Thông Thườngc");
            nguoiNhanCuuTroRef.add(nguoiNhanCuuTro).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(getContext(), "Yêu Cầu Cứu Trợ Thành Công \n Vui Lòng Chờ Xử Lý", Toast.LENGTH_SHORT).show();
                    tv_chiTietTinhTrang.setText("");

                }
            });

    }
}