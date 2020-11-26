package com.iot.logisticsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.Model.Kho;
import com.iot.logisticsapp.Model.User;
import com.iot.logisticsapp.services.milk_delivery.MilkDelivery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_sđtUser, tv_tenUser, tv_diachiUser, tv_chuyenVaiTro, tv_Logout,tv_gia;
  /*  CheckBox cb_CCHH, cb_CCDVVT, cb_CCK, cb_tuVan, cb_DanhSachDuAn;*/

    Button btn_hangHoa, btn_tuVan, btn_Kho, btn_dichVuVanTai, btn_danhSachDuAn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.document("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        tv_tenUser = findViewById(R.id.tv_tenUser);
        tv_Logout = findViewById(R.id.tv_Logout);
        tv_sđtUser = findViewById(R.id.tv_sđtUser);
        tv_diachiUser = findViewById(R.id.tv_diachiUser);
        tv_chuyenVaiTro = findViewById(R.id.tv_chuyenVaiTro_main);

      /*  cb_CCHH = findViewById(R.id.cb_CCHH);
        cb_CCDVVT = findViewById(R.id.cb_CCDVVT);
        cb_CCK = findViewById(R.id.cb_CCK);
        cb_tuVan = findViewById(R.id.cb_tuVan);
        cb_DanhSachDuAn = findViewById(R.id.cb_DanhSachDuAn);*/

        btn_hangHoa = findViewById(R.id.btn_hangHoa);
        btn_tuVan = findViewById(R.id.btn_tuVan);
        btn_Kho = findViewById(R.id.btn_Kho);
        btn_dichVuVanTai = findViewById(R.id.btn_dichVuVanTai);
        btn_danhSachDuAn = findViewById(R.id.btn_danhSachDuAn);
        tv_gia = findViewById(R.id.tv_gia);



        FirebaseFirestore.getInstance().collection("user")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (value.exists()) {

                    User user = value.toObject(User.class);
                    if(!user.getVaiTro().equals("kho")){
                      /*  cb_CCK.setEnabled(false);*/

                        btn_Kho.setEnabled(false);
                        btn_hangHoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_hangHoa.getText().toString().trim());
                                btn_hangHoa.setTextColor(Color.parseColor("#ED1F77"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                             //   btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                        btn_tuVan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_tuVan.getText().toString().trim());
                                btn_tuVan.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                             //   btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                      /*  btn_Kho.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_Kho.getText().toString().trim());
                                btn_Kho.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });*/
                        btn_dichVuVanTai.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_dichVuVanTai.getText().toString().trim());
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                            //    btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                        btn_danhSachDuAn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_danhSachDuAn.getText().toString().trim());
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                             //   btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });


                    } else {
                        btn_hangHoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_hangHoa.getText().toString().trim());
                                btn_hangHoa.setTextColor(Color.parseColor("#ED1F77"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                        btn_tuVan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_tuVan.getText().toString().trim());
                                btn_tuVan.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                        btn_Kho.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_Kho.getText().toString().trim());
                                btn_Kho.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                        btn_dichVuVanTai.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_dichVuVanTai.getText().toString().trim());
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });
                        btn_danhSachDuAn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_gia.setText(btn_danhSachDuAn.getText().toString().trim());
                                btn_danhSachDuAn.setTextColor(Color.parseColor("#ED1F77"));
                                btn_hangHoa.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_tuVan.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_Kho.setTextColor(Color.parseColor("#4C4B4B"));
                                btn_dichVuVanTai.setTextColor(Color.parseColor("#4C4B4B"));
                            }
                        });

                    }
                    onClick();

                }

            }
        });

        Log.d("TAG", " error : ");

    }

    @Override
    protected void onStart()  {
        super.onStart();
        userRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(MainActivity.this, "Error Load", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value.exists()) {
                    tv_tenUser.setText(value.getString("name"));
                    tv_diachiUser.setText(value.getString("address"));
                    tv_sđtUser.setText(value.getString("phone"));

                }
            }
        });

        List<CungCapHangHoa> list = new ArrayList<>();
        CungCapHangHoa c1 = new CungCapHangHoa();
        c1.setKinhDo(0);
        c1.setViDo(12);
        c1.setSoLuong(48);
        list.add(c1);

        CungCapHangHoa c2 = new CungCapHangHoa();
        c2.setKinhDo(6);
        c2.setViDo(5);
        c2.setSoLuong(60);
        list.add(c2);

        CungCapHangHoa c3 = new CungCapHangHoa();
        c3.setKinhDo(7);
        c3.setViDo(15);
        c3.setSoLuong(43);
        list.add(c3);

        CungCapHangHoa c4 = new CungCapHangHoa();
        c4.setKinhDo(9);
        c4.setViDo(12);
        c4.setSoLuong(92);
        list.add(c4);

        CungCapHangHoa c5 = new CungCapHangHoa();
        c5.setKinhDo(15);
        c5.setViDo(3);
        c5.setSoLuong(80);
        list.add(c5);

        Kho kho = new Kho(0, 0, 9999, "", "Thông Thường");

        CungCapVanTai xe1 = new CungCapVanTai();
        xe1.setTaiTrong(10);
        CungCapVanTai xe2 = new CungCapVanTai();
        xe2.setTaiTrong(10);
        List<CungCapVanTai> xeList = new ArrayList<>();
        xeList.add(xe1);
        xeList.add(xe2);

        MilkDelivery milkDelivery = new MilkDelivery();
        milkDelivery.collect(list, kho, xeList);
    }

    public void onClick() {







        tv_chuyenVaiTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Main_NguoiNhanCuuTroActivity.class));
            }
        });
        tv_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    public void Next(View view) {
        if (tv_gia.getText().toString().equals("Hàng hóa")) {
            startActivity(new Intent(getApplicationContext(), CungCapHangHoaAcitivity.class));
        } else if (tv_gia.getText().toString().equals("Dịch Vụ Vận Tải")) {
            startActivity(new Intent(getApplicationContext(), CungCapVanTaiActivity.class));
        } else if (tv_gia.getText().toString().equals("Tư Vấn")) {
            startActivity(new Intent(getApplicationContext(), TuVanActivity.class));
        } else if (tv_gia.getText().toString().equals("Danh Sách Dự Án")) {
            Toast.makeText(this, "Đang Lỗi ---- Đợi fix sau", Toast.LENGTH_SHORT).show();
          /*  startActivity(new Intent(getApplicationContext(), DanhSachDuAnActivity.class));*/
        } else startActivity(new Intent(getApplicationContext(), KhoActivity.class));
    }
}