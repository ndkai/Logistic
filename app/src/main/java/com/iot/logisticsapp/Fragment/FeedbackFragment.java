package com.iot.logisticsapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iot.logisticsapp.R;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {
   public Spinner spinner;
   public LinearLayout linearLayout;
   int count = 0 ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_feedback,container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.feedback_spinner);
        linearLayout = view.findViewById(R.id.feedback_add);
        ArrayList<String> list = new ArrayList<>();
        list.add("Địa chỉ không đúng");
        list.add("Sai loại hàng");
        list.add("Hàng hóa bị hư hỏng");
        list.add("Giao không đúng thời gian");
        list.add("Phản hồi khác");
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             if(count != 0 ){
                 Button textView = new Button(getContext());
                 textView.setBackgroundColor(Color.WHITE);
                 textView.setTextColor(Color.GREEN);
                 textView.setText(list.get(i));
                 textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                 linearLayout.addView(textView);
             }       else{
                 count++;
             }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
