package com.example.newdoctorsapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.ProfileDetail.Specialization;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentDay;

import java.util.ArrayList;
import java.util.List;

public class SelectedDayAdapter extends RecyclerView.Adapter<SelectedDayAdapter.MyViewHolder> {
    Context context;
    ArrayList<AppointmentDay> appointmentDays = new ArrayList<>();
    public AppointmentScheduleAdapter appointmentScheduleAdapter;
    public SelectedDayAdapter(Context context, ArrayList<AppointmentDay> appointmentDays,AppointmentScheduleAdapter appointmentScheduleAdapter ) {
        this.context=context;
        this.appointmentDays=appointmentDays;
        this.appointmentScheduleAdapter=appointmentScheduleAdapter;
    }

    @NonNull
    @Override
    public SelectedDayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectedDayAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_selecteddays, parent, false)); }

    @Override
    public void onBindViewHolder(@NonNull SelectedDayAdapter.MyViewHolder holder, int position) {
        if (appointmentDays.get(position).getDay().equals("monday")){
            holder.mon.setText("M");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
//            holder.tue.setVisibility(View.GONE);
//            holder.wed.setVisibility(View.GONE);
//            holder.thurs.setVisibility(View.GONE);
//            holder.fri.setVisibility(View.GONE);
//            holder.sat.setVisibility(View.GONE);
//            holder.sun.setVisibility(View.GONE);
        }
        if (appointmentDays.get(position).getDay().equals("tuesday")){
            holder.mon.setText("T");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (appointmentDays.get(position).getDay().equals("wednesday")){
            holder.mon.setText("W");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (appointmentDays.get(position).getDay().equals("thursday")){
            holder.mon.setText("T");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (appointmentDays.get(position).getDay().equals("friday")){
            holder.mon.setText("F");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
        }

        if (appointmentDays.get(position).getDay().equals("saturday")){
            holder.mon.setText("S");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
        }

        if (appointmentDays.get(position).getDay().equals("sunday")){
            holder.mon.setText("S");
            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public int getItemCount() {
        return appointmentDays.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtdays,mon,tue,wed,thurs,fri,sat,sun;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mon = itemView.findViewById(R.id.mon);
//            tue = itemView.findViewById(R.id.tue);
//            wed = itemView.findViewById(R.id.wed);
//            thurs = itemView.findViewById(R.id.thurs);
//            fri = itemView.findViewById(R.id.fri);
//            sat = itemView.findViewById(R.id.sat);
//            sun = itemView.findViewById(R.id.sun);
        }
    }
}
