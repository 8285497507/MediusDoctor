package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.fragments.AppointmentsFragment;
import com.example.newdoctorsapp.interfaces.HospitlNameCallback;
import com.example.newdoctorsapp.models.ProfileDetail.HospitalDetail;

import java.util.ArrayList;
import java.util.List;

public class HospitalSpinnerAdapter extends RecyclerView.Adapter<HospitalSpinnerAdapter.MyViewHolder> {
    private Context context;
    List<com.example.newdoctorsapp.models.HospitalList.HospitalDetail> hospitalDetails;
    AppointmentsFragment appointmentsFragment;
    HospitlNameCallback hospitlNameCallback;


    public HospitalSpinnerAdapter(Context context, List<com.example.newdoctorsapp.models.HospitalList.HospitalDetail> hospitalDetails,
                                  AppointmentsFragment appointmentsFragment, HospitlNameCallback hospitlNameCallback) {
        this.context = context;
        this. hospitalDetails = hospitalDetails;
        this. appointmentsFragment = appointmentsFragment;
        this. hospitlNameCallback = hospitlNameCallback;
    }

    @NonNull
    @Override
    public HospitalSpinnerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new HospitalSpinnerAdapter.MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_hospital_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalSpinnerAdapter.MyViewHolder holder, int position) {
        holder.hospitalname.setText(hospitalDetails.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospitlNameCallback.hospitlNameCallback(hospitalDetails.get(position).getName(),
                        hospitalDetails.get(position).get_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalname, Hospitaladderss;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.hospitalname);

        }
    }

}

