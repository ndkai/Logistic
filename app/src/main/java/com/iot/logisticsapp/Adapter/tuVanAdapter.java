package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.iot.logisticsapp.Model.TuVan;
import com.iot.logisticsapp.R;
import com.iot.logisticsapp.TraLoiTuVanActivity;

import java.util.List;

public class tuVanAdapter  extends RecyclerView.Adapter<tuVanAdapter.ViewHolder> {

    private Context mContext;
    private List<TuVan> mTuVan;




    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    public tuVanAdapter(Context mContext, List<TuVan> mTuVan) {
        this.mContext = mContext;
        this.mTuVan = mTuVan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tuvan_item,parent,false);
        return new tuVanAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final TuVan tuVan = mTuVan.get(position);
        holder.tenUser.setText(tuVan.getTenUser());
        holder.sdtUser.setText("SĐT : " + tuVan.getSdtUser());
        holder.cauHoi.setText("Câu Hỏi : "+tuVan.getCauHoi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TraLoiTuVanActivity.class);
                intent.putExtra("CauHoiID",tuVan.getCauHoiID().trim());
                intent.putExtra("tenNguoiDatCauHoi",tuVan.getTenUser().trim());
                intent.putExtra("sdtNguoiDatCauHoi",tuVan.getSdtUser().trim());
                intent.putExtra("cauHoiNguoiDatCauHoi",tuVan.getCauHoi().trim());

                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mTuVan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tenUser, sdtUser, cauHoi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tenUser = itemView.findViewById(R.id.tenUser);
            sdtUser = itemView.findViewById(R.id.sdtUser);
            cauHoi = itemView.findViewById(R.id.cauHoi);


        }
    }



}

