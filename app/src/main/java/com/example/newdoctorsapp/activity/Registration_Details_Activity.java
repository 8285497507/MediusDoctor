package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.RXCalling.BaseDilogFragment.showDatePicker;
import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_PROFILEUPDATE;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.models.CreateDoctor.Registration;
import com.example.newdoctorsapp.models.ProfileUpdate.UpDateRegistationDetail;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateProfileResponce;
import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Registration_Details_Activity extends BaseActivityJava implements View.OnClickListener {
    private EditText r_num, r_council,r_date;
   // private TextView r_date;
    private Button btn_next;
    private Registration registration;
    private String Tag = "";
    private String id = "";
    private String dategegistation;
    LinearLayout ll_indicator;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);
        initToolbar(this);
        toolbar.setTitle("Registation Detail");
        ll_indicator = findViewById(R.id.ll_indicator);
        r_num = findViewById(R.id.r_num);
        r_council = findViewById(R.id.r_council);
        r_date = findViewById(R.id.r_date);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        r_date.setOnClickListener(this);
        if (getIntent().getStringExtra("Registation") != null) {
            Tag = getIntent().getStringExtra("Tag");
            String detail = getIntent().getStringExtra("Registation");
            com.example.newdoctorsapp.models.ProfileDetail.Registration registration = new Gson().fromJson(detail, com.example.newdoctorsapp.models.ProfileDetail.Registration.class);
            r_num.setText(registration.getRegistrationNumber());
            r_council.setText(registration.getRegistrationCouncil());
         //   r_date.setText(registration.getRegistrationDate());

            String currentString = registration.getRegistrationDate();
            String[] separated = currentString.split("T");
            String date = separated[0];
            r_date.setText(date);
            ll_indicator.setVisibility(View.GONE);
            id = registration.get_id();
            btn_next.setText("Save");
            toolbar.setTitle("Update Registation Detail");
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == UpdateProfileResponce.class) {
            UpdateProfileResponce updateProfileResponce = (UpdateProfileResponce) arg;
            if (updateProfileResponce.getStatus() == SUCCESS_STATUS) {
                getprofiledetail();
                onBackPressed();

//                dialog(updateProfileResponce.getMessage(), updateProfileResponce.getData().getFirstName(),
//                        SweetAlertDialog.SUCCESS_TYPE).setConfirmText("ok").
//                        setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        mycustomdialog.show();
//
//                       // sweetAlertDialog.dismissWithAnimation();
//
//                    }
//                });
            } else {
                dialog(updateProfileResponce.getMessage(), "Oops...", SweetAlertDialog.ERROR_TYPE).setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (validation()){
                    registration = new Registration();
                    registration.setRegistrationDate("Fri, 13 May 2022 09:51:07");
                    registration.setRegistrationNumber(r_num.getText().toString());
                    registration.setRegistrationCouncil(r_council.getText().toString());
                    if (!Tag.equals("") && !id.equals("")) {
                        mycustomdialog.show();
                        serviceModel.doPostJSonRequest(new UpDateRegistationDetail(registration), getuserdata().getToken(), API_PROFILEUPDATE);

                    } else {
                        SharedPrefrancess.getInstance().setDoctorRegistation(this, SharedPrefrancess.REGISTATION, new Gson().toJson(registration).toString());
                        startActivity(new Intent(this, Activity_Kyc_details.class));
                    }
                }else {
                    Utils.showToastCenter(this,"Please enter the all required field");
                }

                break;
            case R.id.r_date:
                dategegistation= showDatePicker(r_date);
               // r_date.setText(convertdate(r_date.getText().toString()));   ;
                r_date.setText(dategegistation);

                break;
        }
    }

    private boolean validation() {
        if (TextUtils.isEmpty(r_council.getText().toString())) {
            r_council.setError("Enter registraion council");
            return false;
        } else if (TextUtils.isEmpty(r_num.getText().toString())) {
            r_num.setError("Enter registraion no");
            return false;
        } else if (TextUtils.isEmpty(r_date.getText().toString())) {
            r_date.setError("Enter registraion date");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}