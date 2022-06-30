package com.example.newdoctorsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;

import java.util.Observable;

public class OtheraActivity extends BaseActivityJava {
    @Override
    public Observable getModel() {
        return serviceModel;
    }

  WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initToolbar(this);
        toolbar.setTitle(getIntent().getStringExtra("intentclass"));
        mWebView = findViewById(R.id.mWebView);
        mWebView.loadUrl("http://www.medius.co.in/privacy");
    }



    @Override
    public void update(Observable observable, Object o) {

    }
}
