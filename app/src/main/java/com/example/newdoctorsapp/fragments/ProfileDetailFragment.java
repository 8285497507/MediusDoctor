package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.utility.Constants.API_DELETEPROFILEHOSPITAL;
import static com.example.newdoctorsapp.utility.Constants.API_DELETESPECLIZATIONANDQU;
import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.API_GETSPECIALITY;
import static com.example.newdoctorsapp.utility.Constants.ERROR_STATUS;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;
import static com.example.newdoctorsapp.utility.Constants.USER_TOKEN_ID;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newdoctorsapp.Constant;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.activity.Activity_Kyc_details;
import com.example.newdoctorsapp.activity.AddQualificationDetailActivity;
import com.example.newdoctorsapp.activity.AppointmentHistoryActivity;
import com.example.newdoctorsapp.activity.AppointmentStatActivity;
import com.example.newdoctorsapp.activity.Basic_Details_Activity2;
import com.example.newdoctorsapp.activity.HoliDayCalender;
import com.example.newdoctorsapp.activity.HospitalList;
import com.example.newdoctorsapp.activity.NotificationListActivity;
import com.example.newdoctorsapp.activity.Registration_Details_Activity;
import com.example.newdoctorsapp.adapter.HospitallistAdopyter;
import com.example.newdoctorsapp.adapter.QulifactionAdopter;
import com.example.newdoctorsapp.adapter.SplizationAdopter;
import com.example.newdoctorsapp.fragments.UpdateProfile.BasiceDetailupdate;
import com.example.newdoctorsapp.models.DeleteProfile.DeleteHospital;
import com.example.newdoctorsapp.models.DeleteProfile.DeleteQulifaction;
import com.example.newdoctorsapp.models.DeleteProfile.DeleteSpeclization;
import com.example.newdoctorsapp.models.ProfileDetail.HospitalDetail;
import com.example.newdoctorsapp.models.ProfileDetail.KYCDetails;
import com.example.newdoctorsapp.models.ProfileDetail.ProfileDetail;
import com.example.newdoctorsapp.models.ProfileDetail.Qualification;
import com.example.newdoctorsapp.models.ProfileDetail.Registration;
import com.example.newdoctorsapp.models.ProfileDetail.Specialization;

import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.SpecialityBodyPartAndDisease;
import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.example.newdoctorsapp.workspace.addspecialmodel.AddSpecialResponse;
import com.example.newdoctorsapp.workspace.addspecialmodel.FcmTokenModel;
import com.example.newdoctorsapp.workspace.addspecialmodel.TokenData;
import com.example.newdoctorsapp.workspace.model.specialmodel.Data;
import com.example.newdoctorsapp.workspace.model.specialmodel.Speciality;
import com.example.newdoctorsapp.workspace.model.specialmodel.SpecializationResponse;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileDetailFragment extends BaseFragmentJava implements View.OnClickListener , Callback<SpecializationResponse> {
    public static final String TAG = ProfileDetailFragment.class.getName();
    AppCompatImageView basicdetailedit, registationedit, kycedit, qulifactionedit, addhospital, addspelization;
    private AppCompatTextView basictitel, qulifactiontitel, kyctitel, registationtitel, hospitaltitel, speclizationtitel;
    private Context context;
    private static HashMap<String, String> login  = new HashMap<>();;
    private RecyclerView recyclerView, recyHospital, recy_Speclization;
    private static List<String> splization;
    private QulifactionAdopter addqulifacitionadopter;
    private HospitallistAdopyter adopter;
    private LinearLayoutCompat mainlayout;
    private ProfileDetail profileDetail;
    private ListView listView;
    private SplizationAdopter splizationAdopter;
    LinearLayoutCompat  qulifaction_linear, spelization_linear, particepation_lin;
    Dialog dialog;
    AppCompatSpinner spinnerspec,gender;
    ArrayList<Speciality> filterData = new ArrayList<>();
    ArrayList<String> filterDatastring = new ArrayList<>();
    ArrayList<String> filterDataid = new ArrayList<>();
    ArrayList<String> filterDataselectid = new ArrayList<>();
    private List<String> speciList;
    private SpecialityBodyPartAndDisease specialityBodyPartAndDisease;

    CardView CardStat;

    public ProfileDetailFragment(Context context) {
        this.context = context;
    }

    public static ProfileDetailFragment newInstance(Context context) {


        return new ProfileDetailFragment(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
       if( Utils.haveInternet(context) && isVisibleToUser){

         //  mycustomdialog.show();

           getprofiledetail();
       }

    }

    @Override
    public Observable getModel() {
        return serviceModel;
    }


    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // setUserVisibleHint(true);
        return inflater.inflate(R.layout.fragment_profile_detail, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  login = new HashMap<>();
        RetrofitHelper.getInstance().init(getContext());

        initview(view);


    }

    private void initview(View view) {

        view.findViewById(R.id.onbasicdetail).setOnClickListener(this);
        view.findViewById(R.id.kycvisvelty).setOnClickListener(this);
        view.findViewById(R.id.cardnotification).setOnClickListener(this);
        view.findViewById(R.id.registation_visvalti).setOnClickListener(this);
        view.findViewById(R.id.qulifaction_viseval).setOnClickListener(this);
        view.findViewById(R.id.hospitalvis).setOnClickListener(this);
        view.findViewById(R.id.speclization_lin).setOnClickListener(this);
        view.findViewById(R.id.CardStat).setOnClickListener(this);
        view.findViewById(R.id.cardviewApnmtnHistory).setOnClickListener(this);
        view.findViewById(R.id.holidaycalendar).setOnClickListener(this);
        splization = new ArrayList<>();
        basicdetailedit = view.findViewById(R.id.profileedite);
        basicdetailedit.setOnClickListener(this);
        kycedit = view.findViewById(R.id.kycedit);
        kycedit.setOnClickListener(this);
        qulifactionedit = view.findViewById(R.id.QulifactionDetailedit);
        qulifactionedit.setOnClickListener(this);
        registationedit = view.findViewById(R.id.registationdetailedit);
        registationedit.setOnClickListener(this);
        addhospital = view.findViewById(R.id.hospitaladd);
        addhospital.setOnClickListener(this);
        addspelization = view.findViewById(R.id.splizationadd);
        addspelization.setOnClickListener(this);
        spinnerspec = view.findViewById(R.id.spec);

        basictitel = view.findViewById(R.id.basictitel);

        qulifactiontitel = view.findViewById(R.id.qulifactiontitel);

        registationtitel = view.findViewById(R.id.registationtitel);
        registationtitel.setOnClickListener(this);
        kyctitel = view.findViewById(R.id.kyctitel);

        hospitaltitel = view.findViewById(R.id.hospitaltitel);

        speclizationtitel = view.findViewById(R.id.speclizationtitel);


        mainlayout = view.findViewById(R.id.mainlayout);

    recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.GONE);

        recyHospital = view.findViewById(R.id.recyHospital);
        recyHospital.setLayoutManager(new LinearLayoutManager(getContext()));
        recyHospital.setHasFixedSize(true);
        recyHospital.setVisibility(View.GONE);
        recy_Speclization = view.findViewById(R.id.listview);
        recy_Speclization.setLayoutManager(new LinearLayoutManager(getContext()));
        recy_Speclization.setHasFixedSize(true);
        recy_Speclization.setVisibility(View.GONE);
        //  listView.setOnItemSelectedListener(this);
        setVisevieltiy(View.VISIBLE);
//        if (getUserVisibleHint()) {
//            if (Utils.haveInternet(context)) {
//                mycustomdialog.show();
//                getprofiledetail();
//            }
//        } else {
//            setUserVisibleHint(true);
//        }

        if (Utils.haveInternet(getContext())) {
            mycustomdialog.show();
            RetrofitHelper.getInstance().callspecialization(this);
        }
    }

    private void setVisevieltiy(AppCompatImageView addspelization, int visible) {
        addspelization.setVisibility(visible);

    }

    public void setVisevieltiy(int view) {
        addspelization.setVisibility(view);
        addhospital.setVisibility(view);
        qulifactionedit.setVisibility(view);
        basicdetailedit.setVisibility(view);
        kycedit.setVisibility(view);
        registationedit.setVisibility(view);

    }


    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();

        if (arg.getClass() == ProfileDetail.class) {
            profileDetail = (ProfileDetail) arg;

            if (profileDetail.getStatus() == SUCCESS_STATUS) {
                //   Utils.showToastCenter(getContext(), profileDetail.getMessage());
                setuserDetail(profileDetail);
            } else {
                Utils.showToastCenter(getContext(), profileDetail.getMessage());
            }
        } else if (arg.getClass() == Loginresponce.class) {
            Loginresponce responce = (Loginresponce) arg;
            if (responce.getStatus() == SUCCESS_STATUS) {
                dialog(responce.getMessage(), "Delete ", SweetAlertDialog.SUCCESS_TYPE).setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mycustomdialog.show();
                        getprofiledetail();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
            } else if (responce.getStatus() == ERROR_STATUS) {
                dialog(responce.getMessage(), "Oops..", SweetAlertDialog.ERROR_TYPE).setConfirmText(" Ok ").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
            }
        }
//       else if (arg.getClass() == SpecialityBodyPartAndDisease.class) {
//            SpecialityBodyPartAndDisease specialityBodyPartAndDisease = (SpecialityBodyPartAndDisease) arg;
//            if (specialityBodyPartAndDisease.getData().getSpeciality().size() > 0) {
//                setCuntryspiner(specialityBodyPartAndDisease.getData().getSpeciality());
//            }
//        } else {
//            Utils.showToastCenter(getContext(), specialityBodyPartAndDisease.getMessage());
//        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private void setSpilization(List<Specialization> specialization) {

        splizationAdopter = new SplizationAdopter(context, specialization);
        recy_Speclization.setAdapter(splizationAdopter);
        splizationAdopter.notifyDataSetChanged();
        // listView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, splization));

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setHospital(List<HospitalDetail> hospitalDetails) {
        //  Log.e("TAG", "setuserDetail: " + new Gson().toJson(hospitalDetails));
        adopter = new HospitallistAdopyter(getContext(), hospitalDetails, new HospitallistAdopyter.DeleteListener() {
            @Override
            public void setOnDeleteClickListener(HospitalDetail hospitalDetail) {
                dialog(hospitalDetail.getName(), "Are you sure. Do you want to Delete ?", SweetAlertDialog.WARNING_TYPE).setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        delete(hospitalDetail.get_id(), "H");
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                // delete(hospitalDetail.get_id(),"H");
            }
        });
        recyHospital.setAdapter(adopter);
        adopter.notifyDataSetChanged();
    }

    private void delete(String id, String tag) {
        mycustomdialog.show();
        if (tag.equalsIgnoreCase("h")) {

            serviceModel.doPostJSonRequest(new DeleteHospital(id), getuserdata().getToken(), API_DELETEPROFILEHOSPITAL);

        } else if (tag.equalsIgnoreCase("q")) {
            serviceModel.doPostJSonRequest(new DeleteQulifaction(Collections.singletonList(id)), getuserdata().getToken(), API_DELETESPECLIZATIONANDQU);

        } else if (tag.equalsIgnoreCase("s")) {
            serviceModel.doPostJSonRequest(new DeleteSpeclization(Collections.singletonList(id)), getuserdata().getToken(), API_DELETESPECLIZATIONANDQU);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setQulifaction(List<Qualification> qualification) {
        addqulifacitionadopter = new QulifactionAdopter(context, qualification, new QulifactionAdopter.Updatequlifaction() {
            @Override
            public void Onupdatequlifaction(Qualification qualification) {
                editelist("Q", qualification, "up");
            }

            @Override
            public void setOnDeleteClickListener(Qualification qualification) {
                dialog(qualification.getQualificationName().getName(), "Are you sure. Do you want to Delete ?", SweetAlertDialog.WARNING_TYPE).setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        delete(qualification.get_id(), "q");
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

                // delete(id,"q");
            }
        });
        recyclerView.setAdapter(addqulifacitionadopter);
        addqulifacitionadopter.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    private void setuserDetail(ProfileDetail profileDetail) {
        MediusApp.savePreferences(Constant.f_name,profileDetail.getData().getFirstName());
        MediusApp.savePreferences(Constant.l_name,profileDetail.getData().getLastName());
        MediusApp.savePreferences(Constant.mobile,profileDetail.getData().getPhoneNumber());
        MediusApp.savePreferences(Constant.ID_,profileDetail.getData().get_id());
        MediusApp.savePreferences(Constant.gender,profileDetail.getData().getGender());
        MediusApp.savePreferences(Constant.dob,profileDetail.getData().getDOB());
        MediusApp.savePreferences(Constant.email,profileDetail.getData().getEmail());

        Log.e("TAG", "setuserDetail: " + new Gson().toJson(profileDetail));

        MediusApp.getDatabase().DeleteTable("qualification_table");
        setQulifaction(profileDetail.getData().getQualification());
        for(int i=0;i<profileDetail.getData().getQualification().size();i++){
            ContentValues cv=new ContentValues();
            cv.put("_id",profileDetail.getData().getQualification().get(i).get_id());
            cv.put("name",profileDetail.getData().getQualification().get(i).getQualificationName().getName());
            cv.put("abbreviation",profileDetail.getData().getQualification().get(i).getQualificationName().getAbbreviation());
            MediusApp.getDatabase().InsertData(cv,"qualification_table");
        }

        setHospital(profileDetail.getData().getHospitalDetails());
        setSpilization(profileDetail.getData().getSpecialization());
        setKycdetail(profileDetail.getData().getKYCDetails());
        setRegistationDetail(profileDetail.getData().getRegistration());

    }

    private void setRegistationDetail(Registration registration) {
         String currentString = registration.getRegistrationDate();
        String[] separated = currentString.split("T");
        String date = separated[0];
        Log.d("sxAS","***"+registration.getRegistrationDate());
    }

    private void setKycdetail(KYCDetails kycDetails) {


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kycedit:
            case R.id.kyctitel:
            case R.id.kycvisvelty:
                startActivity(new Intent(getActivity(), Activity_Kyc_details.class).putExtra("kycdetail", new Gson().toJson(profileDetail.getData().getKYCDetails()).toString()).putExtra("Tag", "Update"));
                break;
            case R.id.registationdetailedit:
            case R.id.registationtitel:

                startActivity(new Intent(getActivity(), Registration_Details_Activity.class).putExtra("Registation", new Gson().toJson(profileDetail.getData().getRegistration()).toString()).putExtra("Tag", "Update"));
                break;

            case R.id.cardnotification:
                startActivity(new Intent(getActivity(), NotificationListActivity.class));
                break;

              /*  editelist("P", null, "");
                break;*/
            case R.id.QulifactionDetailedit:
                startActivity(new Intent(getActivity(), AddQualificationDetailActivity.class).putExtra("Tag", "Update"));

                   editelist("Q", null, "add");
                break;

            case R.id.hospitaladd:
                startActivity(new Intent(getActivity(), HospitalList.class).putExtra("Tag", "Update"));
                break;
            case R.id.CardStat:
                startActivity(new Intent(getActivity(), AppointmentStatActivity.class).putExtra("Tag", "Update"));
                break;

            case R.id.cardviewApnmtnHistory:
                startActivity(new Intent(getActivity(), AppointmentHistoryActivity.class));
                 break;
            case R.id.holidaycalendar:
                startActivity(new Intent(getActivity(), HoliDayCalender.class));

                break;
            case R.id.splizationadd:
                dialog  = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.specialization_dialog);
                Spinner spec = (Spinner) dialog.findViewById(R.id.spec);
                Button btn_addspecial = (Button) dialog.findViewById(R.id.btn_addspecial);

                btn_addspecial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.haveInternet(getContext())) {
                            mycustomdialog.show();

                            ArrayList<FcmTokenModel> sendNotificationStudentData = new ArrayList<>();
                            FcmTokenModel notificationStudentData  = new FcmTokenModel();
                            notificationStudentData.setData(filterDataselectid);

//                            sendNotificationStudentData.add(notificationStudentData);
//                            FcmTokenModel fcmTokenModel = new FcmTokenModel();
//                            fcmTokenModel.setData(sendNotificationStudentData);
                            RetrofitHelper.getInstance().callAddspecial(addSpecialResponseCallback,notificationStudentData);
                        }
                    }
                });
                spec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // String s =   filterDataid.get(position);
                     filterDataselectid.add(filterDataid.get(position));

                    // Log.d("dsasawas","***"+s);

                        try {
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        } catch (Exception e){

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,filterDatastring);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spec.setAdapter(aa);
                dialog.show();


                break;
            case R.id.onbasicdetail:
            case R.id.profileedite:
                //setVisevalti(basictitel, basic_linerar);
                 getActivity().startActivity(new Intent(getActivity(), Basic_Details_Activity2.class)
                         .putExtra("query","Update"));
                break;
            case R.id.qulifaction_viseval:
                setVisevalti(qulifactiontitel, recyclerView);

                break;



            case R.id.hospitalvis:
                setVisevalti(hospitaltitel, recyHospital);

                break;
            case R.id.speclization_lin:
                setVisevalti(speclizationtitel, recy_Speclization);

                break;
        }
    }

    private void setVisevalti(AppCompatTextView basictitel, RecyclerView recyclerView) {
        if (recyclerView.getVisibility() == View.GONE) {
            basictitel.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0);
            // setVisevieltiy(basicdetailedit,View.VISIBLE);

            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
            basictitel.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ccp_ic_arrow_drop_down, //right
                    0);
            // setVisevieltiy(basicdetailedit,View.VISIBLE);

        }
    }

    private void setVisevalti(AppCompatTextView basictitel, LinearLayoutCompat basic_linerar) {
        if (basic_linerar.getVisibility() == View.GONE) {
            basictitel.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0);            // setVisevieltiy(basicdetailedit,View.VISIBLE);
            basic_linerar.setVisibility(View.VISIBLE);
        } else {
            basic_linerar.setVisibility(View.GONE);
            basictitel.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ccp_ic_arrow_drop_down, //right
                    0);            // setVisevieltiy(basicdetailedit,View.VISIBLE);

            //  setVisevieltiy(basicdetailedit,View.GONE);

        }
    }

    private void editelist(String Type, Qualification qualification, String tag) {
        FragmentManager fm = getChildFragmentManager();
        switch (Type) {
            case "Q":
                AddQulifactionFragment addQulifactionFragment = AddQulifactionFragment.newInstance(context, qualification, tag);
                addQulifactionFragment.show(fm, "Add Qualification");

                break;
            case "P":
                BasiceDetailupdate editNameDialogFragment = BasiceDetailupdate.newInstance(context, profileDetail.getData());
                editNameDialogFragment.show(fm, "Add Qualification");
                break;

        }

    }

    public static void getprofiledetail() {

        login.put("tokentid", getuserdata().getToken());
        login.put("id", getuserdata().get_id());
        serviceModel.doPostJSonRequest(login, API_GETPROFILE);
    }



    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void onResponse(Call<SpecializationResponse> call, Response<SpecializationResponse> response) {
            if (response.isSuccessful()){
                    if (response.body().getStatus().equals(200)){
                        filterData = (ArrayList<Speciality>)response.body().getData().getSpeciality();

                        for (int i =0;i<filterData.size();i++){
                            filterDatastring.add(response.body().getData().getSpeciality().get(i).getSpecialityName());
                            filterDataid.add(response.body().getData().getSpeciality().get(i).getId());
                        }
                        //filterDatastring.add(0, "Select specialization");

                    }
            }
            else {

            }
    }

    @Override
    public void onFailure(Call<SpecializationResponse> call, Throwable t) {

    }

    Callback<AddSpecialResponse> addSpecialResponseCallback = new Callback<AddSpecialResponse>() {
        @Override
        public void onResponse(Call<AddSpecialResponse> call, Response<AddSpecialResponse> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus().equals(200)){
                    mycustomdialog.hide();
                    dialog.hide();
                }else {
                    mycustomdialog.hide();
                    dialog.hide();
                }
            }
            else {
                mycustomdialog.hide();
                dialog.hide();
            }

        }

        @Override
        public void onFailure(Call<AddSpecialResponse> call, Throwable t) {
            mycustomdialog.show();
            dialog.hide();

        }
    };
}
