package com.example.newdoctorsapp.activity;


import static com.example.newdoctorsapp.utility.Constants.API_LOGIN;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.models.login.OtpVerifaction;

import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Observable;

public class PhoneNumberActivity extends BaseActivityJava implements View.OnClickListener, CountryCodePicker.OnCountryChangeListener {
    EditText main_et_phone;
    Button button;
    ServiceModel serviceModel = new ServiceModel();
    Dialog mycustomdialog;
    String code = "";
    CountryCodePicker countryCodePicker;

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mycustomdialog = new MyProgressDialog().progressDialog(this);
        MediusApp.clearSharePref();
        init();

    }

    public void init() {
        main_et_phone = findViewById(R.id.main_et_phone);
        button = findViewById(R.id.otpsendbtn);
        countryCodePicker = findViewById(R.id.signUp_country_code_picker);

        button.setOnClickListener(this);

        countryCodePicker.setOnCountryChangeListener(this);
        code = countryCodePicker.getSelectedCountryCode();
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == OtpVerifaction.class) {
            OtpVerifaction responce = (OtpVerifaction) arg;
            if (responce.getStatus() == SUCCESS_STATUS) {
                Utils.showToastCenter(this, responce.getMessage());
                startActivity(new Intent(this, OtpVerification_Activity.class).putExtra("phonenumber",main_et_phone.getText().toString()));
                finish();
            } else {
                Utils.showToastCenter(this, responce.getMessage());

            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.otpsendbtn:
                validation();
                //  startActivity(new Intent(this, OtpVerification_Activity.class).putExtra("phonenumber",main_et_phone.getText().toString()));

                break;
        }
    }

    private void validation() {
        if (!main_et_phone.getText().toString().equals("") && main_et_phone.getText().toString().length() == 10) {
            if (Utils.haveInternet(this)) {
                HashMap<String, String> map = new HashMap<>();
                map.put("phoneNumber", main_et_phone.getText().toString());
                mycustomdialog.show();
                serviceModel.doPostJSonRequest(map, API_LOGIN);
            }
        } else {
            Utils.showToastCenter(this, "Please enter a valid number");
        }
    }

    @Override
    public void onCountrySelected() {
        code = countryCodePicker.getSelectedCountryCode();

    }
}