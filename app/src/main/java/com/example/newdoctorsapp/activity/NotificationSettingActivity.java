package com.example.newdoctorsapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatToggleButton;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;

import java.util.Observable;

public class NotificationSettingActivity extends BaseActivityJava {
    @Override
    public Observable getModel() {
        return serviceModel;
    }


    AppCompatToggleButton togleappointment,toglehospital;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti_setting);
        initToolbar(this);
        toolbar.setTitle("Notification Settings");

        toglehospital =  findViewById(R.id.toglehospital);
        togleappointment =  findViewById(R.id.togleappointment);
        togleappointment.setText(null);
        toglehospital.setText(null);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
