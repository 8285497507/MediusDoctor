package com.example.newdoctorsapp.RXCalling;

import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.MainActivity;
import com.example.newdoctorsapp.models.login.Data;
import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.example.newdoctorsapp.utility.Utils;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * Created by Anil Tiwari on 15/12/2021.
 */
public abstract class BaseFragmentJava extends Fragment implements java.util.Observer,OnBackPressedListener {

    public abstract Observable getModel();
    public static ServiceModel serviceModel=new ServiceModel();

    public Dialog mycustomdialog;
    public HorizontalPicker horizontalCalendar;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mycustomdialog=new MyProgressDialog().progressDialog(getContext());
        Observable model = getModel();

        model.addObserver(BaseFragmentJava.this);
        return onCreateViewPost(inflater, container, savedInstanceState);
    }

    protected abstract View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


//    public HorizontalCalendar intitsinglerowcalender(View context){
//        Calendar startDate = Calendar.getInstance();
//        startDate.add(Calendar.DAY_OF_MONTH,0);
//
//        /* ends after 1 month from now */
//        Calendar endDate = Calendar.getInstance();
//        endDate.add(Calendar.DAY_OF_MONTH, 30);
//
//        horizontalCalendar = new HorizontalCalendar.Builder(context, R.id.calendarView).range(startDate, endDate).datesNumberOnScreen(7).build();
//        horizontalCalendar.goToday(true);
//
//
//        return horizontalCalendar;
//    }

    public HorizontalPicker intitsinglerowcalender(View context){
        horizontalCalendar = context.findViewById(R.id.schedule_datePicker);
        horizontalCalendar
                .setDays(90)
                .setOffset(10).setDateSelectedColor(getResources().getColor(R.color.red))
                .setDateSelectedTextColor(Color.WHITE)
                .setMonthAndYearTextColor(Color.DKGRAY)
                .setTodayButtonTextColor(Color.GREEN)
                .setTodayDateTextColor(getResources().getColor(R.color.red))
                .setTodayDateBackgroundColor(getResources().getColor(R.color.red))
                .setUnselectedDayTextColor(Color.DKGRAY)
                .setDayOfWeekTextColor(Color.DKGRAY)
                .setUnselectedDayTextColor(Color.BLACK)
                .showTodayButton(false)
                .init();
        return horizontalCalendar;
    }
    public String ChangeDatefromet(String date){
        String formattedDate = null;
         Date ab;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
    try {
         ab=sdf.parse(date);
        @SuppressLint("SimpleDateFormat")
      //  SimpleDateFormat format=new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss zzz ");
      SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
         formattedDate = format.format(ab);
        Log.e("TAG", "onViewCreated: "+formattedDate);

    } catch (ParseException e) {
        e.printStackTrace();
    }

    return formattedDate;
}
    public static Data getuserdata(){
        String userdata=MediusApp.getPreferences(API_GETPROFILE,"");
        Loginresponce login=new Gson().fromJson(userdata, com.example.newdoctorsapp.models.login.Loginresponce.class);
        return login.getData();
    }
    public SweetAlertDialog dialog(String massage, String hading, int type){
        SweetAlertDialog dialog=  new SweetAlertDialog(getContext(), type);
        dialog.setTitleText(hading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentText(massage).show();
        return dialog;
    }


      public void replaceFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        ((BaseActivityJava) getActivity()).addReplaceFragment(fragmentName, bundle, container, addToBackStack, true);
    }

    public void addFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        ((BaseActivityJava) getActivity()).addReplaceFragment(fragmentName, bundle, container, addToBackStack, false);
    }

    public void replaceChildFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        addReplaceChildFragment(fragmentName, bundle, container, addToBackStack, true);
    }

    public void addChildFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        addReplaceChildFragment(fragmentName, bundle, container, addToBackStack, false);
    }


    /**
     * This method used to add/replace fragment on desire container with bundle and back stack status.
     *
     * @param fragmentName
     * @param bundle
     * @param container
     * @param addToBackStack
     * @param isReplace
     */
    public void addReplaceChildFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack, boolean isReplace) {

        try {
            Fragment fragment = Fragment.instantiate(getActivity(), fragmentName, bundle);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (isReplace) {
                transaction.replace(container, fragment, fragmentName);
            } else {
                transaction.add(container, fragment, fragmentName);
            }
            transaction.addToBackStack(addToBackStack == true ? fragment.getClass().getName() : null);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void popUpFragment(String tag) {
        Log.e("tagback",tag);
        hideSoftKeyboard(getActivity());
        try {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("popUpFragment: ", e.getMessage());
        }
    }

    public void hideSoftKeyboard(Context context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            // check if no view has focus:
            View v = ((Activity) context).getCurrentFocus();
            if (v == null)
                return;

            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("hideSoftKeyboard: ", e.getMessage());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        hideSoftKeyboard(getActivity());
    }




    public Fragment getCurrentFragment(int id) {
        return getActivity().getSupportFragmentManager().findFragmentById(id);
    }

}
