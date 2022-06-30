package com.example.newdoctorsapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.activities.Adapter.Addqulifacitionadopter;
import com.example.newdoctorsapp.fragments.AddQulifactionFragment;
import com.example.newdoctorsapp.models.AddDoctorQulification.AddQulifaction;
import com.example.newdoctorsapp.models.AddDoctorQulification.Data;
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce;
import com.example.newdoctorsapp.models.AddDoctorQulification.adDuration;
import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Activity_Qualification_Details extends BaseActivityJava implements View.OnClickListener {
    EditText q_name, q_org, q_mail;
   
    private AddQulifaction addQulifaction;
    private adDuration duration;
    private ArrayList<String> qulifactionId=new ArrayList<>();
    private static List<Data> qulifactionResponceList;
    Button q_btn_next, q_add, q_remove;
    private static RecyclerView recyclerView;
    private static List<String> addqulifactionid;
    private static Addqulifacitionadopter addqulifacitionadopter;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification_details);
        initToolbar(this);
        toolbar.setTitle("Add Hospital");
        addQulifaction=new AddQulifaction();
        qulifactionResponceList=new ArrayList<>();
        addqulifactionid=new ArrayList<>();
        duration=new adDuration();
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        q_btn_next = (Button) findViewById(R.id.btn_next);
        q_add = (Button) findViewById(R.id.q_add);
        q_remove = (Button) findViewById(R.id.q_remove);
        q_add.setOnClickListener(this);
        q_remove.setOnClickListener(this);
        q_btn_next.setOnClickListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if(addqulifactionid.size()>0){
                    SharedPrefrancess.getInstance().setDoctorqulifaction(this,SharedPrefrancess.QULIFACTION,new Gson().toJson(addqulifactionid));
                    startActivity(new Intent(this, Registration_Details_Activity.class));
                }
                break;
            case R.id.q_add:
                showEditDialog("add");
                break;
            case R.id.q_remove:
                break;
        }
    }

    public void showEditDialog(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        AddQulifactionFragment editNameDialogFragment = AddQulifactionFragment.newInstance(this, null,tag);
        editNameDialogFragment.show(fm, "Add Qualification");
    }
    @SuppressLint("NotifyDataSetChanged")
    public static void setRecyclerViewAdopter(Context context, QulifactionResponce qulifactionResponc){
        qulifactionResponceList.add(qulifactionResponc.getData());
        addqulifactionid.add(qulifactionResponc.getData().get_id());
        addqulifacitionadopter=new Addqulifacitionadopter(context,qulifactionResponceList);
        recyclerView.setAdapter(addqulifacitionadopter);
        addqulifacitionadopter.notifyDataSetChanged();


    }
}