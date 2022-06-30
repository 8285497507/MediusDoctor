package com.example.newdoctorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.AddQualificationDetailActivity;
import com.example.newdoctorsapp.interfaces.SpelizationIdCallback;
import com.example.newdoctorsapp.models.ProfileDetail.Specialization;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.Speciality;

import java.util.List;

public class SpecializationRegAdapter extends RecyclerView.Adapter<SpecializationRegAdapter.MyViewHolder> {
    private Context context;
    private List<Speciality> specialityList;
    AddQualificationDetailActivity addQualificationDetailActivity;
    SpelizationIdCallback spelizationIdCallback;


    public SpecializationRegAdapter(Context context, List<Speciality> specialityList, AddQualificationDetailActivity addQualificationDetailActivity,
                                    SpelizationIdCallback spelizationIdCallback) {
        this.context=context;
        this.specialityList=specialityList;
        this.addQualificationDetailActivity=addQualificationDetailActivity;
        this.spelizationIdCallback=spelizationIdCallback;
    }
    @NonNull
    @Override
    public SpecializationRegAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecializationRegAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.splizationlist, parent, false)); }

    @Override
    public void onBindViewHolder(@NonNull SpecializationRegAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(specialityList.get(position).getSpecialityName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spelizationIdCallback.specializationidcallback(specialityList.get(position).get_id(),
                        specialityList.get(position).getSpecialityName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialityList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.speclization);
        }
    }
}

