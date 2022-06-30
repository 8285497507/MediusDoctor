package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.adapter.ApppintmentHistoryAdapter;
import com.example.newdoctorsapp.models.AppointmentHistoryModel.AppointMentHistoryResponse;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentHistoryActivity extends BaseActivityJava
        implements Callback<AppointMentHistoryResponse> {
    Dialog mydialog;
    RelativeLayout rel_notification;
    RecyclerView mRcv_history;
    ArrayList<com.example.newdoctorsapp.models.AppointmentHistoryModel.Datum> apnmtdata = new ArrayList<>();
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apnmnthistory);
        //setContentView(R.layout.activity_appointment_details);
        initToolbar(this);
        toolbar.setTitle("Appointment History");
        rel_notification =  findViewById(R.id.rel_notification);
        mRcv_history =  findViewById(R.id.mRcv_history);
        mRcv_history.setLayoutManager(new LinearLayoutManager(getContext()));
        mydialog=new MyProgressDialog().progressDialog(this);
        RetrofitHelper.getInstance().init(this);

        if (Utils.haveInternet(getContext())) {
            mydialog.show();
            RetrofitHelper.getInstance().callApnmntHistory(this);
        }
    }


    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onResponse(Call<AppointMentHistoryResponse> call, Response<AppointMentHistoryResponse> response) {
        if (response.isSuccessful()){
            mydialog.hide();
            if (response.body().getStatus().equals(200)){
                apnmtdata = (ArrayList<com.example.newdoctorsapp.models.AppointmentHistoryModel.Datum>)response.body().getData();
                if (apnmtdata.size() != 0) {
                    ApppintmentHistoryAdapter apppintmentHistoryAdapter = new ApppintmentHistoryAdapter(getContext(), apnmtdata, AppointmentHistoryActivity.this);
                    mRcv_history.setAdapter(apppintmentHistoryAdapter);
                } else {
                    mRcv_history.setVisibility(View.GONE);
                    ApppintmentHistoryAdapter apppintmentHistoryAdapter = new ApppintmentHistoryAdapter(getContext(), apnmtdata, AppointmentHistoryActivity.this);
                    mRcv_history.setAdapter(apppintmentHistoryAdapter);
                }

            }
            else {
                Snackbar.make(rel_notification, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                mydialog.hide();
            }
        }

    }

    @Override
    public void onFailure(Call<AppointMentHistoryResponse> call, Throwable t) {
        mydialog.hide();
        Snackbar.make(rel_notification, "response.body().getMessage()", Snackbar.LENGTH_LONG).show();
    }
}
