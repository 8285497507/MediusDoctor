package com.example.newdoctorsapp.workspace.application;

import android.app.Application;
import android.content.Context;

import com.example.newdoctorsapp.workspace.RetrofitHelper;

public class medius extends Application {

    Context medius;

    @Override
    public void onCreate() {
        super.onCreate();
        medius = this.getApplicationContext();
        RetrofitHelper.getInstance().init(medius);
    }
}
