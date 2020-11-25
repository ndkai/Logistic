package com.iot.logisticsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.iot.logisticsapp.MainActivity;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;
import com.iot.logisticsapp.R;

import java.util.Arrays;
import java.util.List;

public class TrangChu_NguoiNhanCuuTroFragment extends Fragment {

    TextView tv_sđtUser, tv_tenUser, tv_diachiUser, tv_chuyenVaiTro;
    EditText tv_cangiupdo, tv_chiTietTinhTrang;
    Spinner spn_vande_cangiupdo;
    String arr[] = {"Lũ Lụt", "Tai Nạn", "Hạn Hán"};
    Button btn_xacnhan;

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
        tv_cangiupdo = view.findViewById(R.id.tv_cangiupdo);
        tv_chiTietTinhTrang = view.findViewById(R.id.tv_chiTietTinhTrang);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_vande_cangiupdo.setAdapter(adapter);

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
                if(tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                        tv_cangiupdo.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                } else {
                    addSave();
                }
            }
        });
    }

    public void addSave(){
        String tenUser = tv_tenUser.getText().toString();
        String sdtUser = tv_sđtUser.getText().toString();
        String diaChiUser = tv_diachiUser.getText().toString();
        String vanDe = spn_vande_cangiupdo.getSelectedItem().toString();
        String canGiupDo = tv_cangiupdo.getText().toString();
        String chiTietTinhTrang = tv_chiTietTinhTrang.getText().toString();

        String theoDoi = "Đang Xử Lý";
        String[] theoDoiYeuCauArray = theoDoi.split("\\s*,\\s*");
        List<String> theoDoiYeuCau = Arrays.asList(theoDoiYeuCauArray);

        NguoiNhanCuuTro nguoiNhanCuuTro = new NguoiNhanCuuTro(FirebaseAuth.getInstance().getCurrentUser().getUid(),tenUser,sdtUser,diaChiUser,vanDe,canGiupDo
                                                        ,chiTietTinhTrang,"Đang Xử Lý","","",12,11,theoDoiYeuCau);
        nguoiNhanCuuTroRef.add(nguoiNhanCuuTro).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                tv_cangiupdo.setText("");
                tv_chiTietTinhTrang.setText("");
            }
        });;
    }
}