package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Kho_ChiTietHangHoaActivity;
import com.iot.logisticsapp.Model.NguoiNhanCuuTro;
import com.iot.logisticsapp.Model.User;
import com.iot.logisticsapp.R;
import com.iot.logisticsapp.chiTietHangHoaActivity;

import java.util.List;

public class yeuCauCuuTroAdapter extends RecyclerView.Adapter<yeuCauCuuTroAdapter.ViewHolder>{

    private Context mContext;
    private List<NguoiNhanCuuTro> mNguoiNhanCuuTro;

    String t ;

    public yeuCauCuuTroAdapter(Context mContext, List<NguoiNhanCuuTro> mNguoiNhanCuuTro) {
        this.mContext = mContext;
        this.mNguoiNhanCuuTro = mNguoiNhanCuuTro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.yeucau_cuutro_item,parent,false);
        return new yeuCauCuuTroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final NguoiNhanCuuTro nguoiNhanCuuTro = mNguoiNhanCuuTro.get(position);
        holder.dotHangCuuTro.setText("Đợt Hàng : " + nguoiNhanCuuTro.getDotCuuTroID());
        holder.diaChiUser.setText("Từ : " + nguoiNhanCuuTro.getDiaChiUser());
        holder.hangHoaYeuCau.setText("Nhu cầu : " + nguoiNhanCuuTro.getCanGiupDo());
        holder.tenNguoiYeuCauCuuTro.setText("Người yêu cầu: " + nguoiNhanCuuTro.getTenUser());
        holder.tinhTrangVanChuyen.setText(nguoiNhanCuuTro.getTinhTrang());
        vaiTroUser(FirebaseAuth.getInstance().getCurrentUser().getUid());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t=="kho"){
                    Intent intent = new Intent(mContext, Kho_ChiTietHangHoaActivity.class);
                    intent.putExtra("dotCuuTroID",nguoiNhanCuuTro.getDotCuuTroID());
                    intent.putExtra("chucNang","yeuCauCuuTro");
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, chiTietHangHoaActivity.class);
                    intent.putExtra("dotCuuTroID",nguoiNhanCuuTro.getDotCuuTroID());
                    intent.putExtra("chucNang","yeuCauCuuTro");
                    Log.d("yeuCauCuuTroAdapter", "error : ");
                    mContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNguoiNhanCuuTro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dotHangCuuTro, diaChiUser, hangHoaYeuCau, tinhTrangVanChuyen,tenNguoiYeuCauCuuTro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dotHangCuuTro = itemView.findViewById(R.id.dotHangCuuTro);
            diaChiUser = itemView.findViewById(R.id.diaChiUser);
            hangHoaYeuCau = itemView.findViewById(R.id.hangHoaYeuCau);
            tinhTrangVanChuyen = itemView.findViewById(R.id.tinhTrangVanChuyen);
            tenNguoiYeuCauCuuTro = itemView.findViewById(R.id.tenNguoiYeuCauCuuTro);

        }
    }

    public void vaiTroUser(String userID){
        FirebaseFirestore.getInstance().collection("user")
                .document(userID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){return;}
                if (value.exists()) {

                    User user = value.toObject(User.class);
                    if(user.getVaiTro().equals("kho")){
                        t = "kho";
                    }

                }

            }
        });
    }


}

