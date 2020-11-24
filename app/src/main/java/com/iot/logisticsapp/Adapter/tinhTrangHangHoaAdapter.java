package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Kho_ChiTietHangHoaActivity;
import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.Model.User;
import com.iot.logisticsapp.R;
import com.iot.logisticsapp.chiTietHangHoaActivity;

import java.util.List;

public class tinhTrangHangHoaAdapter extends RecyclerView.Adapter<tinhTrangHangHoaAdapter.ViewHolder>{

    private Context mContext;
    private List<CungCapHangHoa> mCungcaphanghoa;

    String t ;

    public tinhTrangHangHoaAdapter(Context mContext, List<CungCapHangHoa> mCungcaphanghoa) {
        this.mContext = mContext;
        this.mCungcaphanghoa = mCungcaphanghoa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tinhtrang_cungcaphanghoa_item,parent,false);
        return new tinhTrangHangHoaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CungCapHangHoa cungCapHangHoa = mCungcaphanghoa.get(position);
        holder.cungCapHangHoaID.setText("Đợt hàng : " + cungCapHangHoa.getCungCapHangHoaID());
        holder.tenNguoiCungCap.setText("Người cung cấp : " + cungCapHangHoa.getTenUser());
        holder.diaChiUser.setText("Từ : " + cungCapHangHoa.getDiachiUser());
        holder.diaChiCCC.setText("Đến : " + cungCapHangHoa.getDiaChiCCC());
        holder.tinhTrangVanChuyen.setText(cungCapHangHoa.getTinhTrangVanChuyen());
        vaiTroUser(cungCapHangHoa.getUserID());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t=="kho"){
                    Intent intent = new Intent(mContext, Kho_ChiTietHangHoaActivity.class);
                    intent.putExtra("cungCapHangHoaID",cungCapHangHoa.getCungCapHangHoaID());
                    intent.putExtra("chucNang","cungCapHangHoa");
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, chiTietHangHoaActivity.class);
                    intent.putExtra("cungCapHangHoaID",cungCapHangHoa.getCungCapHangHoaID());
                    intent.putExtra("chucNang","cungCapHangHoa");
                    mContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCungcaphanghoa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cungCapHangHoaID, diaChiUser, diaChiCCC, tinhTrangVanChuyen,tenNguoiCungCap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cungCapHangHoaID = itemView.findViewById(R.id.cungCapHangHoaID);
            diaChiUser = itemView.findViewById(R.id.diaChiUser);
            diaChiCCC = itemView.findViewById(R.id.diaChiCCC);
            tinhTrangVanChuyen = itemView.findViewById(R.id.tinhTrangVanChuyen);
            tenNguoiCungCap = itemView.findViewById(R.id.tenNguoiCungCap);

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

