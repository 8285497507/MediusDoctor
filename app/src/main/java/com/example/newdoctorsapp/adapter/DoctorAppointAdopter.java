package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.DoctorApointmentList.Datum;

import java.util.List;

public class DoctorAppointAdopter extends RecyclerView.Adapter<DoctorAppointAdopter.MyViewHolder> {
    private Context context;
    private List<Datum> data;

    public DoctorAppointAdopter(Context context, List<Datum> data) {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.appointmentlist, parent, false)
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum datum=data.get(position);

        String string = data.get(position).getPatient().getAge();
        String parts =  string.split("\\.")[0];
//        String part1 =string.split("\\.")[1];

        holder.pacentname.setText(datum.getPatient().getFirstName()+" "+datum.getPatient().getLastName());
        holder.pacentdetail.setText(datum.getPatient().getGender()+" | "+parts+" "+"yrs");
        holder.hospitalname.setText(datum.getHospital().getName());
        holder.time.setText(datum.getTime().getFrom().getTime()+" "+"To"+" "+datum.getTime().getTill().getTime());
        if(datum.getPatient().getGender().equalsIgnoreCase("Male")){
            holder.imageView.setImageResource(R.drawable.male);
        }else {
            holder.imageView.setImageResource(R.drawable.female);
        }
      //  holder.timening.setText(datum.getTime().getFrom().getTime()+":"+datum.getTime().getFrom().getDivision());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView pacentname,pacentdetail,timening,hospitalname,time;
        AppCompatImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pacentname=itemView.findViewById(R.id.Pacentname);
            pacentdetail=itemView.findViewById(R.id.pacentDetail);
            timening=itemView.findViewById(R.id.time);
            imageView=itemView.findViewById(R.id.imageView);
            hospitalname=itemView.findViewById(R.id.hospitalname);
            time=itemView.findViewById(R.id.time);
        }
    }
}
