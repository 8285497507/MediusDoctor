package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;
import static com.example.newdoctorsapp.RXCalling.BaseFragmentJava.getuserdata;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.adapter.NotificationAdapter;
import com.example.newdoctorsapp.models.NotificationModel.Datum;
import com.example.newdoctorsapp.models.NotificationModel.NotificationResponse;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationListActivity extends BaseActivityJava implements Callback<NotificationResponse> {
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    ArrayList<Datum> notificationData = new ArrayList<>();

    RecyclerView recyclernotification;
    RelativeLayout rel_notification;
   ServiceModel serviceModel=new ServiceModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_notificationlist);
        initToolbar(this);
        toolbar.setTitle("Notification");

        recyclernotification = findViewById(R.id.recyclernotification);
        rel_notification = findViewById(R.id.rel_notification);
        recyclernotification.setLayoutManager(new LinearLayoutManager(getContext()));
        if (Utils.haveInternet(getContext())) {
            //mycustomdialog.show();
          serviceModel.doPostJSonRequest(getuserdata().getToken(),"getnotification");
        }
      /*  if (Utils.haveInternet(getContext())) {
            //mycustomdialog.show();
            RetrofitHelper.getInstance().callnotofication(this);
        }*/
    }

    @Override
    public void update(Observable observable, Object args) {
        mycustomdialog.hide();
        if(args.getClass()==NotificationResponse.class){
            NotificationResponse resp= (NotificationResponse) args;
            if (resp.getStatus().equals(200)){
                notificationData = (ArrayList<Datum>)resp.getData();
                if (notificationData.size() != 0) {
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), notificationData, NotificationListActivity.this);
                    recyclernotification.setAdapter(notificationAdapter);
                } else {
                    recyclernotification.setVisibility(View.GONE);
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), notificationData, NotificationListActivity.this);
                    recyclernotification.setAdapter(notificationAdapter);
                }

            }
            else {
                Snackbar.make(rel_notification, resp.getMessage(), Snackbar.LENGTH_LONG).show();
                mycustomdialog.hide();
            }
        }
    }

    @Override
    public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
        if (response.isSuccessful()){
            mycustomdialog.hide();
            if (response.body().getStatus().equals(200)){
                notificationData = (ArrayList<Datum>)response.body().getData();
                if (notificationData.size() != 0) {
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), notificationData, NotificationListActivity.this);
                    recyclernotification.setAdapter(notificationAdapter);
                } else {
                    recyclernotification.setVisibility(View.GONE);
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), notificationData, NotificationListActivity.this);
                    recyclernotification.setAdapter(notificationAdapter);
                }

            }
            else {
                Snackbar.make(rel_notification, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                mycustomdialog.hide();
            }
        }
    }

    @Override
    public void onFailure(Call<NotificationResponse> call, Throwable t) {
        mycustomdialog.hide();
        Snackbar.make(rel_notification, "response.body().getMessage()", Snackbar.LENGTH_LONG).show();
    }


}
