package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.logisticsapp.Model.NguoiNhanCuuTro;
import com.iot.logisticsapp.R;

import java.util.List;

public class chiTietHang_YeuCauCuuTroAdapter extends RecyclerView.Adapter<chiTietHang_YeuCauCuuTroAdapter.ViewHolder>{

    private Context mContext;
    private List<NguoiNhanCuuTro> mNguoinhancuutro;

    public chiTietHang_YeuCauCuuTroAdapter(Context mContext, List<NguoiNhanCuuTro> mNguoinhancuutro) {
        this.mContext = mContext;
        this.mNguoinhancuutro = mNguoinhancuutro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chitiet_yeucaucuutro_item,parent,false);
        return new chiTietHang_YeuCauCuuTroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final NguoiNhanCuuTro nguoiNhanCuuTro = mNguoinhancuutro.get(position);
        holder.dotHangYeuCau.setText("Đợt hàng : " + nguoiNhanCuuTro.getDotCuuTroID());
        holder.viTriKho.setText("Kho : " + nguoiNhanCuuTro.getKhoID());
        holder.vanDe.setText("Vấn Đề Khó Khăn : " + nguoiNhanCuuTro.getVanDe());
        holder.canGiupDo.setText("Nhu cầu : " + nguoiNhanCuuTro.getCanGiupDo());
        if(nguoiNhanCuuTro.getChiTietTinhTrang().equals("")){
            holder.chiTietTinhTrangKhoKhan.setVisibility(View.GONE);
        } else {
            holder.chiTietTinhTrangKhoKhan.setText(nguoiNhanCuuTro.getChiTietTinhTrang());
        }

        if(nguoiNhanCuuTro.getTinhTrang()==0){
            holder.tinhTrang.setText("Chờ Vận Chuyển");
        } else {
            holder.tinhTrang.setText("Đã Nhận");
        }
    }

    @Override
    public int getItemCount() {
        return mNguoinhancuutro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dotHangYeuCau, viTriKho, vanDe, canGiupDo, tinhTrang, chiTietTinhTrangKhoKhan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dotHangYeuCau = itemView.findViewById(R.id.dotHangYeuCau);
            viTriKho = itemView.findViewById(R.id.viTriKho);
            vanDe = itemView.findViewById(R.id.vanDe);
            canGiupDo = itemView.findViewById(R.id.canGiupDo);
            tinhTrang = itemView.findViewById(R.id.tinhTrang);
            chiTietTinhTrangKhoKhan = itemView.findViewById(R.id.chiTietTinhTrangKhoKhan);

        }
    }



}