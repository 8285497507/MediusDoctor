package com.example.newdoctorsapp.adapter;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.AppointMentDetailsActivity;
import com.example.newdoctorsapp.activity.AppointmentHistoryActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ApppintmentHistoryAdapter extends RecyclerView.Adapter<ApppintmentHistoryAdapter.MyHolder>{

    Context context;
    public AppointmentHistoryActivity appointmentHistoryActivity ;
    ArrayList<com.example.newdoctorsapp.models.AppointmentHistoryModel.Datum> apnmtdata = new ArrayList<>();
    String second,third,maindate;
   TextView daybooking;
    public ApppintmentHistoryAdapter(Context context, ArrayList<com.example.newdoctorsapp.models.AppointmentHistoryModel.Datum>
            apnmtdata, AppointmentHistoryActivity appointmentHistoryActivity) {
        this.context = context;
        this.appointmentHistoryActivity = appointmentHistoryActivity;
        this.apnmtdata = apnmtdata;
    }

    @NonNull
    @Override
    public ApppintmentHistoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_apnmnthistory, null);
        return new ApppintmentHistoryAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApppintmentHistoryAdapter.MyHolder holder, int position) {

        try {
            holder.patientname.setText(apnmtdata.get(position).getPatient().getFirstName()+" "+
                    apnmtdata.get(position).getPatient().getLastName());
            holder.bookingidtxt.setText(apnmtdata.get(position).getAppointmentId());
            holder.drname.setText(apnmtdata.get(position).getDoctors().getFirstName()+" "+
                                  apnmtdata.get(position).getDoctors().getLastName());
            holder.specialization.setText(apnmtdata.get(position).getSpecials().get(0).getSpecialityName());
            holder.hospitalname.setText(apnmtdata.get(position).getHospital().getName());
            holder.tokennumber.setText("Token Number: "+apnmtdata.get(position).getAppointmentToken()+"");
            holder.age.setText(apnmtdata.get(position).getPatient().getGender()+" | "+apnmtdata.get(position).getPatient().getAge());
            holder.slot.setText("Time Slot:"+apnmtdata.get(position).getTime().getFrom().getTime()+""+":"+
                    apnmtdata.get(position).getTime().getTill().getTime()+"");

            String myDateString = apnmtdata.get(position).getCreatedAt();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
            Date myDate = dateFormat.parse (myDateString.replace("Z","+0000"));
            String[] parts = myDate.toString().split(" ");
            String first = parts[0];
            second = parts[1];
            third = parts[2];
            maindate = third+"-"+second+"-"+"2022";
            holder.daybooking.setText(maindate);
          //  holder.daybooking.setText("Date of Appointment:"+maindate);


            String myDateString2 = apnmtdata.get(position).getTime().getDate();
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
            Date myDate2 = dateFormat2.parse (myDateString2.replace("Z","+0000"));
            String[] parts2 = myDate2.toString().split(" ");
            String first2 = parts2[0];
           String  second2 = parts2[1];
           String third2 = parts2[2];
           String maindate2 = third2+"-"+second2+"-"+"2022";
            holder.doa.setText("Date of Appointment:"+maindate2);

           if (apnmtdata.get(position).getAppointmentType().equals("Follow up")){
               holder.relbookdetails.setBackgroundResource(R.color.folow_upcolor);
               holder.apnmenttype.setText("Follow up");
           }else if (apnmtdata.get(position).getAppointmentType().equals("Fresh")){
               holder.relbookdetails.setBackgroundResource(R.color.newapnmntcolor);
               holder.apnmenttype.setText("New Appointment");
           } else if (apnmtdata.get(position).getCancelled().equals(false)) {
               holder.relbookdetails.setBackgroundResource(R.color.canceled_color);
               holder.apnmenttype.setText("Canceled Appointment");
               holder.apnmenttype.setTextColor(Color.parseColor("#FF5F5F"));

           }


        } catch (Exception e){

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AppointMentDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("patientname",apnmtdata.get(position).getPatient().getFirstName()+" "+
                        apnmtdata.get(position).getPatient().getLastName());
                i.putExtra("ddateofbooking",holder.daybooking.getText());
                i.putExtra("apnmtId",apnmtdata.get(position).getId());
                i.putExtra("dayofapnmnt",holder.doa.getText());
                i.putExtra("apnmenttype",holder.apnmenttype.getText().toString());
                Log.d("sdsdd","***sds*"+holder.daybooking.getText());
                i.putExtra("hospitalname",apnmtdata.get(position).getHospital().getName());
              //  i.putExtra("apnmenttype",apnmtdata.get(position).getAppointmentType());
                i.putExtra("specilization",apnmtdata.get(position).getSpecials().get(0).getSpecialityName());
                i.putExtra("docname",apnmtdata.get(position).getDoctors().getFirstName()+" "+
                        apnmtdata.get(position).getDoctors().getLastName());
                i.putExtra("bookingidtxt",apnmtdata.get(position).getAppointmentId());
                i.putExtra("tokennumber","Token Number: "+apnmtdata.get(position).getAppointmentToken()+"");
                i.putExtra("slot","Time Slot:"+apnmtdata.get(position).getTime().getFrom().getTime()+""+":"+
                        apnmtdata.get(position).getTime().getTill().getTime()+"");
                i.putExtra("agegender",apnmtdata.get(position).getPatient().getGender()+" | "+apnmtdata.get(position).getPatient().getAge());

                context.startActivity(i);
            }
        });



    }


    @Override
    public int getItemCount() {
        return apnmtdata.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class MyHolder extends RecyclerView.ViewHolder {

        TextView patientname,bookingidtxt,drname,
                specialization,hospitalname,age,
                tokennumber,slot,doa,daybooking,apnmenttype;
        Button viewbtn;
        RelativeLayout relbookdetails;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            patientname = itemView.findViewById(R.id.patientname);
            bookingidtxt = itemView.findViewById(R.id.bookingidtxt);
            drname = itemView.findViewById(R.id.drname);
            specialization = itemView.findViewById(R.id.specialization);
            hospitalname = itemView.findViewById(R.id.hospitalname);
            age = itemView.findViewById(R.id.age);
            tokennumber = itemView.findViewById(R.id.tokennumber);
            slot = itemView.findViewById(R.id.slot);
            daybooking = itemView.findViewById(R.id.daybooking);
            doa = itemView.findViewById(R.id.doa);
            viewbtn = itemView.findViewById(R.id.viewbtn);
            relbookdetails = itemView.findViewById(R.id.relbookdetails);
            apnmenttype = itemView.findViewById(R.id.apnmenttype);
        }
    }

}




