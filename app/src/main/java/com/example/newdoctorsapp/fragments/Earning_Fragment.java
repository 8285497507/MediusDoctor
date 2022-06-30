package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.utility.Constants.API_GETPENDINGAMOUNT;
import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.API_GETTOTALEARNING;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.models.login.Data;
import com.example.newdoctorsapp.utility.Utils;

import java.util.Observable;


public class Earning_Fragment extends BaseFragmentJava implements View.OnClickListener {
    private TextView totalearning, withdeowl, pending;
    private Button btntotal, btnwithdrowal, btnpending;



    private final Context context;

    public Earning_Fragment(Context context) {
        this.context = context;
    }


    public static Earning_Fragment newInstance(Context context) {


        return new Earning_Fragment(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ( getView()!=null && isVisibleToUser && Utils.haveInternet(context)) {
            getData();
           // setUserVisibleHint(false);
        }
    }

    private void getData() {
        mycustomdialog.show();
        serviceModel.doPostJSonRequest(getuserdata().getToken(), API_GETTOTALEARNING);

    }

    @Override
    public Observable getModel() {
        return serviceModel;
    }


    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_earning, container, false);
    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);



    }

    private void initview(View view) {

        totalearning=view.findViewById(R.id.totlaamount);
        withdeowl=view.findViewById(R.id.avilableamount);
        pending=view.findViewById(R.id.pendingamount);
        btntotal=view.findViewById(R.id.history);
        btnwithdrowal=view.findViewById(R.id.withdrol);
        btnpending=view.findViewById(R.id.redeem);
        btnpending.setOnClickListener(this);
        btnwithdrowal.setOnClickListener(this);
        btntotal.setOnClickListener(this);
        totalearning.setText(getString(R.string.Rs)+" 500");
//        if ( Utils.haveInternet(context)) {
//            mycustomdialog.show();
//            serviceModel.doPostJSonRequest(getuserdata().getToken(), API_GETTOTALEARNING);
//
//        }

       // setUserVisibleHint(true);
    }


    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
     // ProfileDetailFragment.newInstance(context).setUserVisibleHint(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}









