package com.example.newdoctorsapp.fragments.UpdateProfile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.adapter.SearchHospitalAdapter;
import com.example.newdoctorsapp.models.SearchAddHospitalModel.SearchAddHospitalData;
import com.example.newdoctorsapp.models.SearchAddHospitalModel.SearchAddHospitalRespone;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalAddFragment extends BaseFragmentJava implements Callback<SearchAddHospitalRespone> {

   EditText edttexthospital;
   ProgressBar mProgreeBar;
   RelativeLayout mRelativeAdddearch;
   RecyclerView rcv_searhhospital;
   SearchAddHospitalData searchAddHospitalData;
  ServiceModel serviceModel=new ServiceModel();

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hospital_addfragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitHelper.getInstance().init(getContext());
         edttexthospital = getView().findViewById(R.id.edttexthospital);
        mProgreeBar = getView().findViewById(R.id.mProgreeBar);
        mRelativeAdddearch = getView().findViewById(R.id.mRelativeAdddearch);
        rcv_searhhospital = getView().findViewById(R.id.rcv_searhhospital);


        rcv_searhhospital.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcv_searhhospital.setHasFixedSize(true);
        rcv_searhhospital.setNestedScrollingEnabled(false);

        edttexthospital.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (Utils.haveInternet(getContext())) {
                    mProgreeBar.setVisibility(View.VISIBLE);
//                    RetrofitHelper.getInstance().callAddSearchHospital(HospitalAddFragment.this,
//                            "8826332442");
                    if (edttexthospital.getText().toString().length()!=10){
                        mProgreeBar.setVisibility(View.GONE);
                    }
                    else {
                    //    serviceModel.doPostJSonRequest( edttexthospital.getText().toString(),getuserdata().getToken(),"getaddhospital");
                        RetrofitHelper.getInstance().callAddSearchHospital(HospitalAddFragment.this,
                                edttexthospital.getText().toString());
                    }
                } else

                    Snackbar.make(mRelativeAdddearch, "Check Data Connection", Snackbar.LENGTH_SHORT).show();

                if (edttexthospital.getText().toString().equals("")){
                }

            }
        });
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void update(Observable observable, Object arg) {

      if(arg.getClass()!=null){
      if(arg.getClass()== SearchAddHospitalRespone.class){
          SearchAddHospitalRespone resp= (SearchAddHospitalRespone) arg;
          if(resp.getStatus()==200){
              mProgreeBar.setVisibility(View.GONE);
          }
      }}
      else{

      }
    }

    @Override
    public void onResponse(Call<SearchAddHospitalRespone> call, Response<SearchAddHospitalRespone> response) {
        if (response.isSuccessful()) {
            if (response.body().getStatus()==200) {

                mProgreeBar.setVisibility(View.GONE);
                String Address = response.body().getData().getAddress().getCity().getName() + "," +
                        response.body().getData().getAddress().getState().getName() + "," +
                        response.body().getData().getAddress().getLocality().getName() + ","
                        + response.body().getData().getAddress().getCountry();
                String hospitalname = response.body().getData().getName();
                String hospitalId = response.body().getData().get_id();
                boolean visible = response.body().getData().getContainsDoctor();
                String UserId = getuserdata().get_id();


                if (Address.length() != 0) {
                    SearchHospitalAdapter searchHospitalAdapter = new SearchHospitalAdapter(getContext(), Address, hospitalname, hospitalId,UserId,HospitalAddFragment.this,visible);
                    rcv_searhhospital.setAdapter(searchHospitalAdapter);
                } else {
                    rcv_searhhospital.setVisibility(View.GONE);
                    SearchHospitalAdapter searchHospitalAdapter = new SearchHospitalAdapter(getContext(), Address, hospitalname, hospitalId,UserId,HospitalAddFragment.this,visible);
                    rcv_searhhospital.setAdapter(searchHospitalAdapter);
                }

            } else {
                Snackbar.make(mRelativeAdddearch, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                mProgreeBar.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onFailure(Call<SearchAddHospitalRespone> call, Throwable t) {
        mycustomdialog.show();
    }
}
