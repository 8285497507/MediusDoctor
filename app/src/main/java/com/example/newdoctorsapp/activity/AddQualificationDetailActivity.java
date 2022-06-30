package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;
import static com.example.newdoctorsapp.utility.Constants.API_GETSPECIALITY;
import static com.example.newdoctorsapp.utility.Constants.API_UPDATEQULIFACTION;
import static com.example.newdoctorsapp.utility.Constants.USER_TOKEN_ID;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.activities.Adapter.Addqulifacitionadopter;
import com.example.newdoctorsapp.adapter.Addqulifacitionadopter2;
import com.example.newdoctorsapp.adapter.HospitalSpinnerAdapter;
import com.example.newdoctorsapp.adapter.QualificationListAdapter;
import com.example.newdoctorsapp.adapter.SpecializationRegAdapter;
import com.example.newdoctorsapp.adapter.SplizationAdopter;
import com.example.newdoctorsapp.custom_font.CustomDegree;
import com.example.newdoctorsapp.fragments.AppointmentsFragment;
import com.example.newdoctorsapp.interfaces.DegreeIdCallback;
import com.example.newdoctorsapp.interfaces.SpelizationIdCallback;
import com.example.newdoctorsapp.models.AddDoctorQulification.AddQulifaction;
import com.example.newdoctorsapp.models.AddDoctorQulification.Data;
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorpost;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayCalendarDatamodel;
import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
import com.example.newdoctorsapp.models.NotificationModel.Datum;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.Speciality;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.SpecialityBodyPartAndDisease;
import com.example.newdoctorsapp.models.doctoraddqual.DocrorAddData;
import com.example.newdoctorsapp.models.doctoraddqual.DoctorAddQualModel;
import com.example.newdoctorsapp.models.doctoraddqual.DoctorAddQulResponse;
import com.example.newdoctorsapp.models.getQualiModel.GetQualiData;
import com.example.newdoctorsapp.models.getQualiModel.GetQualificationResponse;
import com.example.newdoctorsapp.utility.BaseActivity;
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQualificationDetailActivity extends BaseActivityJava implements Callback<GetQualificationResponse>,
         DegreeIdCallback, CustomDegree.DegreeInterface {

    private SpecialityBodyPartAndDisease specialityBodyPartAndDisease;

    RelativeLayout addmore;
    Spinner specialization;
    private static List<Speciality> specialityList = new ArrayList<>();
    ArrayList<GetQualiData> getQualiData = new ArrayList<>();
    ArrayList<DocrorAddData> docrorAddData = new ArrayList<>();
    Button btn_next;
    private List<String> addspeciList;
    private static List<String> addqulifactionid;
    private CreateDoctorpost createDdoctor;
    String QULID="",ORGNAME="";
    ArrayList<CustomDegree> customDegreeArrayList=new ArrayList<>();
    Button btn_add_qualification;
    LinearLayout ll_qualification;
    ArrayAdapter<String> specilisation_adapter;
    ServiceModel serviceModel=new ServiceModel();
    boolean gotoscreen=false;
    String tag="Creation";
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qualificationdetail);
      try{
          tag=getIntent().getStringExtra("Tag") ;
      }
      catch (Exception e){
          tag="Creation";
      }

        addspeciList = new ArrayList<>();
        addqulifactionid = new ArrayList<>();

       InIt();


        if (Utils.haveInternet(this)) {
            mycustomdialog.show();
            serviceModel.doPostJSonRequest(USER_TOKEN_ID, API_GETSPECIALITY);

        }


        btn_add_qualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customDegreeArrayList.get(customDegreeArrayList.size()-1).edt_orgnasation.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AddQualificationDetailActivity.this, "Please select Orgnisation name", Toast.LENGTH_SHORT).show();
                }
                else{
                    DoctorAddQualModel doctorAddQualModel = new DoctorAddQualModel();
                    doctorAddQualModel.setQualificationName(degree_id);
                    doctorAddQualModel.setCertificationOrganisation(customDegreeArrayList.get(customDegreeArrayList.size()-1).edt_orgnasation.getText().toString());
                    if (Utils.haveInternet(getContext())) {
                        mycustomdialog.show();
                        RetrofitHelper.getInstance().calladddoctorqual(doctorAddQulResponseCallback,doctorAddQualModel);
                    }

                }

            }
        });



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addspeciList.isEmpty()){
                    Utils.showToastCenter(getContext(), " Select your speciality");
                    return;
                }
              if(customDegreeArrayList.size()==1){
              if(customDegreeArrayList.get(customDegreeArrayList.size()-1).edt_orgnasation.getText().toString().equalsIgnoreCase("")){
                  Toast.makeText(AddQualificationDetailActivity.this,"Please fill orgainsation details",Toast.LENGTH_SHORT).show();
              }
              else if(degree_id.equalsIgnoreCase("-1")){
                  Toast.makeText(AddQualificationDetailActivity.this,"Please select Degree details",Toast.LENGTH_SHORT).show();

              }
              else{
                  gotoscreen=true;
                  DoctorAddQualModel doctorAddQualModel = new DoctorAddQualModel();
                  doctorAddQualModel.setQualificationName(degree_id);
                  doctorAddQualModel.setCertificationOrganisation(customDegreeArrayList.get(customDegreeArrayList.size()-1).edt_orgnasation.getText().toString());
                  if (Utils.haveInternet(getContext())) {
                      mycustomdialog.show();
                      RetrofitHelper.getInstance().calladddoctorqual(doctorAddQulResponseCallback,doctorAddQualModel);
                  }
              }
              }
                else  if(customDegreeArrayList.size()>1){
                  if(customDegreeArrayList.get(customDegreeArrayList.size()-1).edt_orgnasation.getText().toString().equalsIgnoreCase("")){
                    //  Toast.makeText(AddQualificationDetailActivity.this,"Please fill orgainsation details",Toast.LENGTH_SHORT).show();
                      gotoscreen=true;
                      DoctorAddQualModel doctorAddQualModel = new DoctorAddQualModel();
                      //doctorAddQualModel.setQualificationName(degree_id);
                      doctorAddQualModel.setQualificationName(getQualiData.get(customDegreeArrayList.get(customDegreeArrayList.size()-2).spin_degree.getSelectedItemPosition()).getId());
                    //  doctorAddQualModel.setCertificationOrganisation(customDegreeArrayList.get(customDegreeArrayList.size()-2).edt_orgnasation.getText().toString());
                      doctorAddQualModel.setCertificationOrganisation(customDegreeArrayList.get(customDegreeArrayList.size()-2).edt_orgnasation.getText().toString());
                      if (Utils.haveInternet(getContext()) && tag.equalsIgnoreCase("creation") ) {
                          mycustomdialog.show();
                          RetrofitHelper.getInstance().calladddoctorqual(doctorAddQulResponseCallback,doctorAddQualModel);
                      }


                  }

                  else{
                      gotoscreen=true;
                      DoctorAddQualModel doctorAddQualModel = new DoctorAddQualModel();
                      doctorAddQualModel.setQualificationName(degree_id);
                      doctorAddQualModel.setCertificationOrganisation(customDegreeArrayList.get(customDegreeArrayList.size()-1).edt_orgnasation.getText().toString());
                      if (Utils.haveInternet(getContext())) {
                          mycustomdialog.show();
                          RetrofitHelper.getInstance().calladddoctorqual(doctorAddQulResponseCallback,doctorAddQualModel);
                      }

                  }
              }


               }
        });


    }

    private void InIt() {
        RetrofitHelper.getInstance().init(this);
        btn_next = findViewById(R.id.btn_next);
   //     specialname = findViewById(R.id.specialname);
        specialization = findViewById(R.id.specialization);
         addmore = findViewById(R.id.addmore);
        btn_add_qualification = findViewById(R.id.btn_add_qualification);
        ll_qualification = findViewById(R.id.ll_qualification);
        initToolbar(this);
        toolbar.setTitle("Qualification Detail");
    }


    @Override
    public void update(Observable o, Object arg) {//2 response
       //onResponse
        mycustomdialog.dismiss();
        if (arg.getClass() == SpecialityBodyPartAndDisease.class) {
            RetrofitHelper.getInstance().Callgetqualification(this);
            specialityBodyPartAndDisease = (SpecialityBodyPartAndDisease) arg;

            if (specialityBodyPartAndDisease.getData().getSpeciality().size() != 0) {
                specialityList =  specialityBodyPartAndDisease.getData().getSpeciality();
               ArrayList<String> specilityname=new ArrayList<>();
                specilityname.add("----Select Specilization----");
                for(int i=0;i<specialityList.size();i++){
                    specilityname.add(specialityList.get(i).getSpecialityName());
               }

                specilisation_adapter=new ArrayAdapter<String>(AddQualificationDetailActivity.this, android.R.layout.simple_dropdown_item_1line,specilityname);
                specialization.setAdapter(specilisation_adapter);
                specialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position==0){
                            return;
                        }
                        addspeciList.clear();
                        addspeciList.add(specialityList.get(position-1).get_id());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                /*SpecializationRegAdapter specializationRegAdapter = new SpecializationRegAdapter(getContext(),specialityList,
                        AddQualificationDetailActivity.this,AddQualificationDetailActivity.this::specializationidcallback );
                specialreccycler.setAdapter(specializationRegAdapter);*/
            }
        } else {
            Utils.showToastCenter(this, specialityBodyPartAndDisease.getMessage());
        }

    }



    @Override
    public void onResponse(Call<GetQualificationResponse> call, Response<GetQualificationResponse> response) {
        if (response.isSuccessful()){
            if (response.body().getStatus().equals(200)){

                getQualiData = (ArrayList<GetQualiData>)response.body().getData();
                CustomDegree customDegree=new CustomDegree(AddQualificationDetailActivity.this,getQualiData);
                customDegreeArrayList.add(customDegree);
                ll_qualification.addView(customDegree);
               /* if (response.body().getData().size() != 0) {
                    QualificationListAdapter qualificationListAdapter = new QualificationListAdapter(getContext(),getQualiData,
                            AddQualificationDetailActivity.this,AddQualificationDetailActivity.this::degreeidcallback);
                    recy_degree.setAdapter(qualificationListAdapter);
                } else {
                    QualificationListAdapter qualificationListAdapter = new QualificationListAdapter(getContext(), getQualiData,
                            AddQualificationDetailActivity.this,AddQualificationDetailActivity.this::degreeidcallback);
                    recy_degree.setAdapter(qualificationListAdapter);
                }*/

            }
        }

    }

    @Override
    public void onFailure(Call<GetQualificationResponse> call, Throwable t) {


    }


    @Override
    public void degreeidcallback(String qualid, String orgname) {

        QULID = qualid;
        ORGNAME = orgname;

    }
//save button click
    Callback<DoctorAddQulResponse> doctorAddQulResponseCallback = new Callback<DoctorAddQulResponse>() {
        @Override
        public void onResponse(Call<DoctorAddQulResponse> call, Response<DoctorAddQulResponse> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus().equals(200)){
                    mycustomdialog.hide();
                    docrorAddData.add(response.body().getData());
                    addqulifactionid.add(response.body().getData().getId());
                   // addqulifactionid.add(qulifactionResponc.getData().get_id());
                   /* addqulifacitionadopter2 = new Addqulifacitionadopter2(getContext(), docrorAddData);
                    listqualf.setAdapter(addqulifacitionadopter2);
                    addqulifacitionadopter2.notifyDataSetChanged();*/

                    if(gotoscreen){
                        if (addqulifactionid.isEmpty()){
                            Utils.showToastCenter(getContext(), " Add your qualification");
                            return;
                        }
                        SaveScreen();
                    }
                    else{
                    CustomDegree customDegree=new CustomDegree(AddQualificationDetailActivity.this,getQualiData);
                    customDegreeArrayList.add(customDegree);
                    ll_qualification.addView(customDegree);}
                }
            }

        }

        @Override
        public void onFailure(Call<DoctorAddQulResponse> call, Throwable t) {
            mycustomdialog.hide();
        }
    };
    String degree_id;
    @Override
    public void send_degree_id(String degree_name) {
        this.degree_id=degree_name;

    }

    public void SaveScreen(){
        String json = SharedPrefrancess.getInstance().GetDoctorBasic(AddQualificationDetailActivity.this, SharedPrefrancess.USER_DATA);
        createDdoctor = new Gson().fromJson(json, CreateDoctorpost.class);
        createDdoctor.setSpecialization(addspeciList);
        createDdoctor.setQualification(addqulifactionid);
        SharedPrefrancess.getInstance().setDoctorBasic(AddQualificationDetailActivity.this, SharedPrefrancess.USER_DATA, new Gson().toJson(createDdoctor).toString());
        startActivity(new Intent(getContext(), Registration_Details_Activity.class));
        gotoscreen=false;
    }

}
