package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_GETHOSPITALLIST;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.activities.Adapter.recyclerviewadapter;
import com.example.newdoctorsapp.fragments.AddSectionFragment;
import com.example.newdoctorsapp.fragments.AddconsultationFeeFragment;
import com.example.newdoctorsapp.models.Apointmentlist.Timing;
import com.example.newdoctorsapp.models.CreateDoctor.ConsultationFee;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorpost;
import com.example.newdoctorsapp.models.CreateDoctor.HospitalDetail2;
import com.example.newdoctorsapp.models.HospitalList.Data;
import com.example.newdoctorsapp.models.Kycdetail.KycrRsponce;
import com.example.newdoctorsapp.utility.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class HospitalList extends BaseActivityJava implements View.OnClickListener, AddSectionFragment.HitApi {
    private RecyclerView recyclerView;
    private Button addnewHospiital, btn_next;
    private recyclerviewadapter adopter;
    private static List<HospitalDetail2> hospitalDetail2List;
    AddSectionFragment.HitApi  hitApi;
    static HospitalDetail2 hospitalDetail2;
    private EditText searchview;
  private   FragmentManager fragmentManager;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        fragmentManager = getSupportFragmentManager();
        initToolbar(this);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        toolbar.setTitle("Update Practice Hospital Details");
        intitview();
    }

    private void intitview() {


        hospitalDetail2List = new ArrayList<>();
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


        if (Utils.haveInternet(this)) {
            mycustomdialog.show();
            serviceModel.doPostJSonRequest(API_GETHOSPITALLIST);
        }


        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //adopter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adopter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                adopter.getFilter().filter(s);
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == com.example.newdoctorsapp.models.HospitalList.HospitalList.class) {
            com.example.newdoctorsapp.models.HospitalList.HospitalList hospitalList = (com.example.newdoctorsapp.models.HospitalList.HospitalList) arg;
            if (hospitalList.getStatus() == SUCCESS_STATUS) {
                setRecycleview(hospitalList);
            } else {
                Utils.showToastCenter(this, hospitalList.getMessage());
            }

        }
    }

    @Override
    public void onClick(View v) {

    }



    private void setRecycleview(com.example.newdoctorsapp.models.HospitalList.HospitalList hospitalList) {
        adopter = new recyclerviewadapter(this, hospitalList.getData(), new recyclerviewadapter.chekboxe() {
            @Override
            public void OnCheckedChangeListener(Data data) {
                if (data.getChecked()) {
                    showEditDialog("up",data,HospitalList.this);
                } else if (!data.getChecked()) {
                    if (hospitalDetail2List.size() > 0) {
                        for (int i = 0; i <= hospitalDetail2List.size() - 1; i++) {
                            if (hospitalDetail2List.get(i).getHospital().equals(data.get_id())) {
                                hospitalDetail2List.remove(i);
                                Utils.showToastCenter(HospitalList.this, "Hospital is Remove");
                            }

                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adopter);
        adopter.notifyDataSetChanged();
    }

    public void showEditDialog(String Tag,Data data, Activity activity) {

        FragmentManager fm = getSupportFragmentManager();
        AddconsultationFeeFragment editNameDialogFragment = AddconsultationFeeFragment.newInstance(Tag,data, activity);
        editNameDialogFragment.show(fm, "Add Hospital");
    }



    @Override
    public void GetData() {
        mycustomdialog.show();
        getprofiledetail();
    }

    @Override
    public void setWorkingHours(com.example.newdoctorsapp.models.AddApointMent.Data data) {

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }
}