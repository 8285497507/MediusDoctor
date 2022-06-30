package com.example.newdoctorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activities.Adapter.Addqulifacitionadopter;
import com.example.newdoctorsapp.models.AddDoctorQulification.Data;
import com.example.newdoctorsapp.models.doctoraddqual.DocrorAddData;

import java.util.ArrayList;
import java.util.List;

public class Addqulifacitionadopter2 extends RecyclerView.Adapter<Addqulifacitionadopter2.MyViewHolder> {
    ArrayList<DocrorAddData> docrorAddData = new ArrayList<>();
    private Context context;

    public Addqulifacitionadopter2(Context context, ArrayList<DocrorAddData> docrorAddData) {
        this.context = context;
        this.docrorAddData = docrorAddData;
    }

//    public Addqulifacitionadopter(Context context, List<Qualification> qualification) {
//        this.context=context;
//        this
//    }

    @NonNull
    @Override
    public Addqulifacitionadopter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Addqulifacitionadopter2.MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.qulfactionlist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Addqulifacitionadopter2.MyViewHolder holder, int position) {
        holder.q_name.setText(docrorAddData.get(position).getQualificationName().getName());
        holder.q_org.setText(docrorAddData.get(position).getCertificationOrganisation());
        // holder.q_mail.setText(qulifactionResponceList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return docrorAddData.size();
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
