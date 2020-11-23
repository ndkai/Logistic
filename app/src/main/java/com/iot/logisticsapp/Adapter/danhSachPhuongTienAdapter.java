package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.logisticsapp.Model.CungCapVanTai;
import com.iot.logisticsapp.R;

import java.util.List;

public class danhSachPhuongTienAdapter extends RecyclerView.Adapter<danhSachPhuongTienAdapter.ViewHolder>{

    private Context mContext;
    private List<CungCapVanTai> mCungcapvantai;

    public danhSachPhuongTienAdapter(Context mContext, List<CungCapVanTai> mCungcapvantai) {
        this.mContext = mContext;
        this.mCungcapvantai = mCungcapvantai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.danhsach_xe_item,parent,false);
        return new danhSachPhuongTienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CungCapVanTai cungCapVanTai = mCungcapvantai.get(position);
        holder.xeID.setText("Xe: " + cungCapVanTai.getXeID());
        holder.loaiXe.setText("Loại : " + cungCapVanTai.getLoaiXe());
        holder.taiTrong.setText("Tải trọng : " + cungCapVanTai.getTaiTrong());
        holder.bienSo.setText("Biển số : " + cungCapVanTai.getBienSo());
        holder.tuNgay.setText("Từ : " + cungCapVanTai.getTuNgay());
        holder.denNgay.setText("Đến : " + cungCapVanTai.getDenNgay());
        holder.tenTaiXe.setText("Tên Tài Xế : " + cungCapVanTai.getTenTaiXe());
        holder.cmndTaiXe.setText("CMND : " + cungCapVanTai.getCmndTaiXe());
        holder.gplxTaiXe.setText("GPLX : " + cungCapVanTai.getGplxTaiXe());

    }

    @Override
    public int getItemCount() {
        return mCungcapvantai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView xeID, loaiXe, taiTrong, bienSo, tuNgay, denNgay, tenTaiXe, cmndTaiXe, gplxTaiXe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xeID = itemView.findViewById(R.id.xeID);
            loaiXe = itemView.findViewById(R.id.loaiXe);
            taiTrong = itemView.findViewById(R.id.taiTrong);
            bienSo = itemView.findViewById(R.id.bienSo);
            tuNgay = itemView.findViewById(R.id.tuNgay);
            denNgay = itemView.findViewById(R.id.denNgay);
            tenTaiXe = itemView.findViewById(R.id.tenTaiXe);
            cmndTaiXe = itemView.findViewById(R.id.cmndTaiXe);
            gplxTaiXe = itemView.findViewById(R.id.gplxTaiXe);

        }
    }



}


