package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;
import static com.example.newdoctorsapp.utility.Constants.API_GTHOSPITALLISTBYDOCTORID;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demod.RXCalling.ServiceInterface;
import com.example.demod.RXCalling.ServiceModel;

import com.example.medius.models.approvedoc_req.Appr_from_doc_mod;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.models.DoctorApointmentList.DoctorAppointmentList;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.GetHoliDayDataModel;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayCalendarDatamodel;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayCalendarResponse;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayGetResponse;
import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId;
import com.example.newdoctorsapp.models.Submitfeedback.sumitfeedbackmodel;
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.android.material.snackbar.Snackbar;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoliDayCalender extends BaseActivityJava implements
        OnNavigationButtonClickedListener, AdapterView.OnItemSelectedListener, Callback<HolidayCalendarResponse> {
    CustomCalendar customCalendar;
    ServiceModel serviceModel=new ServiceModel();
    Dialog mydialog;
    String token="",maintime;
    Spinner spin_doc;
    ArrayAdapter<String> adapter;
    String doc_id="";
    HashMap<Integer,Object> dateHashmap;
    Calendar calendar;
    private Spinner spinnerState;
    private static String hospitalid = "";
    private static List<HospitalDetail> hospitalDetails = new ArrayList<>();
  
    Dialog dialog;
    private static List<String> Hospitalname =  new ArrayList<>();
    int year=2022,month=0;
    @Override
    public Observable getModel() {
        return serviceModel;
    }
//http://3.21.52.154:3000/doctor/getDoctorsHolidayList get {
//    "doctorId":"625c42ae81925a7d083ad96b"
//}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holi_day_calender);
        RetrofitHelper.getInstance().init(this);
        initToolbar(this);
        toolbar.setTitle("Holiday calendar");
        customCalendar=findViewById(R.id.custom_calendar);
        token= MediusApp.getPreferences(Constants.TOKEN_ID,"");
        spinnerState = findViewById(R.id.spinnerState);
        spinnerState.setOnItemSelectedListener(this);;
        mydialog=new MyProgressDialog().progressDialog(this);
        // Initialize description hashmap
        HashMap<Object, Property> descHashMap=new HashMap<>();

        // Initialize default property
        Property defaultProperty=new Property();

        // Initialize default resource
        defaultProperty.layoutResource=R.layout.d;

        // Initialize and assign variable
        defaultProperty.dateTextViewResource=R.id.text_view;

        // Put object and property
        descHashMap.put("default",defaultProperty);

        // for current date
        Property currentProperty=new Property();
        currentProperty.layoutResource=R.layout.c;
        currentProperty.dateTextViewResource=R.id.text_view;
        descHashMap.put("current",currentProperty);

        // for present date
        Property presentProperty=new Property();
        presentProperty.layoutResource=R.layout.p;
        presentProperty.dateTextViewResource=R.id.text_view;
        descHashMap.put("present",presentProperty);

        
        // set desc hashmap on custom calendar
        customCalendar.setMapDescToProp(descHashMap);

        // Initialize date hashmap
         dateHashmap=new HashMap<>();

        // initialize calendar
          calendar=  Calendar.getInstance();
        month= calendar.get(Calendar.MONTH);
         year= calendar.get(Calendar.YEAR);
        // Put values
//        dateHashmap.put(calendar.get(Calendar.DAY_OF_MONTH),"current");
//       dateHashmap.put(1,"present");
//        dateHashmap.put(2,"absent");
//        dateHashmap.put(3,"present");
//        dateHashmap.put(4,"absent");
//        dateHashmap.put(20,"present");
//        dateHashmap.put(30,"absent");

        //set date
        customCalendar.setDate(calendar,dateHashmap);

        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                // get string date
                String sDate=selectedDate.get(Calendar.YEAR)+
                        "-" +(selectedDate.get(Calendar.MONTH)+1)
                        +"-" + selectedDate.get(Calendar.DAY_OF_MONTH);

                Log.d("sdsdds","***"+sDate);


        //        String dtStart = "2010-10-15T09:27:37Z";

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date = format.parse(sDate);

                    String[] parts = date.toString().split(" ");
                    String first = parts[0];
                    String second = parts[1];
                    String third = parts[2];

                    maintime = first+" "+second+" "+third+" "+selectedDate.get(Calendar.YEAR)+" "+"22:47:17 GMT+0530 (India Standard Time)";

                    Log.d("sdfssd","****"+maintime);
                    if(spinnerState.getSelectedItemPosition()==0){
                        Toast.makeText(HoliDayCalender.this, "Please select Hospital First", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    addholiday();
              //      "Tue May 30 2022 22:47:17 GMT+0530 (India Standard Time)"
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS, this);
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this);
//        if (Utils.haveInternet(this)) {
//            mydialog.show();
//            //"getListOfRequestedApprovals_ByHospital"
//            String url = ServiceInterface.Companion.getBASE_URL() + "hospital/getDoctorsInHospital";
//            serviceModel.doGetCheckStatusRequest("getDoctorsInHospital", url, token);
//        }

      /*  if(Utils.haveInternet(this)){
            mydialog.show();
            Appr_from_doc_mod appr_from_doc_mod = new Appr_from_doc_mod();
            appr_from_doc_mod.setDoctorId(MediusApp.getPreferences(Constants.Hospital_ID, ""));

            serviceModel.doPostJSonRequest(appr_from_doc_mod,token,"getDoctorsHolidayList");
        }*/

        Calendar instance = Calendar.getInstance();
        int  month2 = instance.get(Calendar.MONTH);
     //   month=month2+1;
        year = instance.get(Calendar.YEAR);

        if (Utils.haveInternet(this)) {
            mycustomdialog.show();
            serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
        }

    }

    public  void addholiday(){

        dialog  = new Dialog(HoliDayCalender.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_holiday);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.mTxtViewYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calladdholiday();
                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.mtxtViewLater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }
    public void calladdholiday(){
        HolidayCalendarDatamodel holidayCalendarDatamodel = new HolidayCalendarDatamodel();
        holidayCalendarDatamodel.setDate(maintime);
        holidayCalendarDatamodel.setDoctorId(getuserdata().get_id());
        holidayCalendarDatamodel.setHospitalId(hospitalid);
        if (Utils.haveInternet(getContext())) {
            mycustomdialog.show();
            RetrofitHelper.getInstance().calladdholiday(this,holidayCalendarDatamodel);
        }
    }




    @Override
    public Map<Integer, Object>[] onNavigationButtonClicked(int whichButton, Calendar newMonth) {
        Map<Integer, Object>[] arr = new Map[2];
        if(Utils.haveInternet(HoliDayCalender.this)){
            mydialog.show();
            month = newMonth.get(Calendar.MONTH);
            year = newMonth.get(Calendar.YEAR);
            GetHoliDayDataModel getHoliDayDataModel = new GetHoliDayDataModel();
            getHoliDayDataModel.setDoctorId(getuserdata().get_id());
            getHoliDayDataModel.setHospitalId(hospitalid);
            getHoliDayDataModel.setMonth(newMonth.get(Calendar.MONTH)+1);
            getHoliDayDataModel.setYear(newMonth.get(Calendar.YEAR));

            RetrofitHelper.getInstance().callGetHoliDay(holidayGetResponseCallback,getHoliDayDataModel);

        }
        return arr;
    }

    List<String> names=new ArrayList<>();
    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();

        if (arg.getClass() == HospitalListBydocId.class) {
            HospitalListBydocId hospitalList = (HospitalListBydocId) arg;
            if (hospitalList.getStatus() == SUCCESS_STATUS) {
                setspinner(hospitalList.getData().getHospitalDetails());

            } else {
                Utils.showToastCenter(this, hospitalList.getMessage());
            }

        } else if (arg.getClass() == DoctorAppointmentList.class) {
            DoctorAppointmentList doctorAppointmentList = (DoctorAppointmentList) arg;
            if (doctorAppointmentList.getStatus() == SUCCESS_STATUS) {

                if (hospitalid.equals("")) {
                   // mycustomdialog.show();
                    serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());
                }
                //setRecycleview(doctorAppointmentList.getData());

            } else {
                Utils.showToastCenter(this, doctorAppointmentList.getMessage());

            }
        }


    }


    private void setspinner(List<HospitalDetail> hospitalList) {
        Hospitalname.clear();
        Hospitalname.add(0, "Select Hospital");
        for (int i = 0; i <= hospitalList.size() - 1; i++) {
            Hospitalname.add(hospitalList.get(i).getName());
            hospitalDetails.add(hospitalList.get(i));
        }
        spinnerState.setAdapter(new ArrayAdapter<>(this, R.layout.dropdownitem, Hospitalname));


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getAdapter().getItemId(i) > 0) {
            hospitalid = hospitalDetails.get(i - 1).get_id();
            if (Utils.haveInternet(HoliDayCalender.this)) {
                mydialog.show();
                GetHoliDayDataModel getHoliDayDataModel = new GetHoliDayDataModel();
                getHoliDayDataModel.setDoctorId(getuserdata().get_id());
                getHoliDayDataModel.setHospitalId(hospitalid);
                getHoliDayDataModel.setMonth(month+1);
                getHoliDayDataModel.setYear(year);
                RetrofitHelper.getInstance().callGetHoliDay(holidayGetResponseCallback, getHoliDayDataModel);
            }
        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // set holiday response
    @Override
    public void onResponse(Call<HolidayCalendarResponse> call, Response<HolidayCalendarResponse> response) {
        if (response.isSuccessful()){
            if (response.body().getStatus().equals(200)){
                mycustomdialog.hide();
                if (Utils.haveInternet(HoliDayCalender.this)) {
                    mydialog.show();
                    GetHoliDayDataModel getHoliDayDataModel = new GetHoliDayDataModel();
                    getHoliDayDataModel.setDoctorId(getuserdata().get_id());
                    getHoliDayDataModel.setHospitalId(hospitalid);
                    getHoliDayDataModel.setMonth(month+1);
                    getHoliDayDataModel.setYear(year);
                    RetrofitHelper.getInstance().callGetHoliDay(holidayGetResponseCallback, getHoliDayDataModel);


                }
            }
        }

    }

    @Override
    public void onFailure(Call<HolidayCalendarResponse> call, Throwable t) {
        mycustomdialog.hide();
    }

    Callback<HolidayGetResponse> holidayGetResponseCallback = new Callback<HolidayGetResponse>() {
        @Override
        public void onResponse(Call<HolidayGetResponse> call, Response<HolidayGetResponse> response) {
            if (response.isSuccessful()){
                dateHashmap=new HashMap<>();
                mydialog.hide();
                if (response.body().getStatus().equals(200)){
                    if(!response.body().getData().isEmpty()){
                        for(int i=0;i<response.body().getData().size();i++){
                            Log.d("ashish",response.body().getData().get(i).getDate().substring(8,10));
                            int leavedate=Integer.parseInt(response.body().getData().get(i).getDate().substring(8,10));
                            dateHashmap.put(leavedate,"present");
                        }
                        calendar.set(year, month+1,0);
                        customCalendar.setDate(calendar,dateHashmap);
                       // customCalendar.setDate(calendar,dateHashmap);
                    }else {
                        Log.d("ashish","sddssdsd");
                        calendar=  Calendar.getInstance();
                        calendar.set(year, month+1,0);
                        customCalendar.setDate(calendar,dateHashmap);
                    }
                }
            }

        }

        @Override
        public void onFailure(Call<HolidayGetResponse> call, Throwable t) {
            mycustomdialog.hide();
        }
    };




//        if(arg.getClass()==HolidaysListResp.class){
//            HolidaysListResp resp= (HolidaysListResp) arg;
//            if(resp.getStatus()==200){
//                if(resp.getData().size()>0){
//                    for(int i=0;i<resp.getData().size();i++){
//                        Log.d("ashish",resp.getData().get(i).getDate().substring(8,10));
//                        int leavedate=Integer.parseInt(resp.getData().get(i).getDate().substring(8,10));
//                        dateHashmap.put(leavedate,"present");
//
//                    }
//                    customCalendar.setDate(calendar,dateHashmap);
//                }
//
//
//                // set date
//
//            }
//        }



//       if(arg!=null){
//           if (arg.getClass() == Doc_List.class) {
//               Doc_List resp= (Doc_List) arg;
//               names=new ArrayList<>();
//               if(resp.getStatus()==200){
//                   doctorList=resp.getData().getDoctors();
//                   for(int i=0;i<resp.getData().getDoctors().size();i++){
//                       names.add(resp.getData().getDoctors().get(i).getFirstName()+" "+resp.getData().getDoctors().get(i).getLastName());
//                   }
//                   adapter =new  ArrayAdapter(
//                           this,
//                           R.layout.custom_spinner,
//                           R.id.tv_name,
//                           names
//                   );
//                   spin_doc.setAdapter(adapter);
//                   spin_doc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                       @Override
//                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                           doc_id=resp.getData().getDoctors().get(position).get_id();
//                           if (Utils.haveInternet(HoliDayCalender.this)) {
//                               mydialog.show();
//                               //"getListOfRequestedApprovals_ByHospital"
//                               if(Utils.haveInternet(HoliDayCalender.this)){
//                                   mydialog.show();
//                                   Appr_from_doc_mod appr_from_doc_mod = new Appr_from_doc_mod();
//                                   appr_from_doc_mod.setDoctorId(doc_id);
//                                   String url =ServiceInterface.Companion.getBASE_URL()+"doctor/getDoctorsHolidayList";
//                                    serviceModel.doGetCheckStatusRequest("getDoctorsHolidayList",url,token,appr_from_doc_mod);
//                                  // serviceModel.doPostJSonRequest(doc_id,token,"getDoctorsHolidayList");
//                               }
//                           }
//                       }
//
//                       @Override
//                       public void onNothingSelected(AdapterView<?> parent) {
//
//                       }
//                   });
//               }
//
//
//           }
//           else if(arg.getClass()==HolidaysListResp.class){
//               HolidaysListResp resp= (HolidaysListResp) arg;
//               if(resp.getStatus()==200){
//                   if(resp.getData().size()>0){
//                       for(int i=0;i<resp.getData().size();i++){
//                           Log.d("ashish",resp.getData().get(i).getDate().substring(8,10));
//                           int leavedate=Integer.parseInt(resp.getData().get(i).getDate().substring(8,10));
//                           dateHashmap.put(leavedate,"present");
//
//                       }
//                       customCalendar.setDate(calendar,dateHashmap);
//                   }
///*
//                   dateHashmap.put(1,"present");
//                   dateHashmap.put(2,"absent");
//                   dateHashmap.put(3,"present");
//                   dateHashmap.put(4,"absent");
//                   dateHashmap.put(20,"present");
//                   dateHashmap.put(30,"absent");*/
//
//                   // set date
//
//               }
//           }
//
//       }
//       else{
//           Utils.ShowNotFound(this,"Api Error , Data not found");
//       }


}
