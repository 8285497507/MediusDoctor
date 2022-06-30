package com.example.newdoctorsapp.fragments;


import static com.example.newdoctorsapp.utility.Constants.API_GTHOSPITALLISTBYDOCTORID;
import static com.example.newdoctorsapp.utility.Constants.API_POSTAPPOINTMENTLIST;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.activity.NotificationListActivity;
import com.example.newdoctorsapp.adapter.DoctorAppointAdopter;
import com.example.newdoctorsapp.adapter.HospitalSpinnerAdapter;
import com.example.newdoctorsapp.interfaces.HospitlNameCallback;
import com.example.newdoctorsapp.models.Appointment.Datetime;
import com.example.newdoctorsapp.models.Appointment.HospitalidwithDate;
import com.example.newdoctorsapp.models.DoctorApointmentList.Datum;
import com.example.newdoctorsapp.models.DoctorApointmentList.DoctorAppointmentList;
import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId;
import com.example.newdoctorsapp.utility.Utils;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class AppointmentsFragment extends BaseFragmentJava implements DatePickerListener, AdapterView.OnItemSelectedListener, HospitlNameCallback {
    public static final String TAG = AppointmentsFragment.class.getName();
    private Context context;
    RecyclerView recyclerView;

   /* public AppointmentsFragment(Context context) {
        this.context = context;
    }*/

    private DoctorAppointAdopter appointAdopter;
    private Spinner spinnerState;
    private static List<String> Hospitalname;
    private static List<HospitalDetail> hospitalDetails;
    HashMap<String, String> Apdate;
    private static String hospitalid = "";
    private String date;
    ImageView imgnotification;
    RecyclerView recyclerhospitallist;
    RelativeLayout rel_spinner;
    int count = 0;
    String HospitalId2,HospitalName2;
    TextView hospitalname;
    ServiceModel serviceModel=new ServiceModel();
   /* public static Fragment newInstance(Context context) {
        return new AppointmentsFragment(context);
    }*/


    @Override
    public Observable getModel() {
        return serviceModel;
    }

    View view;
    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_appointments, container, false);
        this.context=getActivity();
        imgnotification = view.findViewById(R.id.imgnotification);
        rel_spinner = view.findViewById(R.id.rel_spinner);
        hospitalname = view.findViewById(R.id.hospitalname);
        recyclerhospitallist = view.findViewById(R.id.recyclerhospitallist);
        recyclerhospitallist.setLayoutManager(new LinearLayoutManager(getContext()));
        imgnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationListActivity.class));
            }
        });

        rel_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count==0){
                    recyclerhospitallist.setVisibility(View.VISIBLE);
                    count=1;
                    Log.d("sdffd","****kk");
                    return;
                } else {
                    recyclerhospitallist.setVisibility(View.GONE);
                    Log.d("sdffd","****jkk");
                    count=0;
                    return;
                }

            }
        });
        intitsinglerowcalender(view).setListener(this);
        //   initToolbar(view).setVisibility(View.GONE);
        initeview(view);
    return view;
    }

/*

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
*/

    private void initeview(View view) {
        //toolbar.setTitle("Hi,Dr "+getuserdata().getFirstName() +" "+ getuserdata().getLastName());
      //  toolbar.setTitle("Appointment Calendar");
//        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.newprofile));
        Apdate = new HashMap<>();
        Hospitalname = new ArrayList<>();
        hospitalDetails = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spinnerState = view.findViewById(R.id.spinnerState);
        spinnerState.setOnItemSelectedListener(this);

        if (Utils.haveInternet(context)) {
            mycustomdialog.show();
            serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
        }
     //  sethorizantalcalendar(view);
        horizontalCalendar.setDate(new DateTime().plusDays(0));
    }

    private void sethorizantalcalendar(View view) {
        intitsinglerowcalender(view).setListener(this).setBackgroundColor(Color.WHITE);

        horizontalCalendar.setBackgroundColor(Color.WHITE);
        horizontalCalendar.setDate(new DateTime().plusDays(0));
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == HospitalListBydocId.class) {
            HospitalListBydocId hospitalList = (HospitalListBydocId) arg;
            if (hospitalList.getStatus() == SUCCESS_STATUS) {
                Log.d("sdffd","****checkkk");
                setspinner(hospitalList.getData().getHospitalDetails());
                hospitalDetails = (hospitalList.getData().getHospitalDetails());
                if (hospitalList.getData().getHospitalDetails().size() != 0) {
                    HospitalSpinnerAdapter hospitalSpinnerAdapter = new HospitalSpinnerAdapter(getContext(),hospitalDetails,
                            AppointmentsFragment.this,AppointmentsFragment.this::hospitlNameCallback);
                    recyclerhospitallist.setAdapter(hospitalSpinnerAdapter);
                } else {
                    recyclerhospitallist.setVisibility(View.GONE);
                    HospitalSpinnerAdapter hospitalSpinnerAdapter = new HospitalSpinnerAdapter(getContext(), hospitalDetails,
                            AppointmentsFragment.this,AppointmentsFragment.this::hospitlNameCallback);
                    recyclerhospitallist.setAdapter(hospitalSpinnerAdapter);
                }

            } else {
                Utils.showToastCenter(context, hospitalList.getMessage());
            }

        } else if (arg.getClass() == DoctorAppointmentList.class) {
            DoctorAppointmentList doctorAppointmentList = (DoctorAppointmentList) arg;
            if (doctorAppointmentList.getStatus() == SUCCESS_STATUS) {

                if (hospitalid.equals("")) {
                    mycustomdialog.show();
                    serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
                }
                setRecycleview(doctorAppointmentList.getData());

            } else {
                Utils.showToastCenter(context, doctorAppointmentList.getMessage());

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setRecycleview(List<Datum> data) {
        appointAdopter = new DoctorAppointAdopter(context, data);
        recyclerView.setAdapter(appointAdopter);
        appointAdopter.notifyDataSetChanged();
    }

    private void getAppoitment(String date, String hospitalid) {
        Log.d("sdsdsd","***"+date);
        mycustomdialog.show();
        if (Utils.haveInternet(context)) {
            Apdate.put("page", "0");
            Apdate.put("auth-header", getuserdata().getToken());

            if (!hospitalid.equals("")) {
               serviceModel.doPostJSonRequest(new HospitalidwithDate((date), hospitalid), Apdate, API_POSTAPPOINTMENTLIST);
            } else {
                serviceModel.doPostJSonRequest(new Datetime((date)), Apdate, API_POSTAPPOINTMENTLIST);

                Log.d("sdsdsd","***"+date);
            }

        }

    }

    private void setspinner(List<HospitalDetail> hospitalList) {
        Hospitalname.clear();
        Hospitalname.add(0, "Select Hospital");
        for (int i = 0; i <= hospitalList.size() - 1; i++) {
            Hospitalname.add(hospitalList.get(i).getName());
            hospitalDetails.add(hospitalList.get(i));
        }
        spinnerState.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, Hospitalname));


    }


    @Override
    public void onDateSelected(DateTime dateSelected) {
        date = ChangeDatefromet(dateSelected.toDate().toString());
        date = Utils.ChangeDatefromet(dateSelected.toDate().toString(),"EEE MMM d HH:mm:ss zzz yyyy","yyyy-MM-dd")+" 18:30:00";

        Log.e("TAG", "onDateSelected: " + ChangeDatefromet(dateSelected.toDate().toString()));
        getAppoitment(date, hospitalid);
        Log.d("zdfsfd","***"+date);
        Log.d("zdfsfd","***cvbv"+ChangeDatefromet(dateSelected.toDate().toString()));


    }

    @Override
    public void onResume() {
        super.onResume();
    //   sethorizantalcalendar(getView());
        if (Utils.haveInternet(context)){
            mycustomdialog.show();
            serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
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
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }



    @Override
    public void hospitlNameCallback(String HospitalName, String HospitalId) {
        hospitalid = HospitalId;
        HospitalName2 =HospitalName;
        hospitalname.setText(HospitalName2);
        getAppoitment(date, hospitalid);
       recyclerhospitallist.setVisibility(View.GONE);


    }
}