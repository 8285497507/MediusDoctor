package com.example.newdoctorsapp.fragments;


import static com.example.newdoctorsapp.utility.Constants.API_APPOINTMENTLIST;
import static com.example.newdoctorsapp.utility.Constants.API_GTHOSPITALLISTBYDOCTORID;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.demod.RXCalling.ServiceModel;
import com.example.medius.models.AppoinmentModel.AppoinmentMod;
import com.example.medius.models.AppoinmentModel.Till;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.activity.HoliDayCalender;
import com.example.newdoctorsapp.adapter.AppointmentScheduleAdapter;
import com.example.newdoctorsapp.adapter.ParentAdopter;
import com.example.newdoctorsapp.adapter.ScheduleDayListAdopter;
import com.example.newdoctorsapp.custom_font.CustomDays;
import com.example.newdoctorsapp.models.AddApointMent.Data;
import com.example.newdoctorsapp.models.Apointmentlist.Apointmentlist;


import com.example.newdoctorsapp.models.Apointmentlist.From;
import com.example.newdoctorsapp.models.Apointmentlist.Timing;
import com.example.newdoctorsapp.models.Apointmentlist.WorkingHour;

import com.example.newdoctorsapp.models.Apointmentlist.getWorkingHours;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.GetHoliDayDataModel;
import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId;
import com.example.newdoctorsapp.models.SlotDelModel.SlotDelResponse;
import com.example.newdoctorsapp.models.SlotDelModel.slotdelsendmodel;
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentSheduleResponse;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentWorkingHour;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppoitmentData;
import com.example.newdoctorsapp.workspace.getworkingworkihgoursmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class schedule_appointment_fragment extends BaseFragmentJava implements AddSectionFragment.HitApi,
        View.OnClickListener, AdapterView.OnItemSelectedListener, Callback<AppointmentSheduleResponse> ,CustomDays.SendData {
  public static final String TAG=schedule_appointment_fragment.class.getName();
    private RecyclerView Sunday_Recycleview, Monday_Rec,
            Tuseday_Recy, Wednesday_Recy, Thrisday_Recy, Friday_recyc, Suterday_Recy,recycerschedule;

    private Activity context;
    private Spinner spinnerHospital;
    private List<String> Hospitalname;
    private HospitalListBydocId hospitalList;
    private List<HospitalDetail> Hospitalid;
    HospitalDetail hospitalDetail;
    HashMap<String, String> Apdate;
    private String Hospitralis = null;
    private ParentAdopter parentAdopter;
    private ScheduleDayListAdopter scheduleDayListAdopter;
    private String hospitalid = "";
    AddSectionFragment.HitApi hitapi;
    TextView Addsession;
    private List<Timing> timings;
    List<WorkingHour> staticdata;
    EditText consultancyfee,prescriptionvalidity;
    String daysname[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    ArrayList<CustomDays> customDaysArrayList=new ArrayList<>();
    LinearLayout ll_time_slot;
    TextView add_day;
    AppCompatButton btn_save;
    AppCompatSpinner spin_doctor;
    ArrayAdapter<String> adapter;
    ServiceModel serviceModel=new ServiceModel();
    //Dialog mydialog;
    String token;
    int count=0;
    CustomDays.SendData senddata;
    List<AppointmentWorkingHour> appointmentWorkingHours = new ArrayList<>();
    public schedule_appointment_fragment(Activity context) {
        this.context = context;
        // Required empty public constructor
    }

    public static Fragment newInstance(Activity mainActivity) {
        return new schedule_appointment_fragment(mainActivity);
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
        hitapi = this;
        senddata=this;
        //return inflater.inflate(R.layout.fragment_schedule_appointment_fragment, container, false);
        return inflater.inflate(R.layout.fragment_schedule_appointment_fragment2, container, false);

    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitHelper.getInstance().init(getContext());
        //view.findViewById(R.id.addsection).setOnClickListener(this);
          Hospitalname = new ArrayList<>();
        token= getuserdata().getToken();

        Hospitalid = new ArrayList<>();
        Apdate = new HashMap<>();
        timings = new ArrayList<>();
        staticdata=new ArrayList<>();

        consultancyfee =  view.findViewById(R.id.consultancyfee);
        prescriptionvalidity =  view.findViewById(R.id.prescriptionvalidity);
        recycerschedule =  view.findViewById(R.id.recycerschedule);
//        recycerschedule.setLayoutManager(new LinearLayoutManager(getContext()));
     //   recycerschedule.setHasFixedSize(true);
        ll_time_slot=view.findViewById(R.id.ll_time_slot);
        btn_save=view.findViewById(R.id.btn_save);
        add_day=view.findViewById(R.id.add_day);

//        Monday_Rec = view.findViewById(R.id.monday_recycler_view);
//        Monday_Rec.setLayoutManager(new LinearLayoutManager(getContext()));
//        Monday_Rec.setHasFixedSize(true);

        spinnerHospital = view.findViewById(R.id.spinnerHospital);
        spinnerHospital.setOnItemSelectedListener(this);

        //  view.findViewById(R.id.addsection).setOnClickListener(this);
        if (Utils.haveInternet(context)) {
            mycustomdialog.show();
            serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
        }

        add_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDays customDays=new CustomDays(getContext(),senddata);
                customDaysArrayList.add(customDays);
                ll_time_slot.addView(customDays);
                customDaysArrayList.get(customDaysArrayList.size()-1).img_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ll_time_slot.removeViewAt(customDaysArrayList.size()-1);
                        customDaysArrayList.remove(customDaysArrayList.size()-1);
                    }
                });
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HitApi();
                mycustomdialog.show();
            }
        });

     //   GetData();
    }

    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == HospitalListBydocId.class) {
            hospitalList = (HospitalListBydocId) arg;
            if (hospitalList.getStatus() == SUCCESS_STATUS) {

                setspinner(hospitalList.getData().getHospitalDetails());

            } else {
                Utils.showToastCenter(context, hospitalList.getMessage());
            }

        } else if (arg.getClass() == Apointmentlist.class) {
            Apointmentlist apointmentlist = (Apointmentlist) arg;
            if (apointmentlist.getStatus() == SUCCESS_STATUS) {
                //Utils.showToastCenter(getContext(), apointmentlist.getMessage());
                //addRecycleview(apointmentlist.getData().getWorkingHours());
            } else {
                Utils.showToastCenter(getContext(), apointmentlist.getMessage());

            }
        }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private void addRecycleview(List<WorkingHour> data) {
//        Timing timing;
//        if (data.size() > 0) {
//            staticdata.clear();
//           // timing = new Timing("0", 0, new From("0", 00, 00), new Till(00, 00), false,"0");
//            timings.clear();
//           // timings.add(timing);
//            staticdata.add(0,new WorkingHour(daysname[0],timings));
//            staticdata.add(1,new WorkingHour(daysname[1],timings));
//            staticdata.add(2,new WorkingHour(daysname[2],timings));
//            staticdata.add(3,new WorkingHour(daysname[3],timings));
//            staticdata.add(4,new WorkingHour(daysname[4],timings));
//            staticdata.add(5,new WorkingHour(daysname[5],timings));
//            staticdata.add(6,new WorkingHour(daysname[6],timings));
//            for(int i=0 ;i<=data.size()-1;i++){
//                setdaywisedata(data.get(i).getDay(), data.get(i));
//            }
//
//        } else {
//            staticdata.clear();
//            // timing = new Timing("0", 0, new From("0", 00, 00), new Till(00, 00), false,"0");
//            timings.clear();
//           // timings.add(timing);
//            staticdata.add(0,new WorkingHour(daysname[0],timings));
//            staticdata.add(1,new WorkingHour(daysname[1],timings));
//            staticdata.add(2,new WorkingHour(daysname[2],timings));
//            staticdata.add(3,new WorkingHour(daysname[3],timings));
//            staticdata.add(4,new WorkingHour(daysname[4],timings));
//            staticdata.add(5,new WorkingHour(daysname[5],timings));
//            staticdata.add(6,new WorkingHour(daysname[6],timings));
//        }
//
//
//
//
//
//
//        parentAdopter = new ParentAdopter(getContext(), staticdata, new ParentAdopter.Addsession() {
//            @Override
//            public void onAddlessioner(String s) {
//                showEditDialog(s, null,0);
//
//            }
//
//            @Override
//            public void onclick(String day, List<Timing> data, int position) {
//                showEditDialog(day, data,position);
//
//            }
//        });
//        Monday_Rec.setAdapter(parentAdopter);
//        parentAdopter.notifyDataSetChanged();
//    }

    private void setdaywisedata(String day, WorkingHour data) {

        switch (day){
            case "monday":

                staticdata.set(0,data);
                break;
            case"tuesday":

                staticdata.set(1,data);
                break;
            case "wednesday":

                staticdata.set(2, data);
                //staticdata.set(2,data);
                break;
            case "thursday":

                staticdata.set(3,data);
                break;
            case "friday":

                staticdata.set(4,data);
                break;
            case "saturday":

                staticdata.set(5,data);
                break;
            case "sunday":

                staticdata.set(6,data);
                break;
        }

    }

    private void showEditDialog(String day, List<Timing> data, int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();

        if (hospitalid != "") {
            AddSectionFragment editNameDialogFragment = AddSectionFragment.newInstance(context, hospitalid, day, data, hitapi,position);
            editNameDialogFragment.show(fm, "Add Session");
        }
    }

    private void setspinner(List<HospitalDetail> hospitalDetails) {
        Hospitalname.clear();
        Hospitalid.clear();
        Hospitalid.addAll(hospitalDetails);
        Hospitalname.add(0, "Select Hospital");
        for (int i = 0; i <= hospitalDetails.size() - 1; i++) {
            Hospitalname.add(hospitalDetails.get(i).getName());

        }
        spinnerHospital.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, Hospitalname));

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addsection:
                showEditDialog("Monday", null,0);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerHospital:
                if (parent.getAdapter().getItemId(position) > 0 && Hospitalname.size()>0) {
                    mycustomdialog.show();
                    hospitalid = Hospitalid.get(position - 1).get_id();
                    GetData();


                } else {
                    hospitalid="";
                }

                break;
            default:

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void GetData() {
        // if (Utils.haveInternet(context)) {
        //          mycustomdialog.show();
//            serviceModel.doPostJSonRequest(new getWorkingHours(getuserdata().get_id(), hospitalid),
//                    getuserdata().getToken(), API_APPOINTMENTLIST);

        //   }

        if (Utils.haveInternet(getContext())) {
            getworkingworkihgoursmodel getworkingworkihgoursmodel = new getworkingworkihgoursmodel();
            getworkingworkihgoursmodel.setDoctorDetails(getuserdata().get_id());
            getworkingworkihgoursmodel.setHospitalDetails(hospitalid);
          //  getworkingworkihgoursmodel.setHospitalDetails("62399c1a86a697737f0a3e3e");
            mycustomdialog.show();
            RetrofitHelper.getInstance().CallscheduleAppoinment(this, getworkingworkihgoursmodel);

        }
    }

    @Override
    public void setWorkingHours(Data data) {
        // showworking("update",data,context);
    }

    @Override
    public void onResponse(Call<AppointmentSheduleResponse> call, Response<AppointmentSheduleResponse> response) {
        if (response.isSuccessful()){
            mycustomdialog.hide();
                if (response.body().getStatus().equals(200)){
//                    consultancyfee.setText(response.body().getData().getFee().getHospitalDetails().getConsultationFee().getMax()+"");
//                    prescriptionvalidity.setText(response.body().getData().getPrescriptionValidity().getValidateTill()+"");
//                    appointmentWorkingHours = (ArrayList<AppointmentWorkingHour>)response.body().getData().getWorkingHours();
//                    if (appointmentWorkingHours.size()!=0){
//                        AppointmentScheduleAdapter currentCourseAdapter =new AppointmentScheduleAdapter(getContext(),appointmentWorkingHours, schedule_appointment_fragment.this);
//                        recycerschedule.setAdapter(currentCourseAdapter);
//
//
//                    }
//                    else
//                    {
//                        appointmentWorkingHours=new ArrayList<>();
//                        AppointmentScheduleAdapter currentCourseAdapter =new AppointmentScheduleAdapter(getContext(),appointmentWorkingHours, schedule_appointment_fragment.this);
//                        recycerschedule.setAdapter(currentCourseAdapter);
//
//                    }

                    appointmentWorkingHours=response.body().getData().getWorkingHours();
                    if(appointmentWorkingHours.size()>0){
                        for(int i=0;i<appointmentWorkingHours.size();i++){
                            SetAllDAta(appointmentWorkingHours,i);
                        }
                    }


                }
                else {
                    mycustomdialog.hide();
                    //  Snackbar.make(mRealtive_grades, "No Data Available", Snackbar.LENGTH_SHORT).show();
                }

            } else {
            mycustomdialog.hide();

            }
        }



    @Override
    public void onFailure(Call<AppointmentSheduleResponse> call, Throwable t) {
        mycustomdialog.hide();
    }


    private void SetAllDAta(List<AppointmentWorkingHour>   appointmentWorkingHours,int i) {
        CustomDays customDays=new CustomDays(context,senddata);
        customDays.spin_ft.setText(appointmentWorkingHours.get(0).getFrom().getTime()+":"+appointmentWorkingHours.get(0).getFrom().getDivision());
        customDays.spin_tt.setText(appointmentWorkingHours.get(0).getTill().getTime()+":"+appointmentWorkingHours.get(0).getTill().getDivision());
        customDays.spin_cap.setSelection(appointmentWorkingHours.get(0).getDays().get(0).getCapacity()-1);
        for(int j=0;j<appointmentWorkingHours.get(i).getDays().size();j++){
            if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("monday")){
                customDays.dayname.add("monday");
                customDays.b_mon=true;
                customDays.tv_mon.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_mon.setTextColor(getResources().getColor(R.color.white));
            }   if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("tuesday")){
                customDays.dayname.add("tuesday");
                customDays.b_tue=true;
                customDays.tv_tue.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_tue.setTextColor(getResources().getColor(R.color.white));
            }   if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("wednesday")){
                customDays.dayname.add("wednesday");
                customDays.b_wed=true;
                customDays.tv_wed.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_wed.setTextColor(getResources().getColor(R.color.white));
            }   if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("thursday")){
                customDays.dayname.add("thursday");
                customDays.b_thu=true;
                customDays.tv_thur.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_thur.setTextColor(getResources().getColor(R.color.white));
            }   if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("friday")){
                customDays.dayname.add("friday");
                customDays.b_fri=true;
                customDays.tv_fri.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_fri.setTextColor(getResources().getColor(R.color.white));
            }   if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("saturday")){
                customDays.dayname.add("saturday");
                customDays.b_sat=true;
                customDays.tv_sat.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_sat.setTextColor(getResources().getColor(R.color.white));
            }   if(appointmentWorkingHours.get(i).getDays().get(j).getDay().equals("sunday")){
                customDays.dayname.add("sunday");
                customDays.b_sun=true;
                customDays.tv_sun.setBackgroundResource(R.drawable.buttonclr);
                customDays.tv_sun.setTextColor(getResources().getColor(R.color.white));
            }
        }
        customDays.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_time_slot.removeView(customDays);
                customDaysArrayList.remove(customDays);

                if(Utils.haveInternet(getContext())){
                    mycustomdialog.show();
                    slotdelsendmodel slotdelsendmodel = new slotdelsendmodel();
                   // slotdelsendmodel.setWorkingHour(getuserdata().get_id());
                 //   slotdelsendmodel.setWorkingHour("623a13de86a697737f0a3fa9");

                    List<String> workingHourList1 = new ArrayList<String>();
                    for (int j = 0; j < appointmentWorkingHours.get(i).getDays().size(); j++)
                        workingHourList1.add(appointmentWorkingHours.get(i).getDays().get(j).getId());
                    slotdelsendmodel.setWorkingHour(workingHourList1);
                    RetrofitHelper.getInstance().calldelslot(slotDelResponseCallback,slotdelsendmodel);

                }
            }
        });
        customDaysArrayList.add(customDays);
        ll_time_slot.addView(customDays);
    }


    public void HitApi(){
        if(customDaysArrayList.size()>count){
            if(Utils.haveInternet(getContext())){
                mycustomdialog.hide();
                AppoinmentMod appoinmentMod=new AppoinmentMod();
                appoinmentMod.setDoctorId(getuserdata().get_id());
               appoinmentMod.setHospitalId(hospitalid);
            //    appoinmentMod.setHospitalId("62399c1a86a697737f0a3e3e");
                List<com.example.medius.models.AppoinmentModel.WorkingHour> workingHours=new ArrayList<>();
                for(int i=0;i<customDaysArrayList.get(count).dayname.size();i++){
                    com.example.medius.models.AppoinmentModel.WorkingHour wrkh=new com.example.medius.models.AppoinmentModel.WorkingHour();
                    wrkh.setName(customDaysArrayList.get(count).dayname.get(i));
                    wrkh.setWorking(true);
                    wrkh.setCapacity(Integer.parseInt(customDaysArrayList.get(count).spin_cap.getSelectedItem().toString()));

                    String fromtime=customDaysArrayList.get(count).spin_ft.getText().toString();
                    String tilltime=customDaysArrayList.get(count).spin_tt.getText().toString();
                    String fromsplit[]=fromtime.split(":");
                    String tillsplit[]=tilltime.split(":");
                    com.example.medius.models.AppoinmentModel.From from=new com.example.medius.models.AppoinmentModel.From();
                    from.setTime(Integer.parseInt(fromsplit[0]));
                    from.setDivision(Integer.parseInt(fromsplit[1]));

                    com.example.medius.models.AppoinmentModel.Till till=new Till();
                    till.setTime(Integer.parseInt(tillsplit[0]));
                    till.setDivision(Integer.parseInt(tillsplit[1]));


                    wrkh.setFrom(from);
                    wrkh.setTill(till);
                    workingHours.add(wrkh);
                }

                appoinmentMod.setWorkingHour(workingHours);
                serviceModel.doPostJSonRequest(appoinmentMod,token,"setSchedule");
             //   GetData();
            }
        }
        else if(customDaysArrayList.size()==count){

        }
    }

    Callback<SlotDelResponse> slotDelResponseCallback = new Callback<SlotDelResponse>() {
        @Override
        public void onResponse(Call<SlotDelResponse> call, Response<SlotDelResponse> response) {
            if (response.isSuccessful()){
                mycustomdialog.hide();
                if (response.body().getStatus().equals(200)){

                }else {
                    mycustomdialog.hide();
                }
            }

        }

        @Override
        public void onFailure(Call<SlotDelResponse> call, Throwable t) {
            mycustomdialog.hide();
        }
    };

    @Override
    public void AddData(String from_time, String tii_time, String capcity, String dayname) {

    }

    @Override
    public void DeleteData(String ids) {

    }
}




//package com.example.newdoctorsapp.fragments;
//
//
//        import static com.example.newdoctorsapp.utility.Constants.API_APPOINTMENTLIST;
//        import static com.example.newdoctorsapp.utility.Constants.API_GTHOSPITALLISTBYDOCTORID;
//        import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;
//
//        import android.annotation.SuppressLint;
//        import android.app.Activity;
//        import android.os.Bundle;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.fragment.app.Fragment;
//        import androidx.fragment.app.FragmentManager;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.AdapterView;
//        import android.widget.ArrayAdapter;
//        import android.widget.EditText;
//        import android.widget.Spinner;
//        import android.widget.TextView;
//
//        import com.example.newdoctorsapp.R;
//        import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
//        import com.example.newdoctorsapp.adapter.AppointmentScheduleAdapter;
//        import com.example.newdoctorsapp.adapter.ParentAdopter;
//        import com.example.newdoctorsapp.adapter.ScheduleDayListAdopter;
//        import com.example.newdoctorsapp.models.AddApointMent.Data;
//        import com.example.newdoctorsapp.models.Apointmentlist.Apointmentlist;
//
//
//        import com.example.newdoctorsapp.models.Apointmentlist.From;
//        import com.example.newdoctorsapp.models.Apointmentlist.Till;
//        import com.example.newdoctorsapp.models.Apointmentlist.Timing;
//        import com.example.newdoctorsapp.models.Apointmentlist.WorkingHour;
//
//        import com.example.newdoctorsapp.models.Apointmentlist.getWorkingHours;
//        import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
//        import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId;
//        import com.example.newdoctorsapp.utility.Utils;
//        import com.example.newdoctorsapp.workspace.RetrofitHelper;
//        import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentSheduleResponse;
//        import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentWorkingHour;
//        import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppoitmentData;
//        import com.example.newdoctorsapp.workspace.getworkingworkihgoursmodel;
//
//        import java.util.ArrayList;
//        import java.util.HashMap;
//        import java.util.List;
//        import java.util.Observable;
//
//        import retrofit2.Call;
//        import retrofit2.Callback;
//        import retrofit2.Response;
//
//
//public class schedule_appointment_fragment extends BaseFragmentJava implements AddSectionFragment.HitApi,
//        View.OnClickListener, AdapterView.OnItemSelectedListener, Callback<AppointmentSheduleResponse> {
//    public static final String TAG=schedule_appointment_fragment.class.getName();
//    private RecyclerView Sunday_Recycleview, Monday_Rec,
//            Tuseday_Recy, Wednesday_Recy, Thrisday_Recy, Friday_recyc, Suterday_Recy,recycerschedule;
//
//    private Activity context;
//    private Spinner spinnerHospital;
//    private List<String> Hospitalname;
//    private HospitalListBydocId hospitalList;
//    private List<HospitalDetail> Hospitalid;
//    HospitalDetail hospitalDetail;
//    HashMap<String, String> Apdate;
//    private String Hospitralis = null;
//    private ParentAdopter parentAdopter;
//    private ScheduleDayListAdopter scheduleDayListAdopter;
//    private String hospitalid = "";
//    AddSectionFragment.HitApi hitapi;
//    TextView Addsession;
//    private List<Timing> timings;
//    List<WorkingHour> staticdata;
//    EditText consultancyfee,prescriptionvalidity;
//    String daysname[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//    ArrayList<AppointmentWorkingHour> appointmentWorkingHours = new ArrayList<>();
//    public schedule_appointment_fragment(Activity context) {
//        this.context = context;
//        // Required empty public constructor
//    }
//
//    public static Fragment newInstance(Activity mainActivity) {
//        return new schedule_appointment_fragment(mainActivity);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public Observable getModel() {
//        return serviceModel;
//    }
//
//
//    @Override
//    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        hitapi = this;
//        //return inflater.inflate(R.layout.fragment_schedule_appointment_fragment, container, false);
//        return inflater.inflate(R.layout.fragment_schedule_appointment_fragment2, container, false);
//
//    }
//
//
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        RetrofitHelper.getInstance().init(getContext());
//        view.findViewById(R.id.addsection).setOnClickListener(this);
//        initToolbar(view);
//        toolbar.setTitle("Appointment Schedule");
//        Hospitalname = new ArrayList<>();
//
//
//        Hospitalid = new ArrayList<>();
//        Apdate = new HashMap<>();
//        timings = new ArrayList<>();
//        staticdata=new ArrayList<>();
//
//        consultancyfee =  view.findViewById(R.id.consultancyfee);
//        prescriptionvalidity =  view.findViewById(R.id.prescriptionvalidity);
//        recycerschedule =  view.findViewById(R.id.recycerschedule);
//        recycerschedule.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycerschedule.setHasFixedSize(true);
//
//        Monday_Rec = view.findViewById(R.id.monday_recycler_view);
//        Monday_Rec.setLayoutManager(new LinearLayoutManager(getContext()));
//        Monday_Rec.setHasFixedSize(true);
//
//        spinnerHospital = view.findViewById(R.id.spinnerHospital);
//        spinnerHospital.setOnItemSelectedListener(this);
//
//        //  view.findViewById(R.id.addsection).setOnClickListener(this);
//        if (Utils.haveInternet(context)) {
//            mycustomdialog.show();
//            serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
//        }
//
//
//    }
//
//    public void update(Observable o, Object arg) {
//        mycustomdialog.dismiss();
//        if (arg.getClass() == HospitalListBydocId.class) {
//            hospitalList = (HospitalListBydocId) arg;
//            if (hospitalList.getStatus() == SUCCESS_STATUS) {
//
//                setspinner(hospitalList.getData().getHospitalDetails());
//
//            } else {
//                Utils.showToastCenter(context, hospitalList.getMessage());
//            }
//
//        } else if (arg.getClass() == Apointmentlist.class) {
//            Apointmentlist apointmentlist = (Apointmentlist) arg;
//            if (apointmentlist.getStatus() == SUCCESS_STATUS) {
//                //Utils.showToastCenter(getContext(), apointmentlist.getMessage());
//                addRecycleview(apointmentlist.getData().getWorkingHours());
//            } else {
//                Utils.showToastCenter(getContext(), apointmentlist.getMessage());
//
//            }
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private void addRecycleview(List<WorkingHour> data) {
//        Timing timing;
//        if (data.size() > 0) {
//            staticdata.clear();
//            // timing = new Timing("0", 0, new From("0", 00, 00), new Till(00, 00), false,"0");
//            timings.clear();
//            // timings.add(timing);
//            staticdata.add(0,new WorkingHour(daysname[0],timings));
//            staticdata.add(1,new WorkingHour(daysname[1],timings));
//            staticdata.add(2,new WorkingHour(daysname[2],timings));
//            staticdata.add(3,new WorkingHour(daysname[3],timings));
//            staticdata.add(4,new WorkingHour(daysname[4],timings));
//            staticdata.add(5,new WorkingHour(daysname[5],timings));
//            staticdata.add(6,new WorkingHour(daysname[6],timings));
//            for(int i=0 ;i<=data.size()-1;i++){
//                setdaywisedata(data.get(i).getDay(), data.get(i));
//            }
//
//        } else {
//            staticdata.clear();
//            // timing = new Timing("0", 0, new From("0", 00, 00), new Till(00, 00), false,"0");
//            timings.clear();
//            // timings.add(timing);
//            staticdata.add(0,new WorkingHour(daysname[0],timings));
//            staticdata.add(1,new WorkingHour(daysname[1],timings));
//            staticdata.add(2,new WorkingHour(daysname[2],timings));
//            staticdata.add(3,new WorkingHour(daysname[3],timings));
//            staticdata.add(4,new WorkingHour(daysname[4],timings));
//            staticdata.add(5,new WorkingHour(daysname[5],timings));
//            staticdata.add(6,new WorkingHour(daysname[6],timings));
//        }
//
//
//
//
//
//
//        parentAdopter = new ParentAdopter(getContext(), staticdata, new ParentAdopter.Addsession() {
//            @Override
//            public void onAddlessioner(String s) {
//                showEditDialog(s, null,0);
//
//            }
//
//            @Override
//            public void onclick(String day, List<Timing> data, int position) {
//                showEditDialog(day, data,position);
//
//            }
//        });
//        Monday_Rec.setAdapter(parentAdopter);
//        parentAdopter.notifyDataSetChanged();
//    }
//
//    private void setdaywisedata(String day, WorkingHour data) {
//
//        switch (day){
//            case "monday":
//
//                staticdata.set(0,data);
//                break;
//            case"tuesday":
//
//                staticdata.set(1,data);
//                break;
//            case "wednesday":
//
//                staticdata.set(2, data);
//                //staticdata.set(2,data);
//                break;
//            case "thursday":
//
//                staticdata.set(3,data);
//                break;
//            case "friday":
//
//                staticdata.set(4,data);
//                break;
//            case "saturday":
//
//                staticdata.set(5,data);
//                break;
//            case "sunday":
//
//                staticdata.set(6,data);
//                break;
//        }
//
//    }
//
//    private void showEditDialog(String day, List<Timing> data, int position) {
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//
//        if (hospitalid != "") {
//            AddSectionFragment editNameDialogFragment = AddSectionFragment.newInstance(context, hospitalid, day, data, hitapi,position);
//            editNameDialogFragment.show(fm, "Add Session");
//        }
//    }
//
//    private void setspinner(List<HospitalDetail> hospitalDetails) {
//        Hospitalname.clear();
//        Hospitalid.clear();
//        Hospitalid.addAll(hospitalDetails);
//        Hospitalname.add(0, "Select Hospital");
//        for (int i = 0; i <= hospitalDetails.size() - 1; i++) {
//            Hospitalname.add(hospitalDetails.get(i).getName());
//
//        }
//        spinnerHospital.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, Hospitalname));
//
//    }
//
//    @Override
//    public boolean onBackPressed() {
//        return false;
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.addsection:
//                showEditDialog("Monday", null,0);
//                break;
//        }
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        switch (parent.getId()) {
//            case R.id.spinnerHospital:
//                if (parent.getAdapter().getItemId(position) > 0 && Hospitalname.size()>0) {
//                    mycustomdialog.show();
//                    hospitalid = Hospitalid.get(position - 1).get_id();
//                    GetData();
//
//
//                } else {
//                    hospitalid="";
//                }
//
//                break;
//            default:
//
//        }
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//
//    @Override
//    public void GetData() {
//        // if (Utils.haveInternet(context)) {
//        //          mycustomdialog.show();
////            serviceModel.doPostJSonRequest(new getWorkingHours(getuserdata().get_id(), hospitalid),
////                    getuserdata().getToken(), API_APPOINTMENTLIST);
//
//        //   }
//
////        if (Utils.haveInternet(getContext())) {
//        getworkingworkihgoursmodel getworkingworkihgoursmodel = new getworkingworkihgoursmodel();
//        getworkingworkihgoursmodel.setDoctorDetails(getuserdata().get_id());
//        getworkingworkihgoursmodel.setHospitalDetails(hospitalid);
//        mycustomdialog.show();
//        RetrofitHelper.getInstance().CallscheduleAppoinment(this,getworkingworkihgoursmodel);
//
//    }
//
//    @Override
//    public void setWorkingHours(Data data) {
//        // showworking("update",data,context);
//    }
//
//    @Override
//    public void onResponse(Call<AppointmentSheduleResponse> call, Response<AppointmentSheduleResponse> response) {
//        if (response.isSuccessful()){
//            mycustomdialog.hide();
//            if (response.body().getStatus().equals(200)){
//                consultancyfee.setText(response.body().getData().getFee().getHospitalDetails().getConsultationFee().getMax()+"");
//                prescriptionvalidity.setText(response.body().getData().getPrescriptionValidity().getValidateTill()+"");
//                appointmentWorkingHours = (ArrayList<AppointmentWorkingHour>)response.body().getData().getWorkingHours();
//                if (appointmentWorkingHours.size()!=0){
//                    AppointmentScheduleAdapter currentCourseAdapter =new AppointmentScheduleAdapter(getContext(),appointmentWorkingHours, schedule_appointment_fragment.this);
//                    recycerschedule.setAdapter(currentCourseAdapter);
//
//
//                }
//                else
//                {
//                    appointmentWorkingHours=new ArrayList<>();
//                    AppointmentScheduleAdapter currentCourseAdapter =new AppointmentScheduleAdapter(getContext(),appointmentWorkingHours, schedule_appointment_fragment.this);
//                    recycerschedule.setAdapter(currentCourseAdapter);
//
//                }
//
//
//            }
//            else {
//                mycustomdialog.hide();
//                //  Snackbar.make(mRealtive_grades, "No Data Available", Snackbar.LENGTH_SHORT).show();
//            }
//
//        } else {
//            mycustomdialog.hide();
//
//        }
//    }
//
//
//
//    @Override
//    public void onFailure(Call<AppointmentSheduleResponse> call, Throwable t) {
//        mycustomdialog.hide();
//    }
//}