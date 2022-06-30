package com.example.newdoctorsapp.RXCalling;

import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.TIME_FORMAT;
import static com.example.newdoctorsapp.utility.Constants.VIEW_DATE_FORMAT;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.login.Data;

import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseDilogFragment extends DialogFragment implements java.util.Observer {

    public abstract Observable getModel();

    public ServiceModel serviceModel = new ServiceModel();
    public Toolbar toolbar;
    public Dialog mycustomdialog;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
       // setCancelable(false);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mycustomdialog=new MyProgressDialog().progressDialog(getContext());

        Observable model = getModel();
        model.addObserver(BaseDilogFragment.this);
        return onCreateViewPost(inflater, container, savedInstanceState);
    }

    protected abstract View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public Toolbar initToolbar(View view) {
        toolbar = view.findViewById(R.id.toolbar);

       // toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_back_arrow));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return toolbar;
    }

    public static String showDatePicker(final TextView editText) {
        final Calendar calendar = Calendar.getInstance();
        if (editText.getText().length() > 0) {
            try {
                Date initDate = new SimpleDateFormat(VIEW_DATE_FORMAT, Locale.US).parse(editText.getText().toString());
                calendar.setTime(initDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate(calendar, editText, VIEW_DATE_FORMAT);
                SimpleDateFormat sdf = new SimpleDateFormat(VIEW_DATE_FORMAT, Locale.US);
               // string=sdf.format(calendar.getTime());
               // editText.setText(sdf.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(editText.getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        Calendar previous = Calendar.getInstance();
        Calendar future = Calendar.getInstance();
        previous.add(Calendar.YEAR, -100);
        future.add(Calendar.MONTH, 1);
        datePickerDialog.getDatePicker().setMinDate(previous.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(future.getTimeInMillis());
        datePickerDialog.show();
        return new SimpleDateFormat(VIEW_DATE_FORMAT,Locale.US).format(calendar.getTime());
    }

    public static void setDate(Calendar calendar, TextView editText, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        editText.setText(sdf.format(calendar.getTime()));
    }
    public static void setDate2(Calendar calendar, TextView editText, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        editText.setText(sdf.format(calendar.getTime()));
    }
    public static Date getDate(String beforeDate) throws Exception{

        SimpleDateFormat readFormat = new SimpleDateFormat(VIEW_DATE_FORMAT, Locale.US);
        Date rdate = readFormat.parse(beforeDate);

        SimpleDateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String format = writeFormat.format(rdate);

        System.out.println(format);

        return writeFormat.parse(format);
    }
    public void showHourPicker(final TextView editText, boolean is24HourView) {
        // Get Current Time
        final Calendar calendar = Calendar.getInstance();
        Log.e("TAG", "onTimeSet:3");

        if (editText.getText().length() > 0) {
            try {
                Date initDate = new SimpleDateFormat(TIME_FORMAT, Locale.US).parse(editText.getText().toString());
                calendar.setTime(initDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                showMessage(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));

                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                setDate2(calendar, editText, TIME_FORMAT);

            }
        };

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),2,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24HourView
        );
        timePickerDialog.show();


    }
    public Data getuserdata(){
        String userdata= MediusApp.getPreferences(API_GETPROFILE,"");
        Loginresponce login=new Gson().fromJson(userdata, com.example.newdoctorsapp.models.login.Loginresponce.class);
        return login.getData();
    }
    public SweetAlertDialog dialog(String massage,String hader, int type){
        SweetAlertDialog dialog=  new SweetAlertDialog(getContext(), type);
        dialog.setTitleText(hader);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentText(massage).show();
        return dialog;
    }
}
