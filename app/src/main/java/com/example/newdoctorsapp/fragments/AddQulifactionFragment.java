package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.MediusApp.getContext;
import static com.example.newdoctorsapp.activity.Basic_Details_Activity.setRecyclerViewAdopter;
import static com.example.newdoctorsapp.fragments.ProfileDetailFragment.getprofiledetail;
import static com.example.newdoctorsapp.utility.Constants.API_PROFILEUPDATE;
import static com.example.newdoctorsapp.utility.Constants.API_UPDATEQULIFACTION;
import static com.example.newdoctorsapp.utility.Constants.ERROR;
import static com.example.newdoctorsapp.utility.Constants.ERROR_STATUS;
import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseDilogFragment;
import com.example.newdoctorsapp.activity.AddQualificationDetailActivity;
import com.example.newdoctorsapp.adapter.QualificationListAdapter;
import com.example.newdoctorsapp.interfaces.DegreeIdCallback;
import com.example.newdoctorsapp.models.AddDoctorQulification.AddQulifaction;
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce;
import com.example.newdoctorsapp.models.AddDoctorQulification.adDuration;
import com.example.newdoctorsapp.models.ProfileDetail.Qualification;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateProfileResponce;
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateQulifaction;
import com.example.newdoctorsapp.models.ProfileUpdate.basicdetailupdate;
import com.example.newdoctorsapp.models.getQualiModel.GetQualiData;
import com.example.newdoctorsapp.models.getQualiModel.GetQualificationResponse;
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQulifactionFragment extends BaseDilogFragment implements View.OnClickListener, Callback<GetQualificationResponse>, DegreeIdCallback {

    public static final String TAG = AddQulifactionFragment.class.getName();
    EditText q_name, q_org, q_mail;
    TextView q_duration, q_till,text_view1;
    Button q_btn_next, q_add;
    AppCompatImageView q_remove;
    private AddQulifaction addQulifaction;
    private adDuration duration;
    RecyclerView recy_degreeadd;
    final Calendar myCalendar = Calendar.getInstance();
    ArrayList<GetQualiData> getQualiData = new ArrayList<>();
    private ArrayList<String> qulifactionId ;
    private ArrayList<AddQulifaction> addQulifactionArrayList = new ArrayList<>();
    private Context context;
    private Qualification qualification;
    String tag,QULID="",ORGNAME="";
    int count=0;

    HashMap<String,String> hashMap;
    public AddQulifactionFragment(Context context, Qualification qualification, String tag) {
        this.context = context;
        this.qualification = qualification;
        this.tag = tag;
        Log.e(TAG, "AddQulifactionFragment: "+tag );
    }

    public static AddQulifactionFragment newInstance(Context context, Qualification qualification, String tag) {
        return new AddQulifactionFragment(context, qualification, tag);
    }

    @Override
    public Observable getModel() {
        return serviceModel;
    }


    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_qulifaction, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addQulifaction = new AddQulifaction();
        qulifactionId = new ArrayList<>();
        hashMap=new HashMap<>();
        initToolbar(view);
        toolbar.setTitle("Add Qulifaction");

        duration = new adDuration();
        q_name = view.findViewById(R.id.q_name);
        q_org = view.findViewById(R.id.q_org);
        text_view1 = view.findViewById(R.id.text_view1);
        q_duration = view.findViewById(R.id.q_duration);
        q_till = view.findViewById(R.id.q_till);
        q_mail = view.findViewById(R.id.q_mail);
        recy_degreeadd = view.findViewById(R.id.recy_degreeadd);
       recy_degreeadd.setLayoutManager(new LinearLayoutManager(getContext()));
//        recy_degreeadd.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recy_degreeadd.setHasFixedSize(true);
//        recy_degreeadd.setNestedScrollingEnabled(false);
        q_btn_next = (Button) view.findViewById(R.id.btn_next);
        q_add = (Button) view.findViewById(R.id.q_add);
        q_remove = view.findViewById(R.id.q_remove);
        q_add.setOnClickListener(this);
        q_remove.setOnClickListener(this);
        q_btn_next.setOnClickListener(this);
        q_till.setOnClickListener(this);
        q_duration.setOnClickListener(this);
        if (qualification != null) {
            //q_name.setText(qualification.getQualificationName());
            q_name.setText(qualification.getQualificationName().getName());
            q_org.setText(qualification.getCertificationOrganisation());
           // q_till.setText(qualification.getDuration().getTill());
            //q_duration.setText(qualification.getDuration().getFrom());
            //q_mail.setText(qualification.getEmail());

        }

        if (Utils.haveInternet(getContext())) {
            mycustomdialog.show();
            RetrofitHelper.getInstance().Callgetqualification(this);
        }


        text_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count==0){
                    recy_degreeadd.setVisibility(View.VISIBLE);
                    count=1;
                    return;
                } else {
                    recy_degreeadd.setVisibility(View.GONE);
                    count=0;
                    return;
                }

            }
        });


    }

    @Override
    public void update(Observable o, Object arg) {
        mycustomdialog.dismiss();
        if (arg.getClass() == QulifactionResponce.class) {
            QulifactionResponce qulifactionResponc = (QulifactionResponce) arg;
            if (qulifactionResponc.getStatus() == SUCCESS_STATUS) {

                switch (tag) {
                    case "up":
                        if(qulifactionResponc.getStatus()==SUCCESS_STATUS){

                            getprofiledetail();


//                            dialog(qulifactionResponc.getData().getQualificationName(),"Updated "+
//                                    qulifactionResponc.getMessage()+"fully", SweetAlertDialog.SUCCESS_TYPE).setConfirmText("ok")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//
//
//                                    sweetAlertDialog.dismissWithAnimation();
//                                }
//                            });
//                            dismiss();
                        }else {
                            dialog(qulifactionResponc.getMessage(), "Oops..",SweetAlertDialog.ERROR_TYPE).setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            });

                        }
                        break;
                    case "add":
                        qulifactionId.add(qulifactionResponc.getData().get_id());
                        mycustomdialog.show();
                        serviceModel.doPostJSonRequest(new UpdateQulifaction(qulifactionId),getuserdata().getToken(), API_PROFILEUPDATE);
                        break;


                    case "Newadd":
                        setRecyclerViewAdopter(context, qulifactionResponc);
                        dismiss();
//                        dialog(qulifactionResponc.getMessage(),qulifactionResponc.getData().getQualificationName(),SweetAlertDialog.SUCCESS_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                                sweetAlertDialog.dismissWithAnimation();
//                            }
//                        });
                        break;

                }



            } else if (qulifactionResponc.getStatus() == ERROR_STATUS) {
                dialog(qulifactionResponc.getMessage(),ERROR,SweetAlertDialog.ERROR_TYPE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

            }

        }
        else if(arg.getClass()== UpdateProfileResponce.class){
            UpdateProfileResponce updateProfileResponce= (UpdateProfileResponce) arg;
            if(updateProfileResponce.getStatus()==SUCCESS_STATUS){

                getprofiledetail();
//                dialog(updateProfileResponce.getMessage(),updateProfileResponce.getData().getFirstName()+" "+updateProfileResponce.getData().getLastName() ,SweetAlertDialog.SUCCESS_TYPE).setConfirmText("ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                        sweetAlertDialog.dismissWithAnimation();
//                    }
//                });
//                dismiss();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (validation() && Utils.haveInternet(getContext())) {
                    addQulifaction.setQualificationName(QULID);
                    addQulifaction.setCertificationOrganisation(q_org.getText().toString());
                   // addQulifaction.setEmail(q_mail.getText().toString());
                   // duration.setFrom(q_duration.getText().toString());
                    //duration.setTill(q_till.getText().toString());
                   // addQulifaction.setDuration(duration);
                    mycustomdialog.show();
                    if(qualification!=null && getuserdata()!=null){
                        hashMap.put("id",qualification.get_id());
                        hashMap.put("token",getuserdata().getToken());
                        serviceModel.doPostJSonRequest(addQulifaction,hashMap,API_UPDATEQULIFACTION);
                    }else {
                        serviceModel.doPostJSonRequest(addQulifaction, Constants.API_ADDQULIFACTION);

                    }

                }

                break;
            case R.id.q_add:
                clear();
                break;
            case R.id.q_remove:
                dismiss();

                break;
            case R.id.q_duration:
                showDatePicker(q_duration);

                break;
            case R.id.q_till:
                showDatePicker(q_till);

                break;
        }

    }

    private void clear() {
        if (validation()) {
            q_name.setText("");
           // q_duration.setText("");
           // q_till.setText("");
           // q_mail.setText("");
            q_org.setText("");
        }


    }

    private boolean validation() {
        if (QULID.equals("")) {
            Utils.showToastCenter(getContext(), "Select degree");
            return false;
        }  else if (TextUtils.isEmpty(q_org.getText().toString())) {
            Utils.showToastCenter(getContext(), "Enter organisation");

            return false;
        }
        return true;
    }


    @Override
    public void onResponse(Call<GetQualificationResponse> call, Response<GetQualificationResponse> response) {
        if (response.isSuccessful()){
            mycustomdialog.dismiss();
            if (response.body().getStatus().equals(200)){
                Log.d("sdsd","**chfg**");

                getQualiData = (ArrayList<GetQualiData>)response.body().getData();
                if (response.body().getData().size() != 0) {
                    QualificationListAdapter qualificationListAdapter = new QualificationListAdapter(getContext(),getQualiData,
                            AddQulifactionFragment.this,AddQulifactionFragment.this::degreeidcallback);
                    recy_degreeadd.setAdapter(qualificationListAdapter);
                } else {
                    QualificationListAdapter qualificationListAdapter = new QualificationListAdapter(getContext(), getQualiData,
                            AddQulifactionFragment.this,AddQulifactionFragment.this::degreeidcallback);
                    recy_degreeadd.setAdapter(qualificationListAdapter);
                }

            }else {
                Log.d("sdsd","***xvdf*");
            }
        }
    }

    @Override
    public void onFailure(Call<GetQualificationResponse> call, Throwable t) {
        mycustomdialog.dismiss();
        Log.d("sdsd","****");
    }

    @Override
    public void degreeidcallback(String qualid, String orgname) {
        text_view1.setText(orgname);
        QULID = qualid;
        ORGNAME = orgname;

        Log.d("sdsd","****"+qualid);

    }
}
