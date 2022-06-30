package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;
import static com.example.newdoctorsapp.RXCalling.BaseDilogFragment.showDatePicker;
import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_GETSPECIALITY;
import static com.example.newdoctorsapp.utility.Constants.API_PROFILEUPDATE;
import static com.example.newdoctorsapp.utility.Constants.ERROR;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;
import static com.example.newdoctorsapp.utility.Constants.USER_TOKEN_ID;
import static com.example.newdoctorsapp.utility.Constants.VIEW_DATE_FORMAT;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.Constant;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.activities.Adapter.Addqulifacitionadopter;
import com.example.newdoctorsapp.fragments.AddQulifactionFragment;
import com.example.newdoctorsapp.models.AddDoctorQulification.AddQulifaction;
import com.example.newdoctorsapp.models.AddDoctorQulification.Data;
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce;
import com.example.newdoctorsapp.models.AddDoctorQulification.adDuration;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDocotorModel;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorResponse;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorpost;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayCalendarDatamodel;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateProfileResponce;
import com.example.newdoctorsapp.models.ProfileUpdate.basicdetailupdate;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.Speciality;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.SpecialityBodyPartAndDisease;
import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Basic_Details_Activity2 extends BaseActivityJava implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, Callback<CreateDoctorResponse> {
    EditText name, Lname, ws_num, mail, loc;
    TextView p_num;
    TextView exprience,dob2,dob3,dob4;
    AppCompatSpinner spinnerspec,gender;
    Button btn_next;
    RadioButton male, female, others;
    LinearLayout next;
    RadioGroup radioGroup;
    RadioButton radioSexButton;
    int day, month, year;
    String selected_gender="",datemonth = "";

    AppCompatImageView imgProfile;

    private CreateDoctorpost createDdoctor;
    private SpecialityBodyPartAndDisease specialityBodyPartAndDisease;
    private List<String> speciList;
    private List<String> addspeciList;
    ///Qulifaction
    private AddQulifaction addQulifaction;
    private adDuration duration;
    private static List<Data> qulifactionResponceList;
    Button q_btn_next, q_add, q_remove;
    private static RecyclerView recyclerView;
    private static List<String> addqulifactionid;
    private static Addqulifacitionadopter addqulifacitionadopter;
    private String dobdate="",DATE = "",datetime="";
    RelativeLayout rel_dob;
    String query="Creation";
    LinearLayout ll_indicator;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_basic_details);
        setContentView(R.layout.activity_basic_details2);
        speciList = new ArrayList<>();
        addspeciList = new ArrayList<>();
        initToolbar(this);
        toolbar.setTitle("Basic Details");
        RetrofitHelper.getInstance().init(this);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow));
        initview();

        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        list.add("Other");

   //     gender.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.dropdownitem,list);
        list.add(0, "Select gender");
        try{
            query=  getIntent().getStringExtra("query");
            if(query.equalsIgnoreCase("update")){
                btn_next.setText("Save");
                ll_indicator.setVisibility(View.GONE);
                name.setText(MediusApp.getPreferences(Constant.f_name,""));
                Lname.setText(MediusApp.getPreferences(Constant.l_name,""));
                mail.setText(MediusApp.getPreferences(Constant.email,""));
                dobdate=MediusApp.getPreferences(Constant.dob,"");
                String dob=MediusApp.getPreferences(Constant.dob,"");
                String count[]=dob.split("-");
             dob4.setText(count[2].substring(0,2));
             dob2.setText(count[1]);
             dob3.setText(count[0]);
                if(MediusApp.getPreferences(Constant.gender,"").equalsIgnoreCase("male")){
                   male.setChecked(true);
                    selected_gender = "Male";
                }
                else {
                    female.setChecked(true);
                    selected_gender = "Female";
                }
            }
        }
        catch (Exception e){
            query="Creation";
        }
    }

    private void initview() {

        ll_indicator = findViewById(R.id.ll_indicator);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        imgProfile = (AppCompatImageView) findViewById(R.id.imgProfile);
        Lname = findViewById(R.id.lastname);
   //     dob = findViewById(R.id.dob);
        dob2 = findViewById(R.id.dob2);
        dob3 = findViewById(R.id.dob3);
        dob4 = findViewById(R.id.dob4);
        p_num = findViewById(R.id.p_num);
        ws_num = findViewById(R.id.ws_num);
        mail = findViewById(R.id.mail);
        rel_dob = findViewById(R.id.rel_dob);
        spinnerspec = findViewById(R.id.spec);
        loc = findViewById(R.id.loc);
        btn_next = (Button) findViewById(R.id.btn_next);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        others = findViewById(R.id.others);
        exprience = findViewById(R.id.exprience);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        // next = findViewById(R.id.next);
        btn_next.setOnClickListener(this);
        //dob.setOnClickListener(this);
        rel_dob.setOnClickListener(this);
        dob2.setOnClickListener(this);
        dob3.setOnClickListener(this);
        dob4.setOnClickListener(this);
        exprience.setOnClickListener(this);
        spinnerspec.setOnItemSelectedListener(this);
        //qulifaction
        addQulifaction = new AddQulifaction();
        qulifactionResponceList = new ArrayList<>();

        addqulifactionid = new ArrayList<>();
        duration = new adDuration();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        q_btn_next = (Button) findViewById(R.id.btn_next);
        q_add = (Button) findViewById(R.id.q_add);
        q_remove = (Button) findViewById(R.id.q_remove);
        q_add.setOnClickListener(this);
        q_remove.setOnClickListener(this);
        q_btn_next.setOnClickListener(this);
        if(getIntent().getStringExtra("num")!=null)
            p_num.setText(getIntent().getStringExtra("num"));
        if (Utils.haveInternet(this)) {
            mycustomdialog.show();
            serviceModel.doPostJSonRequest(USER_TOKEN_ID, API_GETSPECIALITY);
        }
        getcurentdatetime();
    }

    private void getcurentdatetime() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat(VIEW_DATE_FORMAT, Locale.US);
        String formattedDate = df.format(c);
        System.out.println("Current time => " + formattedDate);

        exprience.setText(formattedDate);
        exprience.setVisibility(View.GONE);
    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == SpecialityBodyPartAndDisease.class) {
            specialityBodyPartAndDisease = (SpecialityBodyPartAndDisease) arg;
            if (specialityBodyPartAndDisease.getData().getSpeciality().size() > 0) {
                setCuntryspiner(specialityBodyPartAndDisease.getData().getSpeciality());
            }
        }

        if(arg.getClass()== UpdateProfileResponce.class){
            UpdateProfileResponce updateProfileResponce= (UpdateProfileResponce) arg;
            if(updateProfileResponce.getStatus()==SUCCESS_STATUS){

                // new loginResponce(new com.example.newdoctorsapp.models.login.Data())
                // MediusApp.savePreferences(API_GETPROFILE,new Gson().toJson(updateProfileResponce.getData()));

                dialog(updateProfileResponce.getMessage(),updateProfileResponce.getData().getFirstName()+" "+updateProfileResponce.getData().getLastName() , SweetAlertDialog.SUCCESS_TYPE).setConfirmText("ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        getprofiledetail();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

            }else {
                dialog(updateProfileResponce.getMessage(), ERROR,SweetAlertDialog.ERROR_TYPE).setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

            }
        }
        else {
            Utils.showToastCenter(this, specialityBodyPartAndDisease.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (validation() && query.equalsIgnoreCase("Creation")) {
                    createDdoctor=new CreateDoctorpost();
                    createDdoctor.setDob(dobdate);
                    createDdoctor.setEmail(mail.getText().toString());
                    createDdoctor.setFirstName(name.getText().toString());
                    createDdoctor.setLastName(Lname.getText().toString());
                    //  createDdoctor.setGender(radioSexButton.getText().toString());
                    createDdoctor.setGender(selected_gender);
                    createDdoctor.setPhoneNumber(p_num.getText().toString());
                   // createDdoctor.setPassword("12345");
                   // createDdoctor.setSpecialization(addspeciList);

                    Log.d("asdsa","***"+addspeciList);
                    SharedPrefrancess.getInstance().setDoctorBasic(getContext(), SharedPrefrancess.USER_DATA,
                            new Gson().toJson(createDdoctor).toString());

                    Log.d("sdasd","sdsdas"+SharedPrefrancess.USER_DATA);

               //     startActivity(new Intent(this, Registration_Details_Activity.class));
                  startActivity(new Intent(this, AddQualificationDetailActivity.class));

                }
                else if(validation() && query.equalsIgnoreCase("update")){
                    if (Utils.haveInternet(getContext())) {
                        mycustomdialog.show();
                        serviceModel.doPostJSonRequest(new basicdetailupdate(dobdate,
                                MediusApp.getPreferences(Constant.ID_,""), true, false,
                                name.getText().toString(), selected_gender,
                                Lname.getText().toString()), getuserdata().getToken(), API_PROFILEUPDATE);

                    }
                }

                break;
            case R.id.dob2:
            case R.id.dob3:
            case R.id.dob4:
            case R.id.rel_dob:
             //   dobdate=showDatePicker(dob);
               // dob.setVisibility(View.INVISIBLE);

                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(Basic_Details_Activity2.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth ) {
                        // TODO Auto-generated method stub
                        try {
                            Date d = new SimpleDateFormat("yyyy-MM-dd",
                                    Locale.getDefault()).parse(year + "-" + (monthOfYear) + "-" + dayOfMonth);

                            Log.d("dsd","**"+d.toString());

                            String string =d.toString();
                            String splitData[] = string.split("\\s", 2);
                            String weekday = splitData[0];

                            day = dayOfMonth;
                            String DAY = Integer.toString(day);
                            month = monthOfYear + 1;

                            String monthConverted = "" + month;
                            if (month < 10) {
                                monthConverted = "0" + monthConverted;
                            }

                            if (month == 01) {
                                datemonth = "Jan";
                            }
                            if (month == 02) {
                                datemonth = "Feb";
                            }
                            if (month == 03) {
                                datemonth = "Mar";
                            }
                            if (month == 04) {
                                datemonth = "Apr";
                            }
                            if (month == 05) {
                                datemonth = "May";
                            }
                            if (month == 06) {
                                datemonth = "Jun";
                            }
                            if (month == 07) {
                                datemonth = "Jul";
                            }
                            if (month == 8) {
                                datemonth = "Aug";
                            }
                            if (month == 9) {
                                datemonth = "Sep";
                            }
                            if (month == 10) {
                                datemonth = "Oct";
                            }
                            if (month == 11) {
                                datemonth = "Nov";
                            }
                            if (month == 12) {
                                datemonth = "Dec";
                            }
                            if (day < 10) {
                                DAY = "0" + DAY;
                            }

                            // String MONTH = Integer.toString(month);
                            year = year;
                            String YEAR = Integer.toString(year);
//
//                            dob.setText(datemonth + "-" + DAY + "-"
//                                    + YEAR);

                            dobdate = weekday+","+ DAY + " " + datemonth + " "
                                    + YEAR+" "+ "12:00:52";

                            Log.d("dsds","****"+dobdate);

                            dob4.setText(DAY);
                            dob2.setText(datemonth);
                            dob3.setText(YEAR);

                            Log.d("sdfd","***ff"+datemonth + "-" + DAY + "-"
                                    + YEAR);

                            DATE = (YEAR + "-" + (monthConverted) + "-"
                                    + DAY);

                            Log.d("sdsd","***"+DATE);

                            datetime = DATE + "T";
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

             //   datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();

                //   showDialog(0);


//                String str = dobdate;
//                String[] splitStr = str.split("\\s+");
//                String s1 = splitStr[0];
//                String s2=splitStr[1];
//                String s3=    splitStr[2];
//                String s4=    splitStr[3];
//                String s5=    splitStr[4];

             //   dob4.setText(s2);
             //   dob2.setText(s3);
            //    dob3.setText(s4);
            //    Log.d("sdasads","****"+dobdate+"***"+dob+s1+s2+s3+s4+s5);

           //     showDatePicker(dob);

               // Tue, 24 May 2022 12:00:52
                break;
            case R.id.exprience:
                showDatePicker(exprience);
                break;


            case R.id.q_add:
                showEditDialog();
                break;
            case R.id.q_remove:
                break;
        }

    }

    private void showEditDialog() {

        FragmentManager fm = getSupportFragmentManager();
        AddQulifactionFragment editNameDialogFragment = AddQulifactionFragment.newInstance(this, null, "Newadd");
        editNameDialogFragment.show(fm, "Add Qualification");
    }

    private boolean validation() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("Enter your first name");
            return false;
        }else if (TextUtils.isEmpty(Lname.getText().toString())){
            Lname.setError("Enter your last name");
            return false;
        }
//        else if (TextUtils.isEmpty(ws_num.getText().toString())) {
//            ws_num.setError("Enter whatsapp no");
//            return false;
//        }
        else if ((mail.getText().toString().equals(""))) {
            Utils.showToastCenter(this, " Enter a valid mail");
            return false;
        }
        else if (dobdate.equals("")) {
          //  dob.setError("Enter your dob");
            Utils.showToastCenter(this, "Enter your dob");
            return false;
        }
        else if (selected_gender.equals("")) {

            Log.d("dsd","ds"+selected_gender);
            Utils.showToastCenter(this, " Select your Gender");
            return false;
        }

//        else if ((p_num.getText().toString().equals(""))&& !(p_num.getText().toString().length()==10)) {
//            p_num.setError("Enter phone no");
//            return false;
//        }


//        else if (addspeciList.size()==0) {
//            Utils.showToastCenter(this, " Enter your specialization");
//            return false;
//        }
//        else if (addqulifactionid.isEmpty()) {
//            Utils.showToastCenter(this, " Add your qualification");
//            return false;
//        }
        return true;
    }

    private void setCuntryspiner(List<Speciality> country) {
        speciList.clear();
        speciList.add(0, "Select Specialization");
        for (int i = 0; i <= country.size() - 1; i++) {
            speciList.add(country.get(i).getSpecialityName());
        }
        spinnerspec.setAdapter(new ArrayAdapter<>(this, R.layout.dropdownitem, speciList));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spec:
                if (parent.getAdapter().getItemId(position) != 0) {
                    addspeciList.add(specialityBodyPartAndDisease.getData().getSpeciality().get(position - 1).get_id());
                    Utils.showToastCenter(this, specialityBodyPartAndDisease.getData().getSpeciality().get(position - 1).getSpecialityName());
                } else {
                    Utils.showToastCenter(this, "Select An Other Specialization");
                }

        }

    }

    private void countaddspeciList(String id) {
        if (addspeciList.size() > 0) {
            for (int i = 0; i <= addspeciList.size() - 1; i++) {
                if (!addspeciList.get(i).equals(id)) {
                    addspeciList.add(id);
                } else {

                    Utils.showToastCenter(this, "This  Specialization Already Selected ");
                }
            }
        } else {
            addspeciList.add(id);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.radiogroup:
                if(male.isChecked())
                {
                    selected_gender = "Male";
                }
                else if(female.isChecked()) {
                    selected_gender = "Female";
                }
                else if(others.isChecked()) {
                    selected_gender = "Other";
                }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public static void setRecyclerViewAdopter(Context context, QulifactionResponce qulifactionResponc) {
        qulifactionResponceList.add(qulifactionResponc.getData());
        addqulifactionid.add(qulifactionResponc.getData().get_id());
        addqulifacitionadopter = new Addqulifacitionadopter(context, qulifactionResponceList);
        recyclerView.setAdapter(addqulifacitionadopter);
        addqulifacitionadopter.notifyDataSetChanged();


    }

    @Override
    public void onResponse(Call<CreateDoctorResponse> call, Response<CreateDoctorResponse> response) {
        if (response.isSuccessful()){
            if (response.body().getStatus().equals(200)){
                mycustomdialog.hide();
            }

        }
    }

    @Override
    public void onFailure(Call<CreateDoctorResponse> call, Throwable t) {
        mycustomdialog.hide();
    }


    class CitiesSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            selected_gender =  (gender.getSelectedItem().toString());
            if (selected_gender.equalsIgnoreCase("Male")) {
                imgProfile.setImageResource(R.drawable.male);
                imgProfile.setVisibility(View.VISIBLE);
            } else if (selected_gender.equalsIgnoreCase("Female")) {
                imgProfile.setImageResource(R.drawable.female);
                imgProfile.setVisibility(View.VISIBLE);
            }
            else if (selected_gender.equalsIgnoreCase("Other")) {
                imgProfile.setImageResource(R.drawable.newprofile);
                imgProfile.setVisibility(View.VISIBLE);
            }
            else if (selected_gender.equalsIgnoreCase("Select gender")) {
                imgProfile.setVisibility(View.GONE);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
