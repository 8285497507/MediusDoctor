package com.example.newdoctorsapp.adapter;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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
import com.example.newdoctorsapp.models.InviteHospoital.InviteHospitalResponse;
import com.example.newdoctorsapp.models.InviteHospoital.Invitemodeldata;
import com.example.newdoctorsapp.models.Submitfeedback.sumitfeedbackmodel;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchHospitalAdapter extends RecyclerView.Adapter<SearchHospitalAdapter.MyHolder>implements Callback<InviteHospitalResponse> {

    Context context;
    public HospitalAddFragment hospitalAddFragment;
    String Address;
    String hospitalname;
    String hospitalId;
    String UserId;
    int size = 1;
    ProgressBar mProgreeBar;
   boolean isvisible;
    public SearchHospitalAdapter(Context context, String Address, String hospitalname,String hospitalId,String UserId, HospitalAddFragment hospitalAddFragment,boolean isvisible) {
        this.context = context;
        this.Address = Address;
        this.hospitalname = hospitalname;
        this.isvisible = isvisible;
        this.hospitalId = hospitalId;
        this.UserId = UserId;
        this.hospitalAddFragment = hospitalAddFragment;
    }

    @NonNull
    @Override
    public SearchHospitalAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search_hospital, null);
        return new SearchHospitalAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHospitalAdapter.MyHolder holder, int position) {

        holder.hospitalname.setText(hospitalname);
        holder.hospitaladress.setText(Address);
       if(isvisible){
           holder.invite.setVisibility(View.GONE);
       }
        holder.invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invitehospital();
            }
        });
    }
    public  void invitehospital(){
        Invitemodeldata invitemodeldata = new Invitemodeldata();
        invitemodeldata.setHospitalId(hospitalId);
        invitemodeldata.setDoctorId(UserId);
        if (Utils.haveInternet(getContext())) {
            mProgreeBar.setVisibility(View.VISIBLE);
            RetrofitHelper.getInstance().callinvitehospital(this,invitemodeldata);
        }



    }

    @Override
    public int getItemCount() {
        return size;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView hospitalname,hospitaladress,invite;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.hospitalname);
            hospitaladress = itemView.findViewById(R.id.hospitaladress);
            invite = itemView.findViewById(R.id.invite);
            mProgreeBar = itemView.findViewById(R.id.mProgreeBar);

        }
    }

    @Override
    public void onResponse(Call<InviteHospitalResponse> call, Response<InviteHospitalResponse> response) {
        if (response.isSuccessful()){
            if (response.body().getStatus().equals(200)){
                mProgreeBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Request sended successfully", Toast.LENGTH_LONG).show();
                Fragment homepage = new HisteryOfApointmentFragment(context);
                FragmentTransaction fragmentManager = ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction();
                fragmentManager.replace(R.id.container, homepage);
                fragmentManager.addToBackStack(null);
                fragmentManager.commit();
            }
        }else{

        }

    }

    @Override
    public void onFailure(Call<InviteHospitalResponse> call, Throwable t) {
        mProgreeBar.setVisibility(View.GONE);
    }
}

