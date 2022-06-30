package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.utility.Constants.API_CREATEDOCTOR;
import static com.example.newdoctorsapp.utility.Constants.API_GETHOSPITALLIST;
import static com.example.newdoctorsapp.utility.Constants.PROFILE_STATUS;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.KYCDETALE;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.QUALISPECL;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.QULIFACTION;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.REGISTATION;
import static com.example.newdoctorsapp.utility.SharedPrefrancess.USER_DATA;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.activities.Adapter.recyclerviewadapter;

import com.example.newdoctorsapp.fragments.AddHospitalFragment;
import com.example.newdoctorsapp.fragments.AddconsultationFeeFragment;
import com.example.newdoctorsapp.models.CreateDoctor.ConsultationFee;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorpost;
import com.example.newdoctorsapp.models.CreateDoctor.HospitalDetail2;
import com.example.newdoctorsapp.models.CreateDoctor.Registration;
import com.example.newdoctorsapp.models.HospitalList.Data;
import com.example.newdoctorsapp.models.HospitalList.HospitalList;
import com.example.newdoctorsapp.models.Kycdetail.KycrRsponce;
import com.example.newdoctorsapp.models.login.Loginresponce;

import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Activity_Hospital_Practice extends BaseActivityJava implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button addnewHospiital, btn_next;
    private recyclerviewadapter adopter;
    private List<String> selectedhospital;
    private static List<HospitalDetail2> hospitalDetail2List;
    private CreateDoctorpost createDdoctor;
    KycrRsponce kycrRsponce;
    static HospitalDetail2 hospitalDetail2;
    private ConsultationFee consultationFee;
    private EditText searchview;

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewpractice);
        initToolbar(this);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        toolbar.setTitle("");
        selectedhospital = new ArrayList<>();
        hospitalDetail2List = new ArrayList<>();
        consultationFee = new ConsultationFee();
        hospitalDetail2 = new HospitalDetail2();
        searchview =  findViewById(R.id.searchview);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        addnewHospiital = findViewById(R.id.addnewhospital);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        addnewHospiital.setOnClickListener(this);

/*

        if (Utils.haveInternet(this)) {
            mycustomdialog.show();
            serviceModel.doPostJSonRequest(API_GETHOSPITALLIST);
        }
*/



//       List<String> qualification = (List<String>) new Gson().fromJson(qualspcl,CreateDoctorpost.class);



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
        serviceModel.doPostJSonRequest(createDdoctor, API_CREATEDOCTOR);


     //   searchview.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // adopter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adopter.getFilter().filter(s);
                Log.d("asdas","**"+ s);


            }

            @Override
            public void afterTextChanged(Editable s) {
                adopter.getFilter().filter(s);
                Log.d("asdas","**"+ s);
            }
        });
        //will start working from first character


    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == HospitalList.class) {
            HospitalList hospitalList = (HospitalList) arg;
            if (hospitalList.getStatus() == SUCCESS_STATUS) {
                setRecycleview(hospitalList);
            } else {
                Utils.showToastCenter(this, hospitalList.getMessage());
            }

        } else if (arg.getClass() == Loginresponce.class) {
            Loginresponce createDoctorResponce = (Loginresponce) arg;
            if (createDoctorResponce.getStatus() == SUCCESS_STATUS) {
                String name=createDoctorResponce.getData().getFirstName()+" "+createDoctorResponce.getData().getLastName();
                dialog(createDoctorResponce.getMessage()+" And Your Profile is Under Verification",name ,SweetAlertDialog.SUCCESS_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(Activity_Hospital_Practice.this,SplaceActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

                        startActivity(intent);
                        finishAffinity();
                        finish();
                        sweetAlertDialog.dismissWithAnimation();

                    }
                });



            }else if(createDoctorResponce.getStatus()==PROFILE_STATUS){
                dialog(createDoctorResponce.getMessage(),"Profile Status", SweetAlertDialog.WARNING_TYPE).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(Activity_Hospital_Practice.this,SplaceActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                        finishAffinity();
                        finish();
                        sweetAlertDialog.dismissWithAnimation();

                    }
                });
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

    private void setRecycleview(HospitalList hospitalList) {
        adopter = new recyclerviewadapter(this, hospitalList.getData(), new recyclerviewadapter.chekboxe() {
            @Override
            public void OnCheckedChangeListener(Data data) {
                if (data.getChecked()) {
                    showEditDialog("no",data, Activity_Hospital_Practice.this);

                } else if (!data.getChecked()) {
                    if (hospitalDetail2List.size() > 0) {
                        for (int i = 0; i <= hospitalDetail2List.size() - 1; i++) {
                            if (hospitalDetail2List.get(i).getHospital().equals(data.get_id())) {
                                hospitalDetail2List.remove(i);
                                Utils.showToastCenter(Activity_Hospital_Practice.this, "Hospital is Remove");
                            }

                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adopter);
        adopter.notifyDataSetChanged();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addnewhospital:
                AddnewHospital();
                break;
            case R.id.btn_next:
                if (hospitalDetail2List.size() > 0 && Utils.haveInternet(this)) {
                    createDdoctor.setHospitalDetails(hospitalDetail2List);
                    mycustomdialog.show();
                    MediusApp.SavePhonenumber(this,"number",createDdoctor.getPhoneNumber());
                    serviceModel.doPostJSonRequest(createDdoctor, API_CREATEDOCTOR);
                    Log.d("sdfsd","***"+createDdoctor.getFirstName());
                } else {
                    Utils.showToastCenter(this, "Select Hospital");
                }
                break;
        }
    }

    private void AddnewHospital() {
        FragmentManager fm = getSupportFragmentManager();
        AddHospitalFragment editNameDialogFragment = AddHospitalFragment.newInstance();
        editNameDialogFragment.show(fm, "Add Hospital");
    }

    public void showEditDialog(String tag,Data data, Activity_Hospital_Practice activity_hospital_practice) {

        FragmentManager fm = getSupportFragmentManager();
        AddconsultationFeeFragment editNameDialogFragment = AddconsultationFeeFragment.newInstance(tag, data, activity_hospital_practice);
        editNameDialogFragment.show(fm, "Add Hospital");
    }

    public static void Selecthospital(Context context, HospitalDetail2 hospitalDetail2) {
        if (hospitalDetail2List.size() > 0) {

            for (int i = 0; i <= hospitalDetail2List.size() - 1; i++) {
                if (hospitalDetail2List.get(i).getHospital().equals(hospitalDetail2.getHospital())) {
                    Utils.showToastCenter(context, "This is Already Added");

                } else {
                    hospitalDetail2List.add(hospitalDetail2);
                    Utils.showToastCenter(context, "This is  Added");
                }
            }
        } else {
            hospitalDetail2List.add(hospitalDetail2);
            Utils.showToastCenter(context, "This is  Added");
        }

        Log.e("TAG", "Selecthospital: " + new Gson().toJson(hospitalDetail2));
    }


//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        adopter.getFilter().filter(query);
//
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        adopter.getFilter().filter(newText);
//
//        return false;
//    }

}