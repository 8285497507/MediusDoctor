package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.fragments.schedule_appointment_fragment;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentDay;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentWorkingHour;

import java.util.ArrayList;

public class AppointmentScheduleAdapter extends RecyclerView.Adapter<AppointmentScheduleAdapter.MyHolder>{

       Context context;
       ArrayList<AppointmentWorkingHour> appointmentWorkingHours = new ArrayList<>();
       ArrayList<AppointmentDay> appointmentDays = new ArrayList<>();
       public schedule_appointment_fragment schedule_appointment_fragment;
    RecyclerView recyclerselecteddays;
    public AppointmentScheduleAdapter(Context context, ArrayList<AppointmentWorkingHour> appointmentWorkingHours,
                                      schedule_appointment_fragment schedule_appointment_fragment) {
        this.context = context;
        this.appointmentWorkingHours = appointmentWorkingHours;
        this.schedule_appointment_fragment = schedule_appointment_fragment;

        }


    @NonNull
    @Override
    public AppointmentScheduleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_appointmentschedule, null);
        return new AppointmentScheduleAdapter.MyHolder(view);
        }
    @Override
    public void onBindViewHolder(@NonNull AppointmentScheduleAdapter.MyHolder holder, int position) {


        SelectedDayAdapter selectedDayAdapter = new SelectedDayAdapter(context,
                (ArrayList<AppointmentDay>) appointmentWorkingHours.get(position).getDays(),
                AppointmentScheduleAdapter.this);
        recyclerselecteddays.setAdapter(selectedDayAdapter);

//        recyclerselecteddays.setLayoutManager(new LinearLayoutManager(context));
//        recyclerselecteddays.setHasFixedSize(true);
//        recyclerselecteddays.setNestedScrollingEnabled(false);


        recyclerselecteddays.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerselecteddays.setHasFixedSize(true);
        recyclerselecteddays.setNestedScrollingEnabled(false);

    holder.timestart.setText(appointmentWorkingHours.get(position).getFrom().getTime() + "");
    holder.timeend.setText(appointmentWorkingHours.get(position).getFrom().getDivision() + "");
    holder.capacity.setText(appointmentWorkingHours.get(position).getDays().get(0).getCapacity() + "");



//    for (int i=0;i<=appointmentWorkingHours.get(position).getDays().size();i++){
//        for (int j=0;j<=appointmentWorkingHours.get(i).getDays().size();j++){
//            if(appointmentWorkingHours.get(position).getDays().get(j).getDay().equals("monday")){
//                holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
//                holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
//                //  notifyDataSetChanged();
//            }
//        }
//
//    }



//        if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("monday")) {
//            holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
//            holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
//              if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("tuesday")) {
//                holder.tue.setBackgroundResource(R.drawable.edit_box_selected);
//                holder.tue.setTextColor(Color.parseColor("#FFFFFF"));
//                if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("wednesday")) {
//                      holder.wed.setBackgroundResource(R.drawable.edit_box_selected);
//                      holder.wed.setTextColor(Color.parseColor("#FFFFFF"));
//                    if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("thurday")) {
//                        holder.thurs.setBackgroundResource(R.drawable.edit_box_selected);
//                        holder.thurs.setTextColor(Color.parseColor("#FFFFFF"));
//                        if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("friday")) {
//                            holder.fri.setBackgroundResource(R.drawable.edit_box_selected);
//                            holder.fri.setTextColor(Color.parseColor("#FFFFFF"));
//                            if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("satuday")) {
//                                holder.sat.setBackgroundResource(R.drawable.edit_box_selected);
//                                holder.sat.setTextColor(Color.parseColor("#FFFFFF"));
//                                if (appointmentWorkingHours.get(position).getDays().get(position).getDay().equals("sunday")) {
//                                    holder.sun.setBackgroundResource(R.drawable.edit_box_selected);
//                                    holder.sun.setTextColor(Color.parseColor("#FFFFFF"));
//                                }
//                            }
//
//                        }
//                    }
//
//                  }
//
//              }
//        }



//        for ( int i =0;i<=appointmentWorkingHours.get(position).getDays().size();i++){
//            AppointmentDay appointmentDay = appointmentWorkingHours.get(position).getDays().get(i);
//            if (appointmentDay.getDay().equals("monday")) {
//                holder.mon.setBackgroundResource(R.drawable.edit_box_selected);
//                holder.mon.setTextColor(Color.parseColor("#FFFFFF"));
//                if (appointmentDay.getDay().equals("tuesday")) {
//                    holder.tue.setBackgroundResource(R.drawable.edit_box_selected);
//                    holder.tue.setTextColor(Color.parseColor("#FFFFFF"));
//                    if (appointmentDay.getDay().equals("wednesday")) {
//                        holder.wed.setBackgroundResource(R.drawable.edit_box_selected);
//                        holder.wed.setTextColor(Color.parseColor("#FFFFFF"));
//                    }
//
//                }
//            }
//        }





//                if (appointmentDays.get(position).getDay().equals("thurday")) {
//                    holder.thurs.setBackgroundResource(R.drawable.edit_box_selected);
//                    holder.thurs.setTextColor(Color.parseColor("#FFFFFF"));
//                    if (appointmentDays.get(position).getDay().equals("friday")) {
//                        holder.fri.setBackgroundResource(R.drawable.edit_box_selected);
//                        holder.fri.setTextColor(Color.parseColor("#FFFFFF"));
//                        if (appointmentDays.get(position).getDay().equals("satuday")) {
//                            holder.sat.setBackgroundResource(R.drawable.edit_box_selected);
//                            holder.sat.setTextColor(Color.parseColor("#FFFFFF"));
//                            if (appointmentDays.get(position).getDay().equals("sunday")) {
//                                holder.sun.setBackgroundResource(R.drawable.edit_box_selected);
//                                holder.sun.setTextColor(Color.parseColor("#FFFFFF"));
//                            }
//                        }
//
//                    }
                }





  @Override
   public int getItemCount() {
        return appointmentWorkingHours.size();
        }

  @Override
   public int getItemViewType(int position) {
        return position;
        }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView timestart,timeend,capacity,mon,tue,wed,thurs,fri,sat,sun;


        public MyHolder(View itemView) {
            super(itemView);
            timestart = itemView.findViewById(R.id.timestart);
            timeend = itemView.findViewById(R.id.timeend);
            capacity = itemView.findViewById(R.id.capacity);
            mon = itemView.findViewById(R.id.mon);
//            tue = itemView.findViewById(R.id.tue);
//            wed = itemView.findViewById(R.id.wed);
//            thurs = itemView.findViewById(R.id.thurs);
//            fri = itemView.findViewById(R.id.fri);
//            sat = itemView.findViewById(R.id.sat);
//            sun = itemView.findViewById(R.id.sun);
            recyclerselecteddays = itemView.findViewById(R.id.recyclerselecteddays);


        }
    }
}




