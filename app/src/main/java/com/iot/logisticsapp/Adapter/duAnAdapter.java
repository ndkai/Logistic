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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.iot.logisticsapp.Kho_ChiTietHangHoaActivity;
import com.iot.logisticsapp.Model.DuAn;
import com.iot.logisticsapp.Model.User;
import com.iot.logisticsapp.R;
import com.iot.logisticsapp.chiTietHangHoaActivity;

import java.util.List;

public class duAnAdapter extends RecyclerView.Adapter<duAnAdapter.ViewHolder> {
    private Context mContext;
    private List<DuAn> mduAnList;
    private List<Integer> soLuongThamGia;

    String t;

    public duAnAdapter(Context mContext, List<DuAn> mCungcaphanghoa, List<Integer> soLuongThamGia) {
        this.mContext = mContext;
        this.mduAnList = mCungcaphanghoa;
        this.soLuongThamGia = soLuongThamGia;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.du_an_item, parent, false);
        return new duAnAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DuAn duAn = mduAnList.get(position);
        holder.tenDuAn.setText("Tên dự án: " + duAn.getTen());
        holder.soLuongHienTai.setText("Số lượng hiện tại : " + duAn.getSoLuongHienTai());
        holder.tongCuuTro.setText("Tổng: " + duAn.getTongCuuTro());
        holder.banDaCuuTro.setText("Bạn đã cứu trợ: " + soLuongThamGia.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return mduAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tenDuAn, soLuongHienTai, tongCuuTro, banDaCuuTro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tenDuAn = itemView.findViewById(R.id.tv_tenDuAn);
            soLuongHienTai = itemView.findViewById(R.id.tv_soLuongHienTai);
            tongCuuTro = itemView.findViewById(R.id.tv_tongCuuTro);
            banDaCuuTro = itemView.findViewById(R.id.tv_banDaCuuTro);

        }
    }

}


