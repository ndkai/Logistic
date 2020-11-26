package com.iot.logisticsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.iot.logisticsapp.Model.TraLoiTuVan;
import com.iot.logisticsapp.R;

import java.util.List;

public class traLoiTuVanAdapter extends RecyclerView.Adapter<traLoiTuVanAdapter.ViewHolder> {

    private Context mContext;
    private List<TraLoiTuVan> mTraLoiTuVan;




    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    public traLoiTuVanAdapter(Context mContext, List<TraLoiTuVan> mTraLoiTuVan) {
        this.mContext = mContext;
        this.mTraLoiTuVan = mTraLoiTuVan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tuvan_item,parent,false);
        return new traLoiTuVanAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final TraLoiTuVan traLoiTuVan = mTraLoiTuVan.get(position);
        holder.tenUser.setText(traLoiTuVan.getTenUser());
        holder.sdtUser.setVisibility(View.GONE);
        holder.cauHoi.setText("Trả Lời : "+traLoiTuVan.getCauTraLoi());

    }



    @Override
    public int getItemCount() {
        return mTraLoiTuVan.size();
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

