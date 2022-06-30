package com.example.newdoctorsapp.fragments;

import static com.example.newdoctorsapp.utility.Constants.SUCCESS_STATUS;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseDilogFragment;
import com.example.newdoctorsapp.models.AddHospital.AddHospital;
import com.example.newdoctorsapp.models.AddHospital.Address;
import com.example.newdoctorsapp.models.AddHospital.ResponcerAddHospital;
import com.example.newdoctorsapp.models.CityList.City;
import com.example.newdoctorsapp.models.CityList.CityStatelocalityCountryList;
import com.example.newdoctorsapp.models.CityList.Country;
import com.example.newdoctorsapp.models.CityList.Data;
import com.example.newdoctorsapp.models.CityList.Locality;
import com.example.newdoctorsapp.models.CityList.State;
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.utility.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class AddHospitalFragment extends BaseDilogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText Hname, Hlandmark, Hphonnumbe, Hpin, HAdderss, Htype, Hcity;
    private AppCompatSpinner spinner_state, spinner_cuntery, spinner_city, spinner_locality;

    private AddHospital addHospital;
    private Address address;
    private List<String> hoid;
    private List<String> cities,state,cuntry,loclaity;
    private List<String> stateList;
    private List<Locality> localityList;
    private List<Country> countryList;
    ArrayAdapter arrayAdapter;

    public AddHospitalFragment() {
    }


    public static AddHospitalFragment newInstance() {


        return new AddHospitalFragment();
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
        return inflater.inflate(R.layout.fragment_add_hospital, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
        toolbar.setTitle("Add Hospital");
        addHospital = new AddHospital();
        address = new Address();
        hoid = new ArrayList<>();
        cities = new ArrayList<>();
        stateList = new ArrayList<>();
        cuntry = new ArrayList<>();
        loclaity = new ArrayList<>();
        view.findViewById(R.id.save).setOnClickListener(this);
        Hname = view.findViewById(R.id.H_name);
        Htype = view.findViewById(R.id.H_Type);
        Hphonnumbe = view.findViewById(R.id.H_Mobilenumber);
        Hlandmark = view.findViewById(R.id.H_Landmark);
        HAdderss = view.findViewById(R.id.H_Adderss);
        Hpin = view.findViewById(R.id.H_Pin);
        spinner_city = view.findViewById(R.id.spinnerCity);
        spinner_cuntery = view.findViewById(R.id.spinnerCuntry);
        spinner_locality = view.findViewById(R.id.spinnerlocality);
        spinner_state = view.findViewById(R.id.spinnerState);
        spinner_state.setOnItemSelectedListener(this);
        spinner_city.setOnItemSelectedListener(this);
        spinner_cuntery.setOnItemSelectedListener(this);
        spinner_locality.setOnItemSelectedListener(this);
        if (Utils.haveInternet(getContext())) {
            // addHospital.setContactNumber(Hphonnumbe.getText().toString());
            // addHospital.setType(Htype.getText().toString());
            serviceModel.doPostJSonRequest(Constants.API_GETCITYSTATECUNTRY);
        }

    }


    @Override
    public void update(Observable o, Object arg) {
        Log.e("TAG", "update: "+new Gson().toJson(arg));
        if (arg.getClass() == ResponcerAddHospital.class) {

            ResponcerAddHospital responcerAddHospital = (ResponcerAddHospital) arg;
            if (responcerAddHospital.getStatus() == SUCCESS_STATUS) {
                hoid.add(responcerAddHospital.getData().get_id());

            } else {
                Utils.showToastCenter(getContext(), responcerAddHospital.getMessage());
            }
        } else if (arg.getClass() == CityStatelocalityCountryList.class) {
            CityStatelocalityCountryList countryList = (CityStatelocalityCountryList) arg;
            if (countryList.getStatus() == SUCCESS_STATUS) {
                setcityspiner(countryList.getData().getCity());
                setStatespiner(countryList.getData().getState());
                setCuntryspiner(countryList.getData().getCountry());
                setlocaltispiner(countryList.getData().getLocality());

            }else {
                Utils.showToastCenter(getContext(), countryList.getMessage());

            }

        }
    }

    private void setlocaltispiner(List<Locality> locality) {

        loclaity.clear();
        loclaity.add(0, "Select Locality");
        for (int i = 0; i <= locality.size()-1; i++) {
            loclaity.add(locality.get(i).getName());
        }
        spinner_locality.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, loclaity));

    }

    private void setCuntryspiner(List<Country> country) {
        cuntry.clear();
        cuntry.add(0, "Select Century");
        for (int i = 0; i <= country.size()-1; i++) {
            cuntry.add(country.get(i).getName());
        }
        spinner_cuntery.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, cuntry));

    }

    private void setStatespiner(List<State> state) {
        stateList.clear();
        stateList.add(0, "Select State");
        for (int i = 0; i <= state.size()-1; i++) {
            stateList.add(state.get(i).getName());
        }
        spinner_state.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, stateList));

    }

    private void setcityspiner(List<City> city) {
        cities.clear();
        cities.add(0, "Select City");
        for (int i = 0; i <= city.size()-1; i++) {
            cities.add(city.get(i).getName());
        }

        spinner_city.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdownitem, cities));

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if (validation() && Utils.haveInternet(getContext())) {
                    addHospital.setContactNumber(Hphonnumbe.getText().toString());
                    addHospital.setType(Htype.getText().toString());
                    addHospital.setContactNumber(Hphonnumbe.getText().toString());
                    addHospital.setNumberOfBed(20);
                    Address address=new Address();
//                    address.setState();
//                    addHospital.setAddress();
                    serviceModel.doPostJSonRequest(addHospital, Constants.API_ADDHOSPITAL);
                }

                break;
        }
    }


    private boolean validation() {
        if (TextUtils.isEmpty(Hpin.getText().toString())) {

            return false;
        } else if (TextUtils.isEmpty(Htype.getText().toString())) {

            return false;
        } else if (TextUtils.isEmpty(Hname.getText().toString())) {

            return false;
        } else if (TextUtils.isEmpty(Hphonnumbe.getText().toString())) {

            return false;
        } else if (TextUtils.isEmpty(Hcity.getText().toString())) {

            return false;
        } else if (TextUtils.isEmpty(Hlandmark.getText().toString())) {

            return false;
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerCity:
                if (parent.getAdapter().getItemId(position)!=0) {

                    Utils.showToastCenter(getContext(),parent.getAdapter().getItem(position).toString());
                }else {
                    Utils.showToastCenter(getContext(),"Select An Other City");
                }

                break;
            case R.id.spinnerCuntry:

                break;
            case R.id.spinnerlocality:

                break;
            case R.id.spinnerState:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface gethospitaldetail {
        void NewHospital();
    }
}