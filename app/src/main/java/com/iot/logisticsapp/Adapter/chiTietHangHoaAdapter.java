package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.logisticsapp.Model.CungCapHangHoa;
import com.iot.logisticsapp.R;

import java.util.List;

public class chiTietHangHoaAdapter extends RecyclerView.Adapter<chiTietHangHoaAdapter.ViewHolder>{

    private Context mContext;
    private List<CungCapHangHoa> mCungcaphanghoa;

    public chiTietHangHoaAdapter(Context mContext, List<CungCapHangHoa> mCungcaphanghoa) {
        this.mContext = mContext;
        this.mCungcaphanghoa = mCungcaphanghoa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chitiethanghoa_item,parent,false);
        return new chiTietHangHoaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CungCapHangHoa cungCapHangHoa = mCungcaphanghoa.get(position);
        holder.dotHangCungCap.setText("Đợt hàng : " + cungCapHangHoa.getCungCapHangHoaID());
        holder.viTriUser.setText("Từ : " + cungCapHangHoa.getDiachiUser());
        holder.viTriCCC.setText("Đến : " + cungCapHangHoa.getDiaChiCCC());
        holder.tv_loaiHang.setText("Loại hàng : " + cungCapHangHoa.getTenLoaiItem());
        holder.tv_tenHang.setText("Tên Hàng : " + cungCapHangHoa.getTenItem());
        holder.tv_khoiLuongHang.setText("Khối lượng : " + cungCapHangHoa.getKhoiLuongItem());
        holder.tv_loaiHinhVanChuyen.setText("Loại hình : " + cungCapHangHoa.getLoaiHinhVanChuyen());
        if(cungCapHangHoa.getTinhTrangVanChuyen()==0){
            holder.tv_tinhTrang.setText("Chờ Vận Chuyển");
        } else {
            holder.tv_tinhTrang.setText("Đã Nhận");
        }
    }

    @Override
    public int getItemCount() {
        return mCungcaphanghoa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dotHangCungCap, viTriUser, viTriCCC, tv_loaiHang, tv_tenHang, tv_khoiLuongHang, tv_loaiHinhVanChuyen, tv_tinhTrang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dotHangCungCap = itemView.findViewById(R.id.dotHangCungCap);
            viTriUser = itemView.findViewById(R.id.viTriUser);
            viTriCCC = itemView.findViewById(R.id.viTriCCC);
            tv_loaiHang = itemView.findViewById(R.id.tv_loaiHang);
            tv_tenHang = itemView.findViewById(R.id.tv_tenHang);
            tv_khoiLuongHang = itemView.findViewById(R.id.tv_khoiLuongHang);
            tv_loaiHinhVanChuyen = itemView.findViewById(R.id.tv_loaiHinhVanChuyen);
            tv_tinhTrang = itemView.findViewById(R.id.tv_tinhTrang);

        }
    }



}
