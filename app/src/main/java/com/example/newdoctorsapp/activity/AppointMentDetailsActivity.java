package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.adapter.NotificationAdapter;
import com.example.newdoctorsapp.models.ConsulatantModel.ConsulatResponse;
import com.example.newdoctorsapp.models.ConvinienceModel.ConvieneceResponse;
import com.example.newdoctorsapp.models.NotificationModel.Datum;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointMentDetailsActivity extends BaseActivityJava implements Callback<ConvieneceResponse> {

    TextView ddateofbooking,bookingidtxt,
            patientname,agegender,doa,slot,
            tokennumber,docname,specilization,
            hospitalname,apnmnttyptxt,convfee,
            paymentgatefee,taxes,cosultantdfee,totalpaaidamnt;
    RelativeLayout relbookdetails,apnmtdetails;
    Dialog mydialog;
    Double taxall;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        initToolbar(this);
        mydialog=new MyProgressDialog().progressDialog(this);
        toolbar.setTitle("Appointment Details");
        ddateofbooking=findViewById(R.id.ddateofbooking);
        bookingidtxt=findViewById(R.id.bookingidtxt);
        patientname=findViewById(R.id.patientname);
        agegender=findViewById(R.id.agegender);
        doa=findViewById(R.id.doa);
        slot=findViewById(R.id.slot);
        tokennumber=findViewById(R.id.tokennumber);
        docname=findViewById(R.id.docname);
        specilization=findViewById(R.id.specilization);
        hospitalname=findViewById(R.id.hospitalname);
        relbookdetails=findViewById(R.id.relbookdetails);
        apnmnttyptxt=findViewById(R.id.apnmnttyptxt);
        apnmtdetails=findViewById(R.id.apnmtdetails);
        convfee=findViewById(R.id.convfee);
        paymentgatefee=findViewById(R.id.paymentgatefee);
        taxes=findViewById(R.id.taxes);
        cosultantdfee=findViewById(R.id.cosultantdfee);
        totalpaaidamnt=findViewById(R.id.totalpaaidamnt);

        patientname.setText(getIntent().getStringExtra("patientname"));
        ddateofbooking.setText(getIntent().getStringExtra("ddateofbooking"));
        agegender.setText(getIntent().getStringExtra("agegender"));
        slot.setText(getIntent().getStringExtra("slot"));
        tokennumber.setText(getIntent().getStringExtra("tokennumber"));
        bookingidtxt.setText(getIntent().getStringExtra("bookingidtxt"));
        docname.setText(getIntent().getStringExtra("docname"));
        specilization.setText(getIntent().getStringExtra("specilization"));
        hospitalname.setText(getIntent().getStringExtra("hospitalname"));
        doa.setText(getIntent().getStringExtra("dayofapnmnt"));



        if (getIntent().getStringExtra("apnmenttype").equals("Follow up")){
            relbookdetails.setBackgroundResource(R.color.folow_upcolor);
        }else if (getIntent().getStringExtra("apnmenttype").equals("Fresh")){
            relbookdetails.setBackgroundResource(R.color.newapnmntcolor);
            apnmnttyptxt.setText("New Appointment");
        } else  {
            relbookdetails.setBackgroundResource(R.color.canceled_color);
            apnmnttyptxt.setText("Canceled Appointment");
            apnmnttyptxt.setTextColor(Color.parseColor("#FF5F5F"));

        }


        if (Utils.haveInternet(getContext())) {
            mydialog.show();
            RetrofitHelper.getInstance().callconvfee(this);
        }
    }

    @Override
    public void onResponse(Call<ConvieneceResponse> call, Response<ConvieneceResponse> response) {
        if (response.isSuccessful()){
            mydialog.hide();
            if (response.body().getStatus().equals(200)){
                try{
                    convfee.setText("₹"+response.body().getData().get(0).getFeeAmount()+"");
                    paymentgatefee.setText("₹"+response.body().getData().get(1).getFeeAmount()+"");
                    taxes.setText("₹"+response.body().getData().get(2).getFeeAmount()+"");
                    taxall = response.body().getData().get(0).getFeeAmount()+response.body().getData().get(1).getFeeAmount()+response.body().getData().get(2).getFeeAmount();

                }
                catch (Exception e){}
                } else {

                }
            if (Utils.haveInternet(getContext())) {
               RetrofitHelper.getInstance().callcnsltfee(consulatResponseCallback,"6236ce0b35396cbe27ae651f");
              //  RetrofitHelper.getInstance().callcnsltfee(consulatResponseCallback, (getIntent().getStringExtra("apnmtId")));
            }
            }
            else {
                Snackbar.make(apnmtdetails, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
            mydialog.hide();
            }
        }


    @Override
    public void onFailure(Call<ConvieneceResponse> call, Throwable t) {
        mydialog.hide();
        Snackbar.make(apnmtdetails, "server error", Snackbar.LENGTH_LONG).show();
    }

    Callback<ConsulatResponse>consulatResponseCallback = new Callback<ConsulatResponse>() {
        @Override
        public void onResponse(Call<ConsulatResponse> call, Response<ConsulatResponse> response) {
           if (response.isSuccessful()){
               if (response.body().getStatus().equals(200)){
                   if (response.body().getData()!=null){
                       cosultantdfee.setText("₹"+response.body().getData().getOrderId().getAmount()+"");
                      int  totalpay = (int) (taxall+response.body().getData().getOrderId().getAmount());
                       totalpaaidamnt.setText(totalpay+"");
                   }else {
                   }

               }
           }
        }

        @Override
        public void onFailure(Call<ConsulatResponse> call, Throwable t) {

        }
    };
}

