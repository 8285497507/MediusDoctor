package com.example.newdoctorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;

import com.example.newdoctorsapp.models.ProfileDetail.Qualification;

import java.util.List;



public class QulifactionAdopter extends RecyclerView.Adapter<QulifactionAdopter.MyViewHolder> {
    private List<Qualification> qulifactionResponceList;
    private Context context;
private Updatequlifaction updatequlifaction;
    public QulifactionAdopter(Context context, List<Qualification> qulifactionResponceList, Updatequlifaction updatequlifaction) {
        this.context = context;
        this.qulifactionResponceList = qulifactionResponceList;
        this.updatequlifaction=updatequlifaction;
    }



    @NonNull
    @Override
    public QulifactionAdopter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QulifactionAdopter.MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listofqulifaction, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull QulifactionAdopter.MyViewHolder holder, int position) {
        holder.q_name.setText(qulifactionResponceList.get(position).getQualificationName().getName());
        holder.q_org.setText(qulifactionResponceList.get(position).getCertificationOrganisation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatequlifaction.Onupdatequlifaction(qulifactionResponceList.get(position));
            }
        });
        holder.imageViewDelete.setVisibility(View.VISIBLE);
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatequlifaction.setOnDeleteClickListener(qulifactionResponceList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return qulifactionResponceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView q_name, q_org, q_mail;
        TextView q_duration, q_till;
        AppCompatImageView imageViewDelete;
        public MyViewHolder(@NonNull View view) {
            super(view);
            imageViewDelete = itemView.findViewById(R.id.delete);
            q_name = view.findViewById(R.id.Hospitalname);
            q_org = view.findViewById(R.id.hospitaladderss);

        }
    }
    public interface Updatequlifaction{
       void Onupdatequlifaction(Qualification qualification);
        void setOnDeleteClickListener(Qualification qualification);

    }

}
