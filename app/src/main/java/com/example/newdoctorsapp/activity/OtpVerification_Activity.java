package com.example.newdoctorsapp.activity;


import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.API_OTPVERIFACTION;
import static com.example.newdoctorsapp.utility.Constants.ERROR_STATUS;
import static com.example.newdoctorsapp.utility.Constants.PROFILE_STATUS;
import static com.example.newdoctorsapp.utility.Constants.SESSION;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_OTP;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;
import static com.example.newdoctorsapp.utility.Utils.showToastCenter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.models.login.OtpVerifaction;

import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class OtpVerification_Activity extends BaseActivityJava implements View.OnClickListener {
    private ServiceModel serviceModel = new ServiceModel();
    private Dialog mycustomdialog;
    private TextView code, resend;
    private Button btn;
    private PinView pinView;
    private String phonenumber;
    private TextView numberview,timer;
    private CountDownTimer countDownTimer;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        initToolbar(this);
        toolbar.setTitle("Enter Verification Code");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));

        phonenumber = getIntent().getStringExtra("phonenumber");
        initview();

    }

    private void initview() {
        mycustomdialog = new MyProgressDialog().progressDialog(this);
        resend = findViewById(R.id.resend);
        pinView = findViewById(R.id.PinView);
        numberview = findViewById(R.id.num);
        code = findViewById(R.id.code);
        timer = findViewById(R.id.txt2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        resend.setOnClickListener(this);
        if (phonenumber != null) {
            numberview.setText(phonenumber);
        } else {
            Log.e("TAG", "somthing went to wrong: ");
        }
        countDownTimer().start();
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
     if (arg.getClass() == Loginresponce.class) {
         Loginresponce loginResponce = (Loginresponce) arg;
            if (loginResponce.getStatus() == SUCCESS_STATUS) {
                MediusApp.saveBoolean(SESSION,true);
                MediusApp.savePreferences(API_GETPROFILE,new Gson().toJson(loginResponce));

                startActivity(new Intent(this,MainActivity.class));
                finish();
            }else if(loginResponce.getStatus()==PROFILE_STATUS){
                MediusApp.SavePhonenumber(this,"number",phonenumber);
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
                startActivity(new Intent(this,Basic_Details_Activity2.class).putExtra("num",numberview.getText().toString()));
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
            case R.id.btn:
                otpvalidation();
                break;
            case R.id.resend:
                Resendotp();
                break;
        }
    }

    private void Resendotp() {
        if (Utils.haveInternet(this)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("phoneNumber", phonenumber);
            serviceModel.doPostJSonRequest(map, Constants.API_LOGIN);
            countDownTimer().start();
        }

    }

    private void otpvalidation() {
        if (pinView.getText() != null && pinView.getText()!=null) {
            if (Utils.haveInternet(this)) {
                countDownTimer().onFinish();
                HashMap<String, String> map = new HashMap<>();
                map.put("phoneNumber", numberview.getText().toString());
                map.put("OTP", pinView.getText().toString());
                mycustomdialog.show();
                serviceModel.doPostJSonRequest(map, API_OTPVERIFACTION);
            }
        } else {
            Utils.showToastCenter(this, "Please Check Your OTP ");
        }
    }
    public CountDownTimer countDownTimer(){
        countDownTimer=new CountDownTimer(60000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("00:"+millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
            timer.setText("00:00");
            }
        };

        return countDownTimer;
    }
}