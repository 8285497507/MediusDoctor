package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.fragments.ProfileDetailFragment;
import com.example.newdoctorsapp.models.HospitalList.Data;
import com.example.newdoctorsapp.models.ProfileDetail.HospitalDetail;

import java.util.ArrayList;
import java.util.List;


public class HospitallistAdopyter extends RecyclerView.Adapter<HospitallistAdopyter.MyViewHolder> {
    private Context context;
    private List<com.example.newdoctorsapp.models.ProfileDetail.HospitalDetail> data = new ArrayList<>();
   private DeleteListener deleteListener;


    public HospitallistAdopyter(Context context, List<HospitalDetail> hospitalDetails, DeleteListener deleteListener) {
        this.context = context;
        this.data = hospitalDetails;
        this.deleteListener=deleteListener;
    }

    @NonNull
    @Override
    public HospitallistAdopyter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new HospitallistAdopyter.MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.practice_cardview, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HospitallistAdopyter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.checkedbox.setVisibility(View.GONE);
        holder.imageViewDelete.setVisibility(View.VISIBLE);
        holder.hospitalname.setText(data.get(position).getName());
        holder.Hospitaladderss.setText(data.get(position).getAddress().getAddressLine_1());
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.setOnDeleteClickListener(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalname, Hospitaladderss;
        CheckBox checkedbox;
        AppCompatImageView imageViewDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.Hospitalname);
            Hospitaladderss = itemView.findViewById(R.id.hospitaladderss);
            checkedbox = itemView.findViewById(R.id.checkedbox);
            imageViewDelete = itemView.findViewById(R.id.delete);
        }
    }

    public interface DeleteListener {
        void setOnDeleteClickListener(HospitalDetail hospitalDetail);
    }
}
