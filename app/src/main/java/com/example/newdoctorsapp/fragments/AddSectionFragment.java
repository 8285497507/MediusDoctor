package com.example.newdoctorsapp.fragments;


import static com.example.newdoctorsapp.utility.Constants.API_ADDSESSION;
import static com.example.newdoctorsapp.utility.Constants.API_UPDATESCHEDULE;
import static com.example.newdoctorsapp.utility.Constants.ERROR;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.SwitchCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseDilogFragment;
import com.example.newdoctorsapp.models.AddApointMent.AddApointment;
import com.example.newdoctorsapp.models.AddApointMent.AddApointmentJava;
import com.example.newdoctorsapp.models.AddApointMent.ApointmentResponce;
import com.example.newdoctorsapp.models.AddApointMent.Data;
import com.example.newdoctorsapp.models.AddApointMent.Friday;
import com.example.newdoctorsapp.models.AddApointMent.From;
import com.example.newdoctorsapp.models.AddApointMent.Monday;
import com.example.newdoctorsapp.models.AddApointMent.Saturday;
import com.example.newdoctorsapp.models.AddApointMent.Sunday;
import com.example.newdoctorsapp.models.AddApointMent.Thursday;
import com.example.newdoctorsapp.models.AddApointMent.Till;
import com.example.newdoctorsapp.models.AddApointMent.Tuesday;

import com.example.newdoctorsapp.models.AddApointMent.Wednesday;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHour;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourSun;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourSut;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourThu;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourTu;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourfri;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourjava;
import com.example.newdoctorsapp.models.AddApointMent.WorkingHourwe;
import com.example.newdoctorsapp.models.Apointmentlist.Timing;
import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateSchedule;
import com.example.newdoctorsapp.utility.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddSectionFragment extends BaseDilogFragment implements View.OnClickListener, SwitchCompat.OnCheckedChangeListener {
    private TextView To_Time, Till_time;
    ImageView cancelimg;
    private AppCompatEditText capicity;
    private AddApointment addApointment;
    private WorkingHour workingHour;
    private Till till;
    private From from;
    private Sunday sunday;
    private Monday monday;
    private Friday friday;
    private Wednesday wednesday;
    private Tuesday tuesday;
    private Thursday thursday;
    private Saturday saturday;
    private Context context;
    private List<String> daylist;
    private int posi ;
    private String Hospitralis = null;
    private List<String> Hospitalname;
    private SwitchCompat switchCompat;
    private boolean Activity = false;
    private HospitalListBydocId hospitalList;
    String day;
    String time;
    String min;
    String[] date;
    String hospitalid = "";
    private HitApi hitApi;
    HashMap<String, String> Apdate;
    private List<Timing> data;
    private UpdateSchedule upWorkingHour;


    public AddSectionFragment(Context context, String s, List<Timing> data, String day, HitApi hitapi,int  posi) {
        this.context = context;
        this.hospitalid = day;
        this.day = s;
        this.data = data;
        this.hitApi = hitapi;
        this.posi=posi;
    }



    public static AddSectionFragment newInstance(Context context, String hospitalid, String day, List<Timing> data, HitApi hitapi, int position) {

        return new AddSectionFragment(context, day, data, hospitalid, hitapi,position);
    }



    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Apdate = new HashMap<>();
        initToolbar(view);
        toolbar.setTitle("Add New Session ");

        initView(view);

        Log.e("TAG", "on: day" + day + "\n hospital " + hospitalid);
    }

    private void initView(View view) {
        To_Time = view.findViewById(R.id.to_Time);
        Till_time = view.findViewById(R.id.till_time);
        cancelimg = view.findViewById(R.id.cancelimg);


        Till_time.setOnClickListener(this);
        To_Time.setOnClickListener(this);
        switchCompat = view.findViewById(R.id.switchCompat);

        switchCompat.setOnCheckedChangeListener(this);
        capicity = view.findViewById(R.id.capicity);
        view.findViewById(R.id.btn_next).setOnClickListener(this);
        if (data != null) {
            SetUpdateData(data.get(posi));
        }

        cancelimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void SetUpdateData(Timing data) {
        To_Time.setText(data.getFrom().getTime() + ":" + data.getFrom().getDivision());
        Till_time.setText(data.getTill().getTime() + ":" + data.getTill().getDivision());
        capicity.setText(String.valueOf(data.getCapacity()));
        switchCompat.setChecked(data.getWorking());
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == ApointmentResponce.class) {
            ApointmentResponce addApointment = (ApointmentResponce) arg;
            if (addApointment.getStatus() == SUCCESS_STATUS) {
                hitApi.GetData();
//
//                dialog(addApointment.getMessage(),day,SweetAlertDialog.SUCCESS_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                        sweetAlertDialog.dismissWithAnimation();
//                    }
//                });
                dismiss();
            } else {
                dialog(addApointment.getMessage(), ERROR,SweetAlertDialog.ERROR_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });


            }
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_Time:
                showHourPicker(To_Time, false);
                break;
            case R.id.till_time:
                showHourPicker(Till_time, false);
                break;
            case R.id.btn_next:

                if (validation() ) {
                    if (!day.equals("") && !hospitalid.equals("")) {


                        date = Till_time.getText().toString().split(":");
                        time = date[0];
                        min = date[1];

                        till = new Till(Integer.parseInt(time), Integer.parseInt(min));
                        date = To_Time.getText().toString().split(":");
                        time = date[0];
                        min = date[1];
                        from = new From(Integer.parseInt(time), Integer.parseInt(min));
                        switch (day.toLowerCase()) {
                            case "monday":
                                monday = new Monday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHour(monday));

                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), monday);

                                }
                                break;
                            case "tuesday":
                                tuesday = new Tuesday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHourTu(tuesday));
                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), tuesday);

                                }
                                break;
                            case "wednesday":
                                wednesday = new Wednesday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHourwe(wednesday));
                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), wednesday);

                                }
                                break;
                            case "thursday":
                                thursday = new Thursday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHourThu(thursday));
                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), thursday);
                                }
                                break;
                            case "friday":
                                friday = new Friday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHourfri(friday));
                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), friday);
                                }
                                break;
                            case "saturday":
                                saturday = new Saturday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHourSut(saturday));
                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), saturday);
                                }
                                break;
                            case "sunday":
                                sunday = new Sunday(Activity, from, till, Integer.parseInt(capicity.getText().toString()));
                                addApointment = new AddApointment(hospitalid, new WorkingHourSun(sunday));
                                if (data != null) {
                                    upWorkingHour = new UpdateSchedule(data.get(posi).getWorkingHour(), sunday);
                                }
                                break;

                        }
                        if (data != null && upWorkingHour != null) {
                            mycustomdialog.show();
                            serviceModel.doPostJSonRequest(upWorkingHour, getuserdata().getToken(), API_UPDATESCHEDULE);
                        } else {
                            mycustomdialog.show();
                            serviceModel.doPostJSonRequest(addApointment, getuserdata().getToken(), API_ADDSESSION);
                        }

                    }else {
                        Utils.showToastCenter(context,"Please Selecte Hospital or  Day Name");
                    }

                }
        }
    }



    private boolean validation() {

        if (TextUtils.isEmpty(To_Time.getText().toString())) {
            To_Time.setError("");
            return false;
        } else if (TextUtils.isEmpty(Till_time.getText().toString())) {
            To_Time.setError("");
            return false;
        } else if (TextUtils.isEmpty(capicity.getText().toString())) {
            capicity.setError("");
            return false;
        } else if (TextUtils.isEmpty(hospitalid)) {
            Utils.showToastCenter(getContext(), "Select Hospital");

            return false;
        }
        return true;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Activity = isChecked;
       // Utils.showToastCenter(getContext(), "Working Day Is  " + isChecked);
    }

    public interface HitApi {
        void GetData();
        void setWorkingHours(Data data);
    }


}