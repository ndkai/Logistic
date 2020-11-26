package com.iot.logisticsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, diaChiUser, sdtUser;
    Button register;
    TextView txt_login;

    FirebaseAuth auth;
    ProgressDialog pd;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference useRef = db.collection("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        txt_login = findViewById(R.id.txt_login);
        diaChiUser = findViewById(R.id.diaChiUser);
        sdtUser = findViewById(R.id.sdtUser);

        auth = FirebaseAuth.getInstance();

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Vui lòng chờ .... ");
                pd.show();

                String str_username = username.getText().toString();
                String str_sdtUser = sdtUser.getText().toString();
                String str_DiaChiUser = diaChiUser.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if(TextUtils.isEmpty(str_username)|| TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_password)){
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if(str_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu bắt buộc từ 6 ký tự", Toast.LENGTH_SHORT).show();
                } else {
                    register(str_username,str_sdtUser,str_DiaChiUser,str_email,str_password);
                }
            }
        });
    }

    private void register(final String username, final String sdtUser, final String diaChiUser, String email, final String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid  = firebaseUser.getUid();

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("userID",userid);
                            hashMap.put("name",username.toLowerCase());
                            hashMap.put("phone",sdtUser.toLowerCase());
                            hashMap.put("address",diaChiUser.toLowerCase());
                            hashMap.put("vaiTro","");

                            useRef.document(userid).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        pd.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                    else {
                                        pd.dismiss();
                                        Toast.makeText(RegisterActivity.this, "You can't register", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }
}