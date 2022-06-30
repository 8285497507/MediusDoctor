package com.example.newdoctorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.AddQualificationDetailActivity;
import com.example.newdoctorsapp.fragments.ProfileDetailFragment;
import com.example.newdoctorsapp.models.ProfileDetail.Specialization;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.Speciality;

import java.util.List;

public class SplizationAdopter extends RecyclerView.Adapter<SplizationAdopter.MyViewHolder> {
   private Context context;
   private  List<Specialization> specialization;
  private List<Speciality> specialityList;
    AddQualificationDetailActivity addQualificationDetailActivity;

    public SplizationAdopter(Context profileDetailFragment, List<Specialization> specialization) {
        this.context=profileDetailFragment;
        this.specialization=specialization;
    }


//    public SplizationAdopter(Context context, List<Speciality> specialityList, AddQualificationDetailActivity addQualificationDetailActivity) {
//        this.context=context;
//        this.specialityList=specialityList;
//        this.addQualificationDetailActivity=addQualificationDetailActivity;
//    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SplizationAdopter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.splizationlist, parent, false)); }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.textView.setText(specialization.get(position).getSpecialityName());
    }

    @Override
    public int getItemCount() {
        return specialization.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.speclization);
        }
    }
}
