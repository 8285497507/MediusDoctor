package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.RXCalling.BaseActivityJava.setFragment;
import static com.example.newdoctorsapp.utility.Constants.API_GETAPPOINTMENTSUMMARY;
import static com.example.newdoctorsapp.utility.Constants.API_GETTOTALEARNING;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.RXCalling.Framentcallback;
import com.example.newdoctorsapp.activity.MainActivity;
import com.example.newdoctorsapp.activity.Profile_Activity;
import com.example.newdoctorsapp.models.Apointmentlist.DataX;
import com.example.newdoctorsapp.models.Apointmentlist.apointmentcount;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.util.Observable;


public class Appointments_fragment extends BaseFragmentJava implements View.OnClickListener {

    TextView totlaap, cancleap, servedap;

    private final Context context;

    private AppCompatImageView layout;
Framentcallback framentcallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       // this.onchangefragment=(Onchangefragment) context;
    }

    public Appointments_fragment(Context mainActivity) {
        this.context = mainActivity;
    }

    public static Appointments_fragment newInstance(Context mainActivity) {

        return new Appointments_fragment(mainActivity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && Utils.haveInternet(context)) {
            getotalApointment();
        }

    }

    private void getotalApointment() {
       // mycustomdialog.show();
        serviceModel.doPostJSonRequest(getuserdata().getToken(), API_GETAPPOINTMENTSUMMARY);

    }

    @Override
    public Observable getModel() {
        return serviceModel;
    }


    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointments_fragment, container, false);

    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initeView(view);
       // mycustomdialog.show();

    }

    private void initeView(View view) {
        totlaap = view.findViewById(R.id.totlalapointment);
        cancleap = view.findViewById(R.id.cancleapointment);
        servedap = view.findViewById(R.id.serveappointment);
        layout = view.findViewById(R.id.imageView);
        layout.setOnClickListener(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == apointmentcount.class) {
            apointmentcount ap = (apointmentcount) arg;
            if (ap.getStatus() == SUCCESS_STATUS) {
                Log.e("TAG", "update: "+new Gson().toJson(ap));
                settextview(ap.getData());
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private void settextview(DataX data) {
        totlaap.setText(""+data.getTotalAppointments());
        cancleap.setText(""+data.getCancelledAppointments());
        servedap.setText(""+data.getDoneAppointments());
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                if(getActivity() instanceof Profile_Activity)
                    ((Profile_Activity) getActivity()).ssetFragment(2);
             //setFragment(HisteryOfApointmentFragment.newInstance(context), true, getActivity(), R.id.container);
                break;
        }

    }

}
