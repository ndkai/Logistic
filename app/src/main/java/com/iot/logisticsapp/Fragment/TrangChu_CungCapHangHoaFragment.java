package com.iot.logisticsapp.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iot.logisticsapp.GeoLocation;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.DuAn;
import com.iot.logisticsapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TrangChu_CungCapHangHoaFragment extends Fragment {

    EditText tv_tenUser, tv_sđtUser, tv_diachiUser, /*tv_tenHang,*/ tv_khoiLuongHang, tv_thoiGianDuKien, tv_viTriKho, tv_kinhdo, tv_vido;
    TextView tv_loaiHinhVanChuyen;
    Button tv_tuMangDenCCC, tv_cccDenLay;
    Button btn_xacnhan;
    LinearLayout ln_tuMangDenCCC;
    Spinner spn_loaiHang, spn_GiaoDuc ,spn_Yte , spn_NoiCuTru, spn_NhuYeuPham;
    Spinner spn_duAn;
    String arr[] = {"Quần áo", "Y tế", "Dụng cụ học tập", "Nhu yếu phẩm"};
    String arr_quanao[] = {"Quần áo"};
    String arr_yte[] = {"Thuốc", "Dụng cụ y tế", "Dụng cụ sơ cứu"};
    String arr_dungcuhoctap[] = {"Sách giáo khoa", "Dụng cụ học tập"};
    String arr4_nhuyeupham[] = {"Thực phẩm khô", "Thực phẩm ăn liền", "Nước sạch", "Thuốc men"};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
    private CollectionReference cungCapHangHoaRef = db.collection("CungCapHangHoa");
    private CollectionReference duAnRef = db.collection("DuAn");
    private List<DuAn> duAnList;
    private List<String> duAnStringList;
    CheckBox cb_xacnhanDiaChi;
    ArrayAdapter<String> duAnAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        duAnList = new ArrayList<>();
        duAnStringList = new ArrayList<>();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu__cung_cap_hang_hoa, container, false);

        tv_tenUser = view.findViewById(R.id.tv_tenUser);
        tv_sđtUser = view.findViewById(R.id.tv_sđtUser);
        tv_diachiUser = view.findViewById(R.id.tv_diachiUser);
        spn_loaiHang = view.findViewById(R.id.spn_loaiHang);
      //  tv_tenHang = view.findViewById(R.id.tv_tenHang);
        tv_khoiLuongHang = view.findViewById(R.id.tv_khoiLuongHang);
        tv_thoiGianDuKien = view.findViewById(R.id.tv_thoiGianDuKien);
        tv_viTriKho = view.findViewById(R.id.tv_viTriKho);
        tv_loaiHinhVanChuyen = view.findViewById(R.id.tv_loaiHinhVanChuyen);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);
        tv_tuMangDenCCC = view.findViewById(R.id.tv_tuMangDenCCC);
        tv_cccDenLay = view.findViewById(R.id.tv_cccDenLay);
        ln_tuMangDenCCC = view.findViewById(R.id.ln_tuMangDenCCC);

        tv_kinhdo = view.findViewById(R.id.tv_kinhdo);
        tv_vido = view.findViewById(R.id.tv_vido);
        cb_xacnhanDiaChi = view.findViewById(R.id.cb_xacnhanDiaChi);
        spn_duAn = view.findViewById(R.id.spn_duAn);

        spn_GiaoDuc = view.findViewById(R.id.spn_GiaoDuc);
        spn_Yte = view.findViewById(R.id.spn_Yte);
        spn_NoiCuTru = view.findViewById(R.id.spn_NoiCuTru);
        spn_NhuYeuPham = view.findViewById(R.id.spn_NhuYeuPham);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_loaiHang.setAdapter(adapter);

        duAnAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, duAnStringList);
        duAnAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_duAn.setAdapter(duAnAdapter);

        cb_xacnhanDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(tv_diachiUser.getText().toString(), getContext(), new GeoHandler());
            }
        });

        spn_loaiHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Quần áo"))
                {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr_quanao);
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
                }else  if(selectedItem.equals("Dụng cụ học tập")) {
                    spn_GiaoDuc.setVisibility(View.GONE);
                    spn_NhuYeuPham.setVisibility(View.GONE);
                    spn_NoiCuTru.setVisibility(View.VISIBLE);
                    spn_Yte.setVisibility(View.GONE);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr_dungcuhoctap);
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


        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_loaiHinhVanChuyen.getText().toString().equals("Tự Vận Chuyển")) {
                    if (!cb_xacnhanDiaChi.isChecked() || tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                         /*   tv_tenHang.getText().toString().equals("") ||*/ tv_khoiLuongHang.getText().toString().equals("") || tv_thoiGianDuKien.getText().toString().equals("") ||
                            tv_viTriKho.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                    } else {
                        if(spn_loaiHang.getSelectedItem().toString().equals("Quần áo")){
                            addSave(spn_GiaoDuc.getSelectedItem().toString().trim());
                        } else if(spn_loaiHang.getSelectedItem().toString().equals("Y tế")){
                            addSave(spn_Yte.getSelectedItem().toString().trim());
                        } else if(spn_loaiHang.getSelectedItem().toString().equals("Dụng cụ học tập")){
                            addSave(spn_NoiCuTru.getSelectedItem().toString().trim());
                        } else {
                            addSave(spn_NhuYeuPham.getSelectedItem().toString().trim());
                        }
                    }
                } else {
                    if (!cb_xacnhanDiaChi.isChecked() || tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                          /*  tv_tenHang.getText().toString().equals("") || */tv_khoiLuongHang.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                    } else {
                        if(spn_loaiHang.getSelectedItem().toString().equals("Quần áo")){
                            addSave(spn_GiaoDuc.getSelectedItem().toString().trim());
                        } else if(spn_loaiHang.getSelectedItem().toString().equals("Y tế")){
                            addSave(spn_Yte.getSelectedItem().toString().trim());
                        } else if(spn_loaiHang.getSelectedItem().toString().equals("Dụng cụ học tập")){
                            addSave(spn_NoiCuTru.getSelectedItem().toString().trim());
                        } else {
                            addSave(spn_NhuYeuPham.getSelectedItem().toString().trim());
                        }
                    }
                }
                Log.d("tag","e : ");
            }
        });


        tv_tuMangDenCCC.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                ln_tuMangDenCCC.setVisibility(View.VISIBLE);
                tv_tuMangDenCCC.setTextColor(Color.parseColor("#ED1F77"));
                tv_cccDenLay.setTextColor(Color.parseColor("#4C4B4B"));
                tv_loaiHinhVanChuyen.setText("Tự Vận Chuyển");
            }
        });

        tv_cccDenLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ln_tuMangDenCCC.setVisibility(View.GONE);

                tv_cccDenLay.setTextColor(Color.parseColor("#ED1F77"));
                tv_tuMangDenCCC.setTextColor(Color.parseColor("#4C4B4B"));
                tv_cccDenLay.setBackgroundResource(R.drawable.button_background);
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
        cungCapHangHoaRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        CungCapHangHoa cungCapHangHoa = documentSnapshot.toObject(CungCapHangHoa.class);
                        cungCapHangHoa.setCungCapHangHoaID(documentSnapshot.getId());
                        cungCapHangHoaRef.document(cungCapHangHoa.getCungCapHangHoaID()).update("cungCapHangHoaID", cungCapHangHoa.getCungCapHangHoaID());

                    }
                }
            }
        });
        duAnRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (!value.isEmpty()) {
                    duAnStringList.clear();
                    duAnList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        DuAn duAn = documentSnapshot.toObject(DuAn.class);
                        duAn.setId(documentSnapshot.getId());
                        duAnRef.document(duAn.getId()).update("id", duAn.getId());
                        duAnStringList.add(duAn.getTen());
                        duAnList.add(duAn);
                    }
                    duAnAdapter.notifyDataSetChanged();
                }
            }
        });
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
                Toast.makeText(getContext(), "Đã xác định được vị trí của bạn \nKinh Độ : " + tv_vido.getText().toString() + "\n" + "Vĩ Độ : " + tv_kinhdo.getText().toString(), Toast.LENGTH_SHORT).show();
                btn_xacnhan.setEnabled(true);
            }

        }
    }

    public void addSave(String cungCapCuThe) {
        String tenUser = tv_tenUser.getText().toString();
        String sdtUser = tv_sđtUser.getText().toString();
        String diachiUser = tv_diachiUser.getText().toString();
        String thoiGianDuKien = tv_thoiGianDuKien.getText().toString();
        //String tenItem = tv_tenHang.getText().toString();
        String tenLoaiItem = spn_loaiHang.getSelectedItem().toString();
        int khoiLuongItem = Integer.parseInt(tv_khoiLuongHang.getText().toString().trim());
        String diaChiCCC = tv_viTriKho.getText().toString();
        String loaiHinhVanChuyen = tv_loaiHinhVanChuyen.getText().toString();

        String chiTietTinhTrangInput = "Đang Xử Lý";
        String[] chiTietTinhTrangArray = chiTietTinhTrangInput.split("\\s*,\\s*");
        List<String> chiTietTinhTrang = Arrays.asList(chiTietTinhTrangArray);


       CungCapHangHoa cungCapHangHoa = new CungCapHangHoa(FirebaseAuth.getInstance().getCurrentUser().getUid(),tenUser,sdtUser,diachiUser,
               thoiGianDuKien,cungCapCuThe,tenLoaiItem,khoiLuongItem,diaChiCCC,loaiHinhVanChuyen
               ,"Đang Xử Lý",chiTietTinhTrang,"67Nzk5ohqY32oBYwRkS6",Double.parseDouble(tv_vido.getText().toString()),Double.parseDouble(tv_kinhdo.getText().toString()));
    //    cungCapHangHoa.setDuAnId(duAnList.get(spn_duAn.getId()).getId());
        cungCapHangHoaRef.add(cungCapHangHoa).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "Cung Cấp Hàng Hóa Thành Công", Toast.LENGTH_SHORT).show();
                tv_thoiGianDuKien.setText("");
              /*  tv_tenHang.setText("");*/
                tv_khoiLuongHang.setText("");
                tv_viTriKho.setText("");

                // update soLuong DuAn
              /*  DuAn duAnUpdate = duAnList.get(spn_duAn.getId());
                Map<String, Object> updates = new HashMap<>();
                updates.put("soLuongHienTai", duAnUpdate.getSoLuongHienTai() + cungCapHangHoa.getSoLuong());
                duAnRef.document(duAnUpdate.getId()).update(updates);*/

            }

        });


    }


}