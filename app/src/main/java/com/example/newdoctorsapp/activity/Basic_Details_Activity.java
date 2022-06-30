package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.RXCalling.BaseDilogFragment.showDatePicker;
import static com.example.newdoctorsapp.utility.Constants.API_GETSPECIALITY;
import static com.example.newdoctorsapp.utility.Constants.USER_TOKEN_ID;
import static com.example.newdoctorsapp.utility.Constants.VIEW_DATE_FORMAT;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.activities.Adapter.Addqulifacitionadopter;
import com.example.newdoctorsapp.fragments.AddQulifactionFragment;
import com.example.newdoctorsapp.models.AddDoctorQulification.AddQulifaction;
import com.example.newdoctorsapp.models.AddDoctorQulification.Data;
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce;
import com.example.newdoctorsapp.models.AddDoctorQulification.adDuration;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorpost;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.Speciality;
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.SpecialityBodyPartAndDisease;
import com.example.newdoctorsapp.utility.SharedPrefrancess;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

public class Basic_Details_Activity extends BaseActivityJava implements View.OnClickListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    EditText name, Lname, ws_num, mail, loc;
    TextView p_num;
    TextView dob,exprience;
    AppCompatSpinner spinnerspec,gender;
    Button btn_next;
    RadioButton male, female, others;
    LinearLayout next;
    RadioGroup radioGroup;
    RadioButton radioSexButton;

    String selected_gender;

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
    private String dobdate;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);
     //  setContentView(R.layout.activity_basic_details2);
        speciList = new ArrayList<>();
        addspeciList = new ArrayList<>();
        initToolbar(this);
        toolbar.setTitle("Basic Details");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow));
        initview();

        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        list.add("Other");

        gender.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.dropdownitem,list);
        list.add(0, "Select gender");
      //  aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(aa);
        gender.setOnItemSelectedListener(new CitiesSpinnerClass());

//        Button crashButton = new Button(this);
//        crashButton.setText("Test Crash");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void initview() {

        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        imgProfile = (AppCompatImageView) findViewById(R.id.imgProfile);
        Lname = findViewById(R.id.lastname);
        dob = findViewById(R.id.dob);
        p_num = findViewById(R.id.p_num);
        ws_num = findViewById(R.id.ws_num);
        mail = findViewById(R.id.mail);
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
        dob.setOnClickListener(this);
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
        } else {
            Utils.showToastCenter(this, specialityBodyPartAndDisease.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (validation()) {
                    createDdoctor=new CreateDoctorpost();
                    createDdoctor.setDob(dobdate);
                    createDdoctor.setEmail(mail.getText().toString());
                    createDdoctor.setFirstName(name.getText().toString());
                    createDdoctor.setLastName(Lname.getText().toString());
                  //  createDdoctor.setGender(radioSexButton.getText().toString());
                    createDdoctor.setGender(selected_gender);
                    createDdoctor.setPhoneNumber(p_num.getText().toString());
                    createDdoctor.setPassword("12345");
                    createDdoctor.setSpecialization(addspeciList);

                    Log.d("asdsa","***"+addspeciList);
                    createDdoctor.setQualification(addqulifactionid);
                    createDdoctor.setOverallExperience(exprience.getText().toString());
                    SharedPrefrancess.getInstance().setDoctorBasic(this, SharedPrefrancess.USER_DATA, new Gson().toJson(createDdoctor).toString());
                    Log.d("sdasd","sdsdas"+SharedPrefrancess.USER_DATA);
                    startActivity(new Intent(this, Registration_Details_Activity.class));

                }

                break;
            case R.id.dob:
                dobdate=showDatePicker(dob);

                //showDatePicker(dob);


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
        else if (selected_gender.equalsIgnoreCase("Select gender")) {
            Utils.showToastCenter(this, " Select your Gender");
            return false;
        }
        else if (TextUtils.isEmpty(dob.getText().toString())) {
            dob.setError("Enter your dob");
            return false;
        }
        else if ((p_num.getText().toString().equals(""))&& !(p_num.getText().toString().length()==10)) {
            p_num.setError("Enter phone no");
            return false;
        }
        else if (TextUtils.isEmpty(ws_num.getText().toString())) {
            ws_num.setError("Enter whatsapp no");
            return false;
        }
            else if ((mail.getText().toString().equals(""))) {
            Utils.showToastCenter(this, " Enter a valid mail");
            return false;
        }   else if (addspeciList.size()==0) {
            Utils.showToastCenter(this, " Enter your specialization");
            return false;
        }
        else if (addqulifactionid.isEmpty()) {
            Utils.showToastCenter(this, " Add your qualification");
            return false;
        }
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
                radioSexButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                Utils.showToastCenter(this, "" + radioSexButton.getText());
                break;
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