package com.iot.logisticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;

public class Main_NguoiNhanCuuTroActivity extends AppCompatActivity {

    TextView tv_sđtUser, tv_tenUser, tv_diachiUser, tv_chuyenVaiTro;
    EditText tv_cangiupdo, tv_chiTietTinhTrang;
    Spinner spn_vande_cangiupdo;
    String arr[] = {"Lũ Lụt", "Tai Nạn", "Hạn Hán"};
    Button btn_xacnhan;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
    private CollectionReference nguoiNhanCuuTroRef = db.collection("NguoiNhanCuuTro");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__nguoi_nhan_cuu_tro);

        tv_tenUser = findViewById(R.id.tv_tenUser);
        tv_sđtUser = findViewById(R.id.tv_sđtUser);
        tv_diachiUser = findViewById(R.id.tv_diachiUser);
        tv_chuyenVaiTro = findViewById(R.id.tv_chuyenVaiTro);
        spn_vande_cangiupdo = findViewById(R.id.spn_vande_cangiupdo);
        tv_cangiupdo = findViewById(R.id.tv_cangiupdo);
        tv_chiTietTinhTrang = findViewById(R.id.tv_chiTietTinhTrang);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_vande_cangiupdo.setAdapter(adapter);

        onClick();

    }

    @Override
    protected void onStart() {
        super.onStart();
        userRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(Main_NguoiNhanCuuTroActivity.this, "Error Load", Toast.LENGTH_SHORT).show();
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
    public void onClick(){
        tv_chuyenVaiTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_tenUser.getText().toString().equals("") || tv_sđtUser.getText().toString().equals("") || tv_diachiUser.getText().toString().equals("") ||
                        tv_cangiupdo.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Vui Lòng Điền Đủ Thông Tin", Toast.LENGTH_SHORT).show();
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

        NguoiNhanCuuTro nguoiNhanCuuTro = new NguoiNhanCuuTro(tenUser,sdtUser,diaChiUser,vanDe,canGiupDo,chiTietTinhTrang);
        nguoiNhanCuuTroRef.add(nguoiNhanCuuTro).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                tv_cangiupdo.setText("");
                tv_chiTietTinhTrang.setText("");
            }
        });;
    }
}