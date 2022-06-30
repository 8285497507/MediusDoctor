package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.activity.Activity_Hospital_Practice.Selecthospital;
import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_PROFILEUPDATE;
import static com.example.newdoctorsapp.utility.Constants.ERROR;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseDilogFragment;
import com.example.newdoctorsapp.models.CreateDoctor.ConsultationFee;
import com.example.newdoctorsapp.models.CreateDoctor.HospitalDetail2;
import com.example.newdoctorsapp.models.HospitalList.Data;
import com.example.newdoctorsapp.models.ProfileUpdate.HospitalDetailX;
import com.example.newdoctorsapp.models.ProfileUpdate.HospitalDetails;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateProfileResponce;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddconsultationFeeFragment extends BaseDilogFragment implements View.OnClickListener {
    Data data;
    private TextView hospitalname;
    private EditText maxfee, minfee, capicity;
    Context context;
    private String hospitalid;
    private HospitalDetail2 hospitalDetail2;
    private String Tag = "";
    private HospitalDetails hospitalDetails;
    private List<HospitalDetailX> hospitalDetailXList;
    private HospitalDetailX hospitalDetailX;

    public AddconsultationFeeFragment(String update, Data data, Context context) {
        this.context = context;
        this.data = data;
        this.Tag = update;
    }

    private ConsultationFee consultationFee;


    public static AddconsultationFeeFragment newInstance(String update, Data data, Activity activity_hospital_practice) {

        return new AddconsultationFeeFragment(update, data, activity_hospital_practice);
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
        return inflater.inflate(R.layout.fragment_addconsultation_fee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
        toolbar.setTitle("Add Hospital Consultation Fee");
        hospitalDetailXList = new ArrayList<>();
        consultationFee = new ConsultationFee();
        hospitalDetail2 = new HospitalDetail2();
        hospitalname = view.findViewById(R.id.H_name);
        if (data != null) {
            hospitalname.setText(data.getName());
        }

        maxfee = view.findViewById(R.id.H_max);
        minfee = view.findViewById(R.id.H_min);
        capicity = view.findViewById(R.id.H_capicity);
        view.findViewById(R.id.save).setOnClickListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == UpdateProfileResponce.class) {
            UpdateProfileResponce updateProfileResponce = (UpdateProfileResponce) arg;
            if (updateProfileResponce.getStatus() == SUCCESS_STATUS) {
                dialog(updateProfileResponce.getMessage(),updateProfileResponce.getData().getFirstName()+" "+updateProfileResponce.getData().getLastName() ,SweetAlertDialog.SUCCESS_TYPE).setConfirmText("ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                dismiss();
            } else {
                dialog(updateProfileResponce.getMessage(),ERROR, SweetAlertDialog.ERROR_TYPE).setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
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
            case R.id.save:

                if (validation() && !Tag.equals("")) {
                    consultationFee.setMax(Integer.valueOf(maxfee.getText().toString()));
                    consultationFee.setMin(Integer.valueOf(maxfee.getText().toString()));
                   // hospitalDetail2.setCapacity(Integer.valueOf(capicity.getText().toString()));
                    hospitalDetail2.setConsultationFee(consultationFee);
                    switch (Tag){
                        case "up":
                            hospitalDetailX = new HospitalDetailX(consultationFee, data.get_id());
                            hospitalDetailXList.add(hospitalDetailX);
                            hospitalDetails = new HospitalDetails(hospitalDetailXList);
                            mycustomdialog.show();
                            serviceModel.doPostJSonRequest(hospitalDetails, getuserdata().getToken(), API_PROFILEUPDATE);
                            break;
                        case "no":
                            hospitalDetail2.setHospital(data.get_id());
                            Selecthospital(context, hospitalDetail2);
                            dismiss();
                            break;
                    }

                }
                break;
        }
    }

    private boolean validation() {
        if (TextUtils.isEmpty(maxfee.getText().toString())) {
            maxfee.setError("");
            return false;
        }
        return true;
    }

}