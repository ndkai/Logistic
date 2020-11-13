package com.iot.logisticsapp.duy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.iot.logisticsapp.R;
import com.iot.logisticsapp.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.email_edt)
    EditText emailEdt;
    @BindView(R.id.password_edt)
    EditText passEdt;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupViews();
    }

    private void setupViews(){

    }
    @OnClick(R.id.login_btn)
    public void login(){

        if(!TextUtils.isEmpty(emailEdt.getText()) || !TextUtils.isEmpty(passEdt.getText())){
            firebaseAuth.signInWithEmailAndPassword(emailEdt.getText().toString(), passEdt.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            CommonUtils.showToast(getContext(), "Đăng nhập thành công");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            CommonUtils.showToast(getContext(), "Đăng nhập thất bại");
                        }
                    });
        }
        else{
            CommonUtils.showToast(getContext(), "Vui lòng nhập đầy đủ tài khoản và mật khẩu");
        }
    }
}
