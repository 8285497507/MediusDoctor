
package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.utility.Constants.API_APPOINTMENTLIST;
import static com.example.newdoctorsapp.utility.Constants.API_GTHOSPITALLISTBYDOCTORID;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.demod.RXCalling.ServiceInterface;
import com.example.demod.RXCalling.ServiceModel;
import com.example.medius.models.AppoinmentModel.AppoinmentMod;
import com.example.medius.models.AppoinmentModel.AppointResp.AppoinmentResp;
import com.example.medius.models.AppoinmentModel.From;
import com.example.medius.models.AppoinmentModel.Till;
import com.example.medius.models.AppoinmentModel.WorkingHour;
import com.example.medius.models.Consult_Fee.Consult_Fee_Mod;
import com.example.medius.models.Consult_Fee.ConsultationFee;
import com.example.medius.models.Prescriptionvalidity.Prescriptionvarid_Mod;
import com.example.medius.models.deletehour.DeleteResp;
import com.example.medius.models.deletehour.DeleteWorkingHourMod;
import com.example.medius.models.working_hour.WorkingHourMod;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.custom_font.CustomDays;
import com.example.newdoctorsapp.models.CommonModel;
import com.example.newdoctorsapp.models.CommonResp;
import com.example.newdoctorsapp.models.HospitalList.HospitalDetail;
import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId;
import com.example.newdoctorsapp.models.feevalidity.GetFeeValidity;
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AppoinmentScedule extends BaseFragmentJava implements CustomDays.SendData {
    ArrayList<CustomDays> customDaysArrayList = new ArrayList<>();
    LinearLayout ll_time_slot;
    TextView add_day;
    AppCompatButton btn_save;
    AppCompatSpinner spin_doctor;
    ArrayAdapter<String> adapter;
    ServiceModel serviceModel = new ServiceModel();
    Dialog mydialog;
    String token;
    int count = 0;
    EditText edt_consult_fee;
    Spinner spin_prescription;
    ArrayAdapter<String> array_validaday;
    CustomDays.SendData customdays;
    View view;
    AppCompatTextView tv_title;
    ImageButton btn_back;
    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_appoinment_scedule, container, false);
        customdays = this;
        INit(view);

        add_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDays customDays = new CustomDays(getActivity(),customdays);
                customDaysArrayList.add(customDays);
                ll_time_slot.addView(customDays);

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultFee();
                //HitApi();

            }
        });
//getFeeAndValidity
        if (Utils.haveInternet(getActivity())) {
            mydialog.show();

            serviceModel.doGetCheckStatusRequest(API_GTHOSPITALLISTBYDOCTORID, getuserdata().get_id(), getuserdata().getToken());

//            String url = ServiceInterface.Companion.getBASE_URL() + "hospital/getDoctorsInHospital";
//            serviceModel.doGetCheckStatusRequest("getDoctorsInHospital", url, token);

        }

        return view;
    }

    private void INit(View view) {
        spin_doctor = view.findViewById(R.id.spin_doctor);
        mydialog = new MyProgressDialog().progressDialog(getActivity());
        ll_time_slot = view.findViewById(R.id.ll_time_slot);
        btn_save = view.findViewById(R.id.btn_save);
        add_day = view.findViewById(R.id.add_day);
        spin_prescription =view .findViewById(R.id.spin_prescription);
        edt_consult_fee = view.findViewById(R.id.edt_consult_fee);
        tv_title = view.findViewById(R.id.tv_title);
        btn_back = view.findViewById(R.id.btn_back);
        tv_title.setText("Appointment Schedule");
        token =getuserdata().getToken();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        ArrayList<String> daysname = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            daysname.add("day " + i);
        }
        array_validaday = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, daysname);
        spin_prescription.setAdapter(array_validaday);
        spin_prescription.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    List<HospitalDetail> hospitalDetails=new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    String hos_id = "";
    List<com.example.newdoctorsapp.models.working_hour.WorkingHour> workingHourList = new ArrayList<>();


    private void setspinner(List<HospitalDetail> hospitalDetails2) {
       hospitalDetails=hospitalDetails2;
        name.add(0, "Select Hospital");
        for (int i = 0; i <= hospitalDetails.size() - 1; i++) {
            name.add(hospitalDetails.get(i).getName());

        }
        spin_doctor.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.dropdownitem, name));
        spin_doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){}
                else{
                    hos_id=hospitalDetails.get(position-1).get_id();
                    customDaysArrayList.clear();
                    ll_time_slot.removeAllViews();
                    if (Utils.haveInternet(getActivity())) {
                        mydialog.show();
                        CommonModel commonModel = new CommonModel();
                        commonModel.setDoctorId(getuserdata().get_id());
                        commonModel.setHospitalId(hos_id);
                        serviceModel.doPostJSonRequest(commonModel, token, "getFeeAndValidity");

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        mydialog.dismiss();
        if (arg != null) {
            if (arg.getClass() == HospitalListBydocId.class) {
                HospitalListBydocId resp = (HospitalListBydocId) arg;
                if (resp.getStatus() == 200) {
                    hospitalDetails = resp.getData().getHospitalDetails();
                    if (hospitalDetails.size() > 0) {
                        setspinner(hospitalDetails);
                    } else {
                        customDaysArrayList.clear();
                        ll_time_slot.removeAllViews();
                    }
                }
            } else if (arg.getClass() == GetFeeValidity.class) {
                GetFeeValidity resp = (GetFeeValidity) arg;
                if (resp.getStatus() == 200) {
                    try {
                        edt_consult_fee.setText(resp.getData().getConsultationFee().getMax() + "");
                    } catch (Exception e) {
                    }
                    try {
                        spin_prescription.setSelection(resp.getData().getPrescription().get(0).getValidateTill() - 1);
                    } catch (Exception e) {
                    }

                    WorkingHourMod workingHourMod = new WorkingHourMod();
                    workingHourMod.setDoctorDetails(getuserdata().get_id());
                    workingHourMod.setHospitalDetails(hos_id);
                    serviceModel.doPostJSonRequest(workingHourMod, getuserdata().getToken(), API_APPOINTMENTLIST);
                }
            } else if (arg.getClass() == AppoinmentResp.class) {
                AppoinmentResp resp = (AppoinmentResp) arg;
                if (resp.getStatus() == 200) {

                } else {
                    Utils.ShowNotFound(getActivity(), resp.getMessage());
                }
            } else if (arg.getClass() == com.example.newdoctorsapp.models.working_hour.Working_Hour_Resp.class) {
                com.example.newdoctorsapp.models.working_hour.  Working_Hour_Resp resp = (com.example.newdoctorsapp.models.working_hour.Working_Hour_Resp) arg;
                if (resp.getStatus() == 200) {
                    workingHourList = resp.getData().getWorkingHours();
                    if (workingHourList.size() > 0) {
                        for (int i = 0; i < workingHourList.size(); i++) {
                            SetAllDAta(workingHourList, i);
                         }
                    }
                } else {
                    Utils.ShowNotFound(getActivity(), resp.getMessage());
                }
            } else if (arg.getClass() == CommonResp.class) {
                CommonResp resp = (CommonResp) arg;
                if (resp.getStatus() == 200) {
                    if (isagain)
                        PrescriptionVAlid();
                    else {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("")
                                .setContentText(resp.getMessage()).setConfirmText("OK")
                                .setConfirmClickListener(sweetAlertDialog -> {
                                    sweetAlertDialog.dismiss();
                                    getActivity().onBackPressed();
                                }).show();
                    }
                }
            } else if (arg.getClass() == DeleteResp.class) {
                DeleteResp resp = (DeleteResp) arg;
                if (resp.getStatus() == 200 && isfinish) {
                    //isfinish menas if we delete getActivity() by delete icon isFinish will be true , ifwe delete one by one false
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Delete")
                            .setContentText(resp.getMessage()).setConfirmText("OK")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                sweetAlertDialog.dismiss();
                                getActivity().finish();
                            }).show();
                } else {

                }
            }
        } else {
            Utils.ShowNotFound(getActivity(), "Data not found by Api");
        }
    }

    private void SetAllDAta(List<com.example.newdoctorsapp.models.working_hour.WorkingHour> workingHourList, int i) {
        CustomDays customDays = new CustomDays(getActivity(),customdays);
        customDays.spin_ft.setText(workingHourList.get(i).getFrom().getTime() + ":" + workingHourList.get(i).getFrom().getDivision());
        customDays.spin_tt.setText(workingHourList.get(i).getTill().getTime() + ":" + workingHourList.get(i).getTill().getDivision());
        customDays.spin_cap.setSelection(workingHourList.get(i).getDays().get(0).getCapacity() - 1);
        for (int j = 0; j < workingHourList.get(i).getDays().size(); j++) {
            if (workingHourList.get(i).getDays().get(j).getDay().equals("monday")) {
                customDays.dayname.add("monday");
                customDays.b_mon = true;
                customDays.s_mon = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_mon.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_mon.setBackgroundResource(R.drawable.buttonclr);
            }
            if (workingHourList.get(i).getDays().get(j).getDay().equals("tuesday")) {
                customDays.dayname.add("tuesday");
                customDays.b_tue = true;
                customDays.s_tue = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_tue.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_tue.setBackgroundResource(R.drawable.buttonclr);
            }
            if (workingHourList.get(i).getDays().get(j).getDay().equals("wednesday")) {
                customDays.dayname.add("wednesday");
                customDays.b_wed = true;
                customDays.s_wed = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_wed.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_wed.setBackgroundResource(R.drawable.buttonclr);
            }
            if (workingHourList.get(i).getDays().get(j).getDay().equals("thursday")) {
                customDays.dayname.add("thursday");
                customDays.b_thu = true;
                customDays.s_thu = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_thur.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_thur.setBackgroundResource(R.drawable.buttonclr);
            }
            if (workingHourList.get(i).getDays().get(j).getDay().equals("friday")) {
                customDays.dayname.add("friday");
                customDays.b_fri = true;
                customDays.s_fri = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_fri.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_fri.setBackgroundResource(R.drawable.buttonclr);
            }
            if (workingHourList.get(i).getDays().get(j).getDay().equals("saturday")) {
                customDays.dayname.add("saturday");
                customDays.b_sat = true;
                customDays.s_sat = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_sat.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_sat.setBackgroundResource(R.drawable.buttonclr);
            }
            if (workingHourList.get(i).getDays().get(j).getDay().equals("sunday")) {
                customDays.dayname.add("sunday");
                customDays.b_sun = true;
                customDays.s_sun = workingHourList.get(i).getDays().get(j).getId();
                customDays.tv_sun.setTextColor(Color.parseColor("#ffffff"));
                customDays.tv_sun.setBackgroundResource(R.drawable.buttonclr);
            }
        }
        customDays.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.haveInternet(getActivity())){
                isfinish=true;
                DeleteWorkingHourMod deleteWorkingHourMod = new DeleteWorkingHourMod();
                List<String> workingHourList1 = new ArrayList<String>();
                for (int j = 0; j < workingHourList.get(i).getDays().size(); j++)
                    workingHourList1.add(workingHourList.get(i).getDays().get(j).getId());
                deleteWorkingHourMod.setWorkingHour(workingHourList1);
                serviceModel.doPostJSonRequest(deleteWorkingHourMod, token, "deleteWorkingHour");
                ll_time_slot.removeView(customDays);
                customDaysArrayList.remove(customDays);}
            }
        });
        customDaysArrayList.add(customDays);
        ll_time_slot.addView(customDays);
    }

    public void HitApi() {
        if (customDaysArrayList.size() > count) {
            if (Utils.haveInternet(getActivity())) {
                mydialog.show();
                AppoinmentMod appoinmentMod = new AppoinmentMod();
                appoinmentMod.setDoctorId(getuserdata().get_id());
                appoinmentMod.setHospitalId(hos_id);
                List<WorkingHour> workingHours = new ArrayList<>();
                for (int i = count; i < customDaysArrayList.get(count).dayname.size(); i++) {
                    WorkingHour wrkh = new WorkingHour();
                    wrkh.setName(customDaysArrayList.get(count).dayname.get(i));
                    wrkh.setWorking(true);
                    wrkh.setCapacity(Integer.parseInt(customDaysArrayList.get(count).spin_cap.getSelectedItem().toString()));

                    String fromtime = customDaysArrayList.get(count).spin_ft.getText().toString();
                    String tilltime = customDaysArrayList.get(count).spin_tt.getText().toString();
                    String fromsplit[] = fromtime.split(":");
                    String tillsplit[] = tilltime.split(":");
                    From from = new From();
                    from.setTime(Integer.parseInt(fromsplit[0]));
                    from.setDivision(Integer.parseInt(fromsplit[1]));

                    Till till = new Till();
                    till.setTime(Integer.parseInt(tillsplit[0]));
                    till.setDivision(Integer.parseInt(tillsplit[1]));


                    wrkh.setFrom(from);
                    wrkh.setTill(till);
                    workingHours.add(wrkh);
                }
                appoinmentMod.setWorkingHour(workingHours);
                serviceModel.doPostJSonRequest(appoinmentMod, token, "setSchedule");
            }
        } else if (customDaysArrayList.size() == count) {

        }
    }

    public void HitApi2(String from_time, String till_time, String capcity, String dayname) {

        if (Utils.haveInternet(getActivity())) {
            mydialog.show();
            AppoinmentMod appoinmentMod = new AppoinmentMod();
            appoinmentMod.setDoctorId(getuserdata().get_id());
            appoinmentMod.setHospitalId(hos_id);
            List<WorkingHour> workingHourList = new ArrayList<>();
            // WorkingHour workingHours=new WorkingHour();
            WorkingHour wrkh = new WorkingHour();
            // wrkh.setName(customDaysArrayList.get(count).dayname.get(i));
            wrkh.setName(dayname);
            wrkh.setWorking(true);
            //wrkh.setCapacity(Integer.parseInt(customDaysArrayList.get(count).spin_cap.getSelectedItem().toString()));
            wrkh.setCapacity(Integer.parseInt(capcity));

            //  String fromtime=customDaysArrayList.get(count).spin_ft.getText().toString();
            //  String tilltime=customDaysArrayList.get(count).spin_tt.getText().toString();
            String fromtime = from_time;
            String tilltime = till_time;
            String fromsplit[] = fromtime.split(":");
            String tillsplit[] = tilltime.split(":");
            From from = new From();
            from.setTime(Integer.parseInt(fromsplit[0]));
            from.setDivision(Integer.parseInt(fromsplit[1]));

            Till till = new Till();
            till.setTime(Integer.parseInt(tillsplit[0]));
            till.setDivision(Integer.parseInt(tillsplit[1]));


            wrkh.setFrom(from);
            wrkh.setTill(till);

            workingHourList.add(wrkh);
            appoinmentMod.setWorkingHour(workingHourList);
            serviceModel.doPostJSonRequest(appoinmentMod, token, "setSchedule");
        }

    }

    public void ConsultFee() {
        if (Utils.haveInternet(getActivity())) {
            isagain = true;
            mydialog.show();
            Consult_Fee_Mod consult_fee_mod = new Consult_Fee_Mod();
            consult_fee_mod.setHospitalId(hos_id);
            consult_fee_mod.setDoctorId(getuserdata().get_id());
            ConsultationFee consultationFee = new ConsultationFee();
            consultationFee.setMax(Integer.parseInt(edt_consult_fee.getText().toString()));
            consultationFee.setMin(Integer.parseInt(edt_consult_fee.getText().toString()));
            consult_fee_mod.setConsultationFee(consultationFee);
            serviceModel.doPostJSonRequest(consult_fee_mod, token, "setConsultationFeeForDoctor");

        }
    }

    boolean isagain = true;

    public void PrescriptionVAlid() {
        if (Utils.haveInternet(getActivity())) {
            isagain = false;
            mydialog.show();
            Prescriptionvarid_Mod prescriptionvarid_mod = new Prescriptionvarid_Mod();
            prescriptionvarid_mod.setHospitalId(hos_id);
            prescriptionvarid_mod.setDoctorId(getuserdata().get_id());
            prescriptionvarid_mod.setValidateTill(spin_prescription.getSelectedItemPosition() + 1);
            serviceModel.doPostJSonRequest(prescriptionvarid_mod, token, "setPrescriptionValidity");

        }
    }

    @Override
    public void AddData(String from_time, String tii_time, String capcity, String dayname) {

        HitApi2(from_time, tii_time, capcity, dayname);
    }

    boolean isfinish=false;
    @Override
    public void DeleteData(String day_id) {
        if(Utils.haveInternet(getActivity())){
            mydialog.show();
            isfinish=false;
        DeleteWorkingHourMod deleteWorkingHourMod = new DeleteWorkingHourMod();
        List<String> workingHourList1 = new ArrayList<String>();
            workingHourList1.add(day_id);
        deleteWorkingHourMod.setWorkingHour(workingHourList1);
        serviceModel.doPostJSonRequest(deleteWorkingHourMod, MediusApp.getPreferences(Constants.TOKEN_ID, ""), "deleteWorkingHour");
        }

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}