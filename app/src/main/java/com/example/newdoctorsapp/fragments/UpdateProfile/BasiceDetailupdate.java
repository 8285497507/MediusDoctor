package com.example.newdoctorsapp.fragments.UpdateProfile;

import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.API_PROFILEUPDATE;
import static com.example.newdoctorsapp.utility.Constants.ERROR;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;

import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.RXCalling.BaseDilogFragment;
import com.example.newdoctorsapp.models.ProfileDetail.Data;
import com.example.newdoctorsapp.models.ProfileDetail.Qualification;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateProfileResponce;
import com.example.newdoctorsapp.models.ProfileUpdate.basicdetailupdate;

import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BasiceDetailupdate extends BaseDilogFragment implements View.OnClickListener, SwitchCompat.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
    EditText name, Lname, ws_num, mail, loc;
    TextView p_num;
    static TextView dob;
    TextView exprience;
    AppCompatSpinner spinnerspec;
    Button btn_next;
    private Data data;
    private Context context;
    RadioGroup radioGroup;
    RadioButton radioSexButton;
    RadioButton Male, Female, other;
    private SwitchCompat switchCompat;
    private View view;
    private String dobdate="";
    public BasiceDetailupdate(Context context, Data data) {
        this.context = context;
        this.data = data;


    }

    public static BasiceDetailupdate newInstance(Context context, Data data) {
        return new BasiceDetailupdate(context, data);
    }

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_basic_update, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initToolbar(view).setTitle("Basic Detail Update");

        initeView(view);
    }

    private void initeView(View view) {
        view.findViewById(R.id.btn_next).setOnClickListener(this);

        name = view.findViewById(R.id.name);
        Lname = view.findViewById(R.id.lastname);
        dob = view.findViewById(R.id.dob);
        radioGroup = view.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        Male = view.findViewById(R.id.male);
        Female = view.findViewById(R.id.female);
        other = view.findViewById(R.id.others);
        switchCompat = view.findViewById(R.id.switchCompat);
        switchCompat.setOnCheckedChangeListener(this);
        if (data != null) {
            name.setText(data.getFirstName());
            Lname.setText(data.getLastName());

            String currentString = data.getDOB();
            String[] separated = currentString.split("T");
            String date = separated[0];
           // dob.setText(date);
          //  dobdate=showDatePicker(dob);
            String formatted = convertDateToReadable(date);

            switchCompat.setChecked(data.getActive());

            if (data.getGender().equalsIgnoreCase("male")) {

                radioGroup.check(Male.getId());

            } else if (data.getGender().equalsIgnoreCase("female")) {
                Female.setActivated(true);
                radioGroup.check(Female.getId());
            } else if (data.getGender().equals("others")) {
                radioGroup.check(other.getId());
            }
            Log.e("TAG", data.getGender() + "initeView: " + switchCompat.isActivated());
        }
    }

    public static String convertDateToReadable(String dateStr) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date formattedDate = null;
        try {
            formattedDate = inputFormat.parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDateStr = outputFormat.format(formattedDate);

        Log.d("sadS","***"+formattedDateStr);

       dob.setText(formattedDateStr);


        return formattedDateStr;
    }


    @Override
    public void update(Observable observable, Object object) {
        mycustomdialog.dismiss();
        if(object.getClass()== UpdateProfileResponce.class){
            UpdateProfileResponce updateProfileResponce= (UpdateProfileResponce) object;
            if(updateProfileResponce.getStatus()==SUCCESS_STATUS){

               // new loginResponce(new com.example.newdoctorsapp.models.login.Data())
               // MediusApp.savePreferences(API_GETPROFILE,new Gson().toJson(updateProfileResponce.getData()));

                dialog(updateProfileResponce.getMessage(),updateProfileResponce.getData().getFirstName()+" "+updateProfileResponce.getData().getLastName() ,SweetAlertDialog.SUCCESS_TYPE).setConfirmText("ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        getprofiledetail();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                dismiss();
            }else {
                dialog(updateProfileResponce.getMessage(), ERROR,SweetAlertDialog.ERROR_TYPE).setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dob:
              String s =  showDatePicker(dob);
              Log.d("hvggh","***"+s);

                break;
            case R.id.btn_next:
                if (validation()) {
                    if (Utils.haveInternet(getContext())) {
                        mycustomdialog.show();
                        serviceModel.doPostJSonRequest(new basicdetailupdate(dob.getText().toString(),
                                data.get_id(), switchCompat.isChecked(), data.getDeleted(),
                                name.getText().toString(), radioSexButton.getText().toString(),
                                Lname.getText().toString()), getuserdata().getToken(), API_PROFILEUPDATE);

                    }
                }
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

      //  Utils.showToastCenter(getContext(), "Working Day Is  " + isChecked);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.radiogroup:
                radioSexButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
        //        Utils.showToastCenter(getContext(), "" + radioSexButton.getText());
                break;
        }

    }

    private boolean validation() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("");
            return false;
        }
        if (TextUtils.isEmpty(Lname.getText().toString())) {
            Lname.setError("");
            return false;
        } else if (TextUtils.isEmpty(dob.getText().toString())) {
            dob.setError("");
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() != 0 && TextUtils.isEmpty(radioSexButton.getText().toString())) {
            Utils.showToastCenter(getContext(), " Select your Gender");
            return false;
        }
        return true;
    }


}
