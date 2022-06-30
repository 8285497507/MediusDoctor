package com.example.newdoctorsapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;

import java.util.Observable;

public class Basic_Detail_UpdateActivity extends BaseActivityJava {
    @Override
    public Observable getModel() {
        return serviceModel;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_detail_update2);
        initToolbar(this);
        toolbar.setTitle("Basic Detail");
    }



    @Override
    public void update(Observable observable, Object o) {

    }
}
