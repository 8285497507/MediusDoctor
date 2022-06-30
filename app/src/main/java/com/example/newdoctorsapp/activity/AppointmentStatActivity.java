package com.example.newdoctorsapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;

import org.joda.time.DateTime;

import java.util.Observable;

public class AppointmentStatActivity extends BaseActivityJava implements DatePickerListener {
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentstat);
        intitsinglerowcalender(AppointmentStatActivity.this).setListener(this);
        initToolbar(this);
        toolbar.setTitle("Appointment Stat");
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onDateSelected(DateTime dateSelected) {

    }
}
