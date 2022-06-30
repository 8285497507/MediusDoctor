package com.example.newdoctorsapp.adapter;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.fragments.HisteryOfApointmentFragment;
import com.example.newdoctorsapp.fragments.UpdateProfile.HospitalAddFragment;
import com.example.newdoctorsapp.models.HospitalApprovalList.HospitalApprovalData;
import com.example.newdoctorsapp.models.InviteHospoital.InviteHospitalResponse;
import com.example.newdoctorsapp.models.NotificationModel.SendDataHospitalAccpet;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalApprovalAdapter extends RecyclerView.Adapter<HospitalApprovalAdapter.MyHolder>  implements Callback<InviteHospitalResponse> {

        Context context;
        public HisteryOfApointmentFragment histeryOfApointmentFragment ;
        ArrayList<HospitalApprovalData> hospitalApprovalData = new ArrayList<>();
       ProgressBar mProgreeBar;
       String requestId;

    public HospitalApprovalAdapter(Context context, ArrayList<HospitalApprovalData> hospitalApprovalData,HisteryOfApointmentFragment histeryOfApointmentFragment) {
        this.context = context;
        this.histeryOfApointmentFragment = histeryOfApointmentFragment;
        this.hospitalApprovalData = hospitalApprovalData;
        }

    @NonNull
    @Override
    public HospitalApprovalAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_approvaladapter, null);
        return new HospitalApprovalAdapter.MyHolder(view);
        }



    @Override
public void onBindViewHolder(@NonNull HospitalApprovalAdapter.MyHolder holder, int position) {
        try {

            if (hospitalApprovalData.get(position).getRequestTo()!=null){
                holder.hospitalname.setText(hospitalApprovalData.get(position).getRequestTo().getName());
                holder.invite.setText(hospitalApprovalData.get(position).getApprovalStatus());
                holder.hospitaladress.setText(hospitalApprovalData.get(position).getRequestTo().getAddress().getCity().getName()+","+
                        hospitalApprovalData.get(position).getRequestTo().getAddress().getState().getName()+","+
                        hospitalApprovalData.get(position).getRequestTo().getAddress().getLocality().getName());
            }else {
                holder.hospitalname.setText(hospitalApprovalData.get(position).getRequestFrom().getName());
                holder.invite.setText(hospitalApprovalData.get(position).getApprovalStatus());
                holder.hospitaladress.setText(hospitalApprovalData.get(position).getRequestFrom().getAddress().getCity().getName()+","+
                        hospitalApprovalData.get(position).getRequestFrom().getAddress().getState().getName()+","+
                        hospitalApprovalData.get(position).getRequestFrom().getAddress().getLocality().getName());

            }



        } catch (Exception e){

            Log.d("dssdd","***"+e);
        }
        if (hospitalApprovalData.get(position).getRefFrom().equalsIgnoreCase("hospitals")==true) {
            if (hospitalApprovalData.get(position).getApprovalStatus().equals("Pending")==true){
                //holder.rel_acceptreject.setVisibility(View.VISIBLE);
                holder.invite.setText(hospitalApprovalData.get(position).getApprovalStatus());

            }

        }


        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestId = hospitalApprovalData.get(position).getId();
                accept();
            }
        });



        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestId = hospitalApprovalData.get(position).getId();
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

        return hospitalApprovalData.size();
        }
        @Override
            public int getItemViewType(int position) {
        return position;
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

    public class MyHolder extends RecyclerView.ViewHolder {

    TextView hospitalname,hospitaladress,invite;
    RelativeLayout rel_acceptreject;
    Button Accept,reject;
    ImageView imghospoital;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        hospitalname = itemView.findViewById(R.id.hospitalname);
        hospitaladress = itemView.findViewById(R.id.hospitaladress);
        invite = itemView.findViewById(R.id.invite);
        rel_acceptreject = itemView.findViewById(R.id.rel_acceptreject);
        Accept = itemView.findViewById(R.id.Accept);
        reject = itemView.findViewById(R.id.reject);
        mProgreeBar = itemView.findViewById(R.id.mProgreeBar);
        imghospoital = itemView.findViewById(R.id.imghospoital);

    }
}
}


