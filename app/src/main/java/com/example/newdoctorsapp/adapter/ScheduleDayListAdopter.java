package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;

import com.example.newdoctorsapp.models.Apointmentlist.Timing;


import java.util.List;

public class ScheduleDayListAdopter extends RecyclerView.Adapter<ScheduleDayListAdopter.MyHolderView> {
    private List<Timing> data;
    private Context context;

private Updateschdule updateschdule;

    public ScheduleDayListAdopter(List<Timing> timings, Updateschdule updateschdule) {
        this.data=timings;
        this.updateschdule=updateschdule;
    }

    @NonNull
    @Override
    public ScheduleDayListAdopter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScheduleDayListAdopter.MyHolderView(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.schedulelayout, parent, false)
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScheduleDayListAdopter.MyHolderView holder, @SuppressLint("RecyclerView") int position) {
        holder.capecty.setText(data.get(position).getCapacity()+"");
        Log.e("TAG", "onBindViewHolder: "+ data.get(position).getFrom().getTime()+":"+data.get(position).getFrom().getDivision()+ " To " + data.get(position).getTill().getTime()+":"+data.get(position).getTill().getDivision() );
        holder.scheduletime.setText(data.get(position).getFrom().getTime()+":"+data.get(position).getFrom().getDivision()+ " To " + data.get(position).getTill().getTime()+":"+data.get(position).getTill().getDivision());
//        if(data.get(position).getWorking()){
//            holder.imageView.setImageResource(R.drawable.ic_baseline_check_24);
//        }else {
//
//        }
        holder.imageView.setImageResource(R.drawable.ic_close);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateschdule.Updateschdule(data,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        TextView scheduletime;
        TextView capecty;
        AppCompatImageView imageView;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            scheduletime = itemView.findViewById(R.id.scheduletime);
            capecty = itemView.findViewById(R.id.capecity);
            imageView = itemView.findViewById(R.id.img_cancel);
        }
    }
    public  interface Updateschdule{
        void Updateschdule(List<Timing> data, int position);

    }
}
