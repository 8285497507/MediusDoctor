package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.DoctorApointmentList.Data;

import java.util.List;

public class DoctorAppointAdopter extends RecyclerView.Adapter<DoctorAppointAdopter.MyViewHolder> {
    private Context context;
    private List<Data> data;

    public DoctorAppointAdopter(Context context, List<Data> data) {
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
        Data datum=data.get(position);

        String string = data.get(position).getPatient().getAge();
        String parts =  string.split("\\.")[0];
//        String part1 =string.split("\\.")[1];
         try{

             string = data.get(position).getSubPatient().getDOB();
             parts =  string.split("\\.")[0];
             holder.pacentname.setText(datum.getSubPatient().getFirstName()+" "+datum.getSubPatient().getLastName());
             holder.pacentdetail.setText(datum.getSubPatient().getGender()+" | "+parts+" "+"yrs");

         }
         catch (Exception e){
             string = data.get(position).getPatient().getAge();
             parts =  string.split("\\.")[0];
             holder.pacentname.setText(datum.getPatient().getFirstName()+" "+datum.getPatient().getLastName());
             holder.pacentdetail.setText(datum.getPatient().getGender()+" | "+parts+" "+"yrs");

         }


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
