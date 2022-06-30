package com.example.newdoctorsapp.activities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.AddDoctorQulification.Data;
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce;
import com.example.newdoctorsapp.models.ProfileDetail.Qualification;

import java.util.List;

public class Addqulifacitionadopter extends RecyclerView.Adapter<Addqulifacitionadopter.MyViewHolder> {
    private List<Data> qulifactionResponceList;
    private Context context;

    public Addqulifacitionadopter(Context context, List<Data> qulifactionResponceList) {
        this.context = context;
        this.qulifactionResponceList = qulifactionResponceList;
    }

//    public Addqulifacitionadopter(Context context, List<Qualification> qualification) {
//        this.context=context;
//        this
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Addqulifacitionadopter.MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.qulfactionlist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.q_name.setText(qulifactionResponceList.get(position).getQualificationName());
        holder.q_org.setText(qulifactionResponceList.get(position).getCertificationOrganisation());
       // holder.q_mail.setText(qulifactionResponceList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return qulifactionResponceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView q_name, q_org, q_mail;
        TextView q_duration, q_till;

        public MyViewHolder(@NonNull View view) {
            super(view);
            q_name = view.findViewById(R.id.q_name);
            q_org = view.findViewById(R.id.q_org);
            q_duration = view.findViewById(R.id.q_duration);
            q_till = view.findViewById(R.id.q_till);
            q_mail = view.findViewById(R.id.q_mail);
            q_mail.setVisibility(View.GONE);
            q_duration.setVisibility(View.GONE);
            q_till.setVisibility(View.GONE);
        }
    }
}
