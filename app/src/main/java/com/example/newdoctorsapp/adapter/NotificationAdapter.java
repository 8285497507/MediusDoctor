package com.example.newdoctorsapp.adapter;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.NotificationListActivity;
import com.example.newdoctorsapp.models.InviteHospoital.InviteHospitalResponse;
import com.example.newdoctorsapp.models.InviteHospoital.Invitemodeldata;
import com.example.newdoctorsapp.models.NotificationModel.Datum;
import com.example.newdoctorsapp.models.NotificationModel.SendDataHospitalAccpet;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder>
        implements Callback<InviteHospitalResponse> {

    Context context;
    public NotificationListActivity notificationListActivity ;
    ArrayList<Datum> notificationData = new ArrayList<>();
    ProgressBar mProgreeBar;
    String requestId;

    public NotificationAdapter(Context context, ArrayList<Datum> notificationData, NotificationListActivity notificationListActivity) {
        this.context = context;
        this.notificationListActivity = notificationListActivity;
        this.notificationData = notificationData;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_notification_hoispital, null);
        return new NotificationAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyHolder holder, int position) {

        try {
            holder.hospitalname.setText(notificationData.get(position).getSender().getName());
            holder.addressline1.setText(notificationData.get(position).getSender().getAddress().getAddressLine1()+","+
                    notificationData.get(position).getSender().getAddress().getCity().getName()+","+
                    notificationData.get(position).getSender().getAddress().getState().getName());
        } catch (Exception e){

        }

        holder.btnAccpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestId = notificationData.get(position).getId();
                accept();
            }
        });



        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestId = notificationData.get(position).getId();
                reject();
            }
        });

    }

    public  void reject(){
        SendDataHospitalAccpet sendDataHospitalAccpet = new SendDataHospitalAccpet();
        sendDataHospitalAccpet.setRequestId(requestId);
        if (Utils.haveInternet(getContext())) {
            mProgreeBar.setVisibility(View.VISIBLE);
            RetrofitHelper.getInstance().calldenyhospoital(this,sendDataHospitalAccpet);
        }
    }

    public  void accept(){
        SendDataHospitalAccpet sendDataHospitalAccpet = new SendDataHospitalAccpet();
        sendDataHospitalAccpet.setRequestId(requestId);
        if (Utils.haveInternet(getContext())) {
            mProgreeBar.setVisibility(View.VISIBLE);
            RetrofitHelper.getInstance().callapprovehospoital(this,sendDataHospitalAccpet);
        }
    }
    @Override
    public int getItemCount() {
        return notificationData.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class MyHolder extends RecyclerView.ViewHolder {

        TextView hospitalname,addressline1;
        AppCompatButton btnAccpet,btnReject;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.hospitalname);
            addressline1 = itemView.findViewById(R.id.addressline1);
            btnAccpet = itemView.findViewById(R.id.btnAccpet);
            btnReject = itemView.findViewById(R.id.btnReject);
            mProgreeBar = itemView.findViewById(R.id.mProgreeBar);

        }
    }

    @Override
    public void onResponse(Call<InviteHospitalResponse> call, Response<InviteHospitalResponse> response) {
        if (response.isSuccessful()){
            if (response.body().getStatus().equals(200)){
                mProgreeBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Request sended successfully", Toast.LENGTH_LONG).show();
            }
             else {
                mProgreeBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

        }else{

        }
    }

    @Override
    public void onFailure(Call<InviteHospitalResponse> call, Throwable t) {
        mProgreeBar.setVisibility(View.GONE);
    }
}



