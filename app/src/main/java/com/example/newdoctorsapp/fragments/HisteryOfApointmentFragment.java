package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.utility.Constants.API_POSTAPPOINTMENTLIST;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.adapter.DoctorAppointAdopter;
import com.example.newdoctorsapp.adapter.HospitalApprovalAdapter;
import com.example.newdoctorsapp.fragments.UpdateProfile.HospitalAddFragment;
import com.example.newdoctorsapp.models.Appointment.Datetime;
import com.example.newdoctorsapp.models.Appointment.HospitalidwithDate;
import com.example.newdoctorsapp.models.DoctorApointmentList.Data;
import com.example.newdoctorsapp.models.HospitalApprovalList.HospitalApprovalData;
import com.example.newdoctorsapp.models.HospitalApprovalList.HospitalApprovalResponse;
import com.example.newdoctorsapp.models.HospitalList.Address;
import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.google.android.material.snackbar.Snackbar;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HisteryOfApointmentFragment extends BaseFragmentJava implements DatePickerListener,
        AdapterView.OnItemSelectedListener, Callback<HospitalApprovalResponse> {
    public static final String TAG=HisteryOfApointmentFragment.class.getName();
    private Context context;
    private RecyclerView recyclerView;
    private DoctorAppointAdopter appointAdopter;
    private Spinner spinnerState;
    private static List<String> Hospitalname;
    private static List<HospitalDetail> hospitalDetails;
    HashMap<String, String> Apdate;
    private String hospitalid = "";
    private Date date;
    ImageView mFoatingAddHospital;
    RelativeLayout rel_historyhospital;
    ArrayList<HospitalApprovalData> hospitalApprovalData = new ArrayList<>();
    AppCompatTextView tv_title;
    ImageButton btn_back;

    public HisteryOfApointmentFragment(Context context) {
        this.context = context;
    }
    public static HisteryOfApointmentFragment newInstance(Context context) {
        return new HisteryOfApointmentFragment(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public Observable getModel() {
        return serviceModel;
    }



    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_histery_of_apointment, container, false);
    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitHelper.getInstance().init(getContext());

    //    toolbar.setTitleTextColor(getResources().getColor(R.color.black));
      //  initToolbar(view).setVisibility(View.VISIBLE);
        Apdate = new HashMap<>();
        Hospitalname = new ArrayList<>();
        hospitalDetails = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        tv_title = view.findViewById(R.id.tv_title);
        btn_back = view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getActivity().onBackPressed();
            }
        });
        tv_title.setText("Hospital List");
        rel_historyhospital = view.findViewById(R.id.rel_historyhospital);
        mFoatingAddHospital = view.findViewById(R.id.mFoatingAddHospital);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spinnerState = view.findViewById(R.id.spinnerState);
        spinnerState.setOnItemSelectedListener(this);
        intitsinglerowcalender(view).setListener(this);
        horizontalCalendar.setBackgroundColor(Color.WHITE);
        horizontalCalendar.setDate(new DateTime().plusDays(0));

        mFoatingAddHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment homepage = new HospitalAddFragment();
                FragmentTransaction fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager()
                        .beginTransaction();
                fragmentManager.replace(R.id.container, homepage);
                fragmentManager.addToBackStack(null);
                fragmentManager.commit();
            }
        });

        if (Utils.haveInternet(getContext())) {
            mycustomdialog.show();
            RetrofitHelper.getInstance().callhospitalApproval(this);
        }
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {


    }

    @SuppressLint("NotifyDataSetChanged")
    private void setRecycleview(List<Data> data) {
        appointAdopter = new DoctorAppointAdopter(context, data);
        recyclerView.setAdapter(appointAdopter);
        appointAdopter.notifyDataSetChanged();
    }

    private void getAppoitment(Date date, String hospitalid) {

        mycustomdialog.show();
        if (Utils.haveInternet(context)) {
            Apdate.put("page", "0");
            Apdate.put("auth-header", getuserdata().getToken());

            if (!hospitalid.equals("")) {
                serviceModel.doPostJSonRequest(new HospitalidwithDate(ChangeDatefromet(date.toString()), hospitalid), Apdate, API_POSTAPPOINTMENTLIST);
            } else {
                serviceModel.doPostJSonRequest(new Datetime(ChangeDatefromet(date.toString())), Apdate, API_POSTAPPOINTMENTLIST);
            }

        }

    }

    private void setspinner(List<HospitalDetail> hospitalList) {
        Hospitalname.clear();
        Hospitalname.add(0, "Selects Hospital");
        hospitalDetails.add(0, new HospitalDetail(0, "0", new Address(0, "", "", "", "", "", ""), "", ""));
        for (int i = 0; i <= hospitalList.size() - 1; i++) {
            Hospitalname.add(hospitalList.get(i).getName());
            hospitalDetails.add(hospitalList.get(i));
        }
        spinnerState.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, Hospitalname));


    }


    @Override
    public void onDateSelected(DateTime dateSelected) {
        date = dateSelected.toDate();
        Log.e("TAG", "onDateSelected: " + ChangeDatefromet(dateSelected.toDate().toString()));
        getAppoitment(date, hospitalid);

    }

    @Override
    public void onResume() {
//        if (Utils.haveInternet(context))
//            mycustomdialog.show();
//        serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());

        super.onResume();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter().getItemId(position) > 0) {

            hospitalid = hospitalDetails.get(position-1).get_id();
            getAppoitment(date, hospitalid);

        } else {
            hospitalid = "";
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onResponse(Call<HospitalApprovalResponse> call, Response<HospitalApprovalResponse> response) {
        if (response.isSuccessful()) {
            mycustomdialog.hide();


            if (response.body().getStatus().equals(200)) {
                hospitalApprovalData = (ArrayList<HospitalApprovalData>) response.body().getData();
                Log.d("sadas", "***" + hospitalApprovalData.size());
                if (hospitalApprovalData.size() != 0) {
                    HospitalApprovalAdapter hospitalApprovalAdapter = new HospitalApprovalAdapter(getContext(), hospitalApprovalData, HisteryOfApointmentFragment.this);
                    recyclerView.setAdapter(hospitalApprovalAdapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    HospitalApprovalAdapter hospitalApprovalAdapter = new HospitalApprovalAdapter(getContext(), hospitalApprovalData, HisteryOfApointmentFragment.this);
                    recyclerView.setAdapter(hospitalApprovalAdapter);
                }

            } else {
                Snackbar.make(rel_historyhospital, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                mycustomdialog.hide();
            }
        }


    }
    @Override
    public void onFailure(Call<HospitalApprovalResponse> call, Throwable t) {
        mycustomdialog.hide();
        Log.d("sadas","***"+ t);
    }

    public void BackPressed(View view){
        onBackPressed();
    }
}