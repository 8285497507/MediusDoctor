package com.example.newdoctorsapp.custom_font;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.utility.Utils;

import java.util.ArrayList;
import java.util.Calendar;


public class CustomDays extends LinearLayout {
    public  TextView spin_ft,spin_tt,tv_mon,tv_tue,tv_wed,tv_thur,tv_fri,tv_sat,tv_sun;
    public  Spinner spin_cap;
    Context context;
    public ImageView img_delete;
    ArrayAdapter<String> capacityadapter;
    public  ArrayList<String> dayname=new ArrayList<>();
    public  boolean b_mon=false,b_tue=false,b_wed=false,b_thu=false,b_fri=false,b_sat=false,b_sun=false;
    public  String s_mon="",s_tue="",s_wed="",s_thu="",s_fri="",s_sat="",s_sun="";
    SendData sendData;

    public CustomDays(Context context, SendData sendDat) {
        super(context);
        this.context=context;
        sendData= (SendData) context;
        myView();


    }
    public CustomDays(Context context, @Nullable AttributeSet attrs) {
        super(context);
        this.context=context;
        myView();
    }
    private void initView() {
        inflate(getContext(), R.layout.custom_time_slot, this);
    }
    public View myView() {
        View v; // Creating an instance for View Object
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.custom_time_slot, this);
        tv_mon=v.findViewById(R.id.tv_mon);
        tv_tue=v.findViewById(R.id.tv_tue);
        tv_wed=v.findViewById(R.id.tv_wed);
        tv_thur=v.findViewById(R.id.tv_thur);
        tv_fri=v.findViewById(R.id.tv_fri);
        tv_sat=v.findViewById(R.id.tv_sat);
        tv_sun=v.findViewById(R.id.tv_sun);
        spin_ft=v.findViewById(R.id.spin_ft);
        spin_tt=v.findViewById(R.id.spin_tt);
        spin_cap=v.findViewById(R.id.spin_cap);
        img_delete=v.findViewById(R.id.img_delete);
        String count[]=new String[200];
        for(int i=1;i<=200;i++){
            count[i-1]=String.valueOf(i);
        }
        capacityadapter=new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line,count);
        spin_cap.setAdapter(capacityadapter);
        spin_ft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               OpenTime(spin_ft);
            }
        }); spin_tt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTime(spin_tt);
            }
        });
        Click();
        return v;
    }

    private void Click() {

        tv_mon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_mon){
                    b_mon=false;
                    tv_mon.setTextColor(getResources().getColor(R.color.menublack));
                    tv_mon.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("monday");
                    DeleteInfo(s_mon);
                }
                else {
                    b_mon=true;
                    tv_mon.setTextColor(Color.parseColor("#ffffff"));
                    tv_mon.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("monday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"monday");

                }
            }
        });
        tv_tue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_tue){
                    b_tue=false;
                    tv_tue.setTextColor(getResources().getColor(R.color.menublack));
                    tv_tue.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("tuesday");
                    DeleteInfo(s_tue);
                }
                else {
                    b_tue=true;
                    tv_tue.setTextColor(getResources().getColor(R.color.white));
                    tv_tue.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("tuesday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"tuesday");

                }
            }
        });
        tv_wed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_wed){
                    b_wed=false;
                    tv_wed.setTextColor(getResources().getColor(R.color.menublack));
                    tv_wed.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("wednesday");
                    DeleteInfo(s_wed);
                }
                else {
                    b_wed=true;
                    tv_wed.setTextColor(getResources().getColor(R.color.white));
                    tv_wed.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("wednesday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"wednesday");

                }
            }
        });
        tv_thur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_thu){
                    b_thu=false;
                    tv_thur.setTextColor(getResources().getColor(R.color.menublack));
                    tv_thur.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("thursday");
                    DeleteInfo(s_thu);
                }
                else {
                    b_thu=true;
                    tv_thur.setTextColor(getResources().getColor(R.color.white));
                    tv_thur.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("thursday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"thursday");

                }
            }
        });
        tv_fri.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_fri){
                    b_fri=false;
                    tv_fri.setTextColor(getResources().getColor(R.color.menublack));
                    tv_fri.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("friday");
                    DeleteInfo(s_fri);
                }
                else {
                    b_fri=true;
                    tv_fri.setTextColor(getResources().getColor(R.color.white));
                    tv_fri.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("friday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"friday");

                }
            }
        });
        tv_sat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_sat){
                    b_sat=false;
                    tv_sat.setTextColor(getResources().getColor(R.color.menublack));
                    tv_sat.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("saturday");
                    DeleteInfo(s_sat);
                }
                else {
                    b_sat=true;
                    tv_sat.setTextColor(getResources().getColor(R.color.white));
                    tv_sat.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("saturday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"saturday");

                }
            }
        });
        tv_sun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                if(b_sun){
                    b_sun=false;
                    tv_sun.setTextColor(getResources().getColor(R.color.menublack));
                    tv_sun.setBackgroundResource(R.drawable.rect_shadow);
                    dayname.remove("sunday");
                    DeleteInfo(s_sun);
                }
                else {
                    b_sun=true;
                    tv_sun.setTextColor(getResources().getColor(R.color.white));
                    tv_sun.setBackgroundResource(R.drawable.buttonclr);
                    dayname.add("sunday");
                    AddInfo(spin_ft.getText().toString(),spin_tt.getText().toString(),spin_cap.getSelectedItem().toString(),"sunday");

                }
            }
        });
    }

    public void OpenTime(TextView tv){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tv.setText(hourOfDay + ":" + minute);
            }
        },hour,minute,false);
       /* mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
               // tv.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute);*/
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void AddInfo(String from_time,String tii_time,String capcity,String dayname){
        sendData.AddData(from_time, tii_time, capcity, dayname);
    }
    public void DeleteInfo(String id){
        sendData.DeleteData(id);
    }

    public interface SendData{
        public void AddData(String from_time,String tii_time,String capcity,String dayname);
        public void DeleteData(String ids);
    }
    public boolean validate(){
        if(!Utils.haveInternet(context)){
            return false;
        }
        if(spin_ft.getText().toString().isEmpty() || spin_tt.getText().toString().isEmpty()
                || spin_ft.getText().toString().equalsIgnoreCase("") || spin_tt.getText().toString().equalsIgnoreCase("")){
            Utils.ShowNotFound(context,"Please fill From time and Till Time both ");
            return false;
        }
        return true;
    }
}
