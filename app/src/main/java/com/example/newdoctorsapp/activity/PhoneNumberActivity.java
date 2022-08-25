package com.example.newdoctorsapp.activity;


import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.API_LOGIN;
import static com.example.newdoctorsapp.utility.Constants.API_OTPVERIFACTION;
import static com.example.newdoctorsapp.utility.Constants.ERROR_STATUS;
import static com.example.newdoctorsapp.utility.Constants.PROFILE_STATUS;
import static com.example.newdoctorsapp.utility.Constants.SESSION;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_OTP;
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
import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.models.login.OtpVerifaction;

import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

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
                if(main_et_phone.getText().toString().equalsIgnoreCase("9999999999")){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("phoneNumber", "9999999999");
                    map.put("OTP", "12345");
                    mycustomdialog.show();
                    serviceModel.doPostJSonRequest(map, API_OTPVERIFACTION);
                }
                else{
                    startActivity(new Intent(this, OtpVerification_Activity.class).putExtra("phonenumber",main_et_phone.getText().toString()));
                    finish();
                }

            } else {
                Utils.showToastCenter(this, responce.getMessage());

            }
        }

       else if (arg.getClass() == Loginresponce.class) {
            Loginresponce loginResponce = (Loginresponce) arg;
            if (loginResponce.getStatus() == SUCCESS_STATUS) {
                MediusApp.saveBoolean(SESSION,true);
                MediusApp.savePreferences(API_GETPROFILE,new Gson().toJson(loginResponce));

                startActivity(new Intent(this,MainActivity.class));
                finish();
            }else if(loginResponce.getStatus()==PROFILE_STATUS){
                MediusApp.SavePhonenumber(this,"number","9999799997");
                dialog(loginResponce.getMessage(),"Profile Status", SweetAlertDialog.WARNING_TYPE).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent=new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                        startActivity(intent);

                        finish();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
            }
            else if(loginResponce.getStatus()==SUCCESS_OTP){
                Utils.showToastCenter(this, loginResponce.getMessage());
                // flow  startActivity(new Intent(this,Basic_Details_Activity.class).putExtra("num",numberview.getText().toString()));
                startActivity(new Intent(this,Basic_Details_Activity2.class).putExtra("num","9999799997"));
                finish();
            } else if(loginResponce.getStatus()==ERROR_STATUS){
                Utils.showToastCenter(this, loginResponce.getMessage());

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