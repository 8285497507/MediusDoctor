package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_CREATEDOCTOR;
import static com.example.newdoctorsapp.utility.Constants.API_KYCDETAIL;
import static com.example.newdoctorsapp.utility.Constants.API_KYCDETAILUPDATE;
import static com.example.newdoctorsapp.utility.Constants.PROFILE_STATUS;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.KYCDETALE;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.QULIFACTION;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.REGISTATION;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.USER_DATA;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorpost;
import com.example.newdoctorsapp.models.CreateDoctor.Registration;
import com.example.newdoctorsapp.models.Kycdetail.KycrRsponce;
import com.example.newdoctorsapp.models.Kycdetail.Postkycdata;
import com.example.newdoctorsapp.models.Kycdetail.Updatekycdata;
import com.example.newdoctorsapp.models.ProfileDetail.KYCDetails;
import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Activity_Kyc_details extends BaseActivityJava implements View.OnClickListener {
    private EditText pan, bank, bank_no, ifsc, adhar;
    private Button save;
private String tag="";
  private   String Id="";
  LinearLayout ll_indicator;
    public ServiceModel serviceModel = new ServiceModel();
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_details);
        initToolbar(this);
        toolbar.setTitle("Payment Detail");

        initview();
    }

    @SuppressLint("SetTextI18n")
    private void initview() {
        ll_indicator = findViewById(R.id.ll_indicator);
        pan = findViewById(R.id.pan);
        adhar = findViewById(R.id.adharcard);
        bank = findViewById(R.id.bank);
        bank_no = findViewById(R.id.bank_no);
        ifsc = findViewById(R.id.ifsc);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);
        if(getIntent().getStringExtra("kycdetail")!=null){
            tag=getIntent().getStringExtra("Tag");
            String kyc=getIntent().getStringExtra("kycdetail");
            KYCDetails kycDetails=new Gson().fromJson(kyc,KYCDetails.class);
            Id = kycDetails.get_id();
            pan.setText(kycDetails.getPanCard());
            adhar.setText(kycDetails.getAdhaarCard());
            bank.setText(kycDetails.getBankName());
            bank_no.setText(kycDetails.getBankAccountNumber());
            ifsc.setText(kycDetails.getIFSC());
            save.setText("Save");
            ll_indicator.setVisibility(View.GONE);
            toolbar.setTitle("Update KYC Details");
        }

    }
    private CreateDoctorpost createDdoctor;
    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == KycrRsponce.class) {
            KycrRsponce kycrRsponce = (KycrRsponce) arg;
            if (kycrRsponce.getStatus() == SUCCESS_STATUS) {
                if(!tag.equals("")){
                    getprofiledetail();
                    onBackPressed();
//                    dialog(kycrRsponce.getMessage(), kycrRsponce.getData().getBankName(), SweetAlertDialog.SUCCESS_TYPE).setConfirmText("Updated    ").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                            mycustomdialog.show();
//
//
//                            sweetAlertDialog.dismissWithAnimation();
//
//                        }
//                    });

                }else {
                    SharedPrefrancess.getInstance().setDoctorKycdetail(this, SharedPrefrancess.KYCDETALE, new Gson().toJson(kycrRsponce).toString());
                    SiGNUP();
                   /* dialog(kycrRsponce.getMessage(), kycrRsponce.getData().getBankName(), SweetAlertDialog.SUCCESS_TYPE).setConfirmText("Success").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            startActivity(new Intent(Activity_Kyc_details.this, Activity_Hospital_Practice.class).putExtra("kycid", kycrRsponce.getData().get_id()));

                            sweetAlertDialog.dismissWithAnimation();

                        }
                    });*/
                }



            } else {
                dialog(kycrRsponce.getMessage(), "Oops...", SweetAlertDialog.ERROR_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();

                    }
                });

            }
        }
        else if (arg.getClass() == Loginresponce.class) {
            Loginresponce createDoctorResponce = (Loginresponce) arg;
            if (createDoctorResponce.getStatus() == SUCCESS_STATUS) {
                String name=createDoctorResponce.getData().getFirstName()+" "+createDoctorResponce.getData().getLastName();
                Intent intent = new Intent(Activity_Kyc_details.this,ThankYouActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

                startActivity(intent);



            }else if(createDoctorResponce.getStatus()==PROFILE_STATUS){
                Intent intent = new Intent(Activity_Kyc_details.this,ThankYouActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

                startActivity(intent);
            } else {
                dialog(createDoctorResponce.getMessage(),"Oops..." ,SweetAlertDialog.WARNING_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();

                    }
                });

                //  Utils.showToastCenter(this, createDoctorResponce.getMessage());

            }
            SharedPrefrancess.getInstance().clearSpecificSharePref(this,USER_DATA);
            SharedPrefrancess.getInstance().clearSpecificSharePref(this,REGISTATION);
            SharedPrefrancess.getInstance().clearSpecificSharePref(this,QULIFACTION);
            SharedPrefrancess.getInstance().clearSpecificSharePref(this,KYCDETALE);
        }


    }
    KycrRsponce kycrRsponce;
    private void SiGNUP() {
        String json = SharedPrefrancess.getInstance().GetDoctorBasic(this, USER_DATA);
        createDdoctor = new Gson().fromJson(json, CreateDoctorpost.class);
        //   createDdoctor.setQualification(qualification);

        String kycdetai = SharedPrefrancess.getInstance().GetDoctorBasic(this, KYCDETALE);
        kycrRsponce = new Gson().fromJson(kycdetai, KycrRsponce.class);

        String registation = SharedPrefrancess.getInstance().GetDoctorBasic(this, REGISTATION);
        Registration registration = new Gson().fromJson(registation, Registration.class);

        createDdoctor.setKYCDetails(kycrRsponce.getData().get_id());
        createDdoctor.setRegistration(registration);

        // create doctor service hit
        MediusApp.SavePhonenumber(this,"number",createDdoctor.getPhoneNumber());
        if(Utils.haveInternet(this)){
            mycustomdialog.show();
            serviceModel.doPostJSonRequest(createDdoctor, API_CREATEDOCTOR);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if (validation()) {
                    mycustomdialog.show();
                    if(!tag.equals("") && !Id.equals("")){
                        Updatekycdata updatekycdata=new Updatekycdata(Id,ifsc.getText().toString(), adhar.getText().toString(), bank_no.getText().toString(), bank.getText().toString(), pan.getText().toString());
                        serviceModel.doPostJSonRequest(updatekycdata, API_KYCDETAILUPDATE);
                    }else {
                        Postkycdata postkycdata = new Postkycdata(ifsc.getText().toString(),  bank_no.getText().toString(), bank.getText().toString(), pan.getText().toString());

                        serviceModel.doPostJSonRequest(postkycdata, API_KYCDETAIL);
                    }

                }else {
                   // Utils.showToastCenter(this,"Plese Fill the Empety Filde");
                }
                break;
        }
    }

    private boolean validation() {
        if (TextUtils.isEmpty(pan.getText().toString())) {
         //   pan.setError("");
            Utils.showToastCenter(this,"Please enter pan number");
            return false;
        } else if (TextUtils.isEmpty(bank.getText().toString())) {
          //  bank.setError("");
            Utils.showToastCenter(this,"Please enter bank name");
            return false;
        } else if (TextUtils.isEmpty(bank_no.getText().toString())) {
         //   bank_no.setError("");
            Utils.showToastCenter(this,"Please enter account number");
            return false;
        } else if (TextUtils.isEmpty(ifsc.getText().toString())) {
            //ifsc.setError("");
            Utils.showToastCenter(this,"Please enter IFSC number");
            return false;
        }
        return true;
    }

}