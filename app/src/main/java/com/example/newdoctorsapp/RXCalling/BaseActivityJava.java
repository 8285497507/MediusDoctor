package com.example.newdoctorsapp.RXCalling;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.fragments.AddQulifactionFragment;
import com.example.newdoctorsapp.models.login.Data;
import com.example.newdoctorsapp.models.login.Loginresponce;
import com.example.newdoctorsapp.utility.MyProgressDialog;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.gson.Gson;

import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Anil Tiwari on 15/12/2021.
 */
public abstract class BaseActivityJava extends AppCompatActivity implements java.util.Observer {

    public abstract Observable getModel();

    public Toolbar toolbar;
    public ServiceModel serviceModel = new ServiceModel();
    public Dialog mycustomdialog;
    public HorizontalPicker horizontalCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable model = getModel();

        mycustomdialog = new MyProgressDialog().progressDialog(this);
        model.addObserver(BaseActivityJava.this);

    }

    public Toolbar initToolbar(Activity activity) {
        toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(activity, R.drawable.ic_back_arrow));

        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        return toolbar;
    }

    public static void setFragment(Fragment fragment, boolean removeStack, FragmentActivity activity, int mContainer) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ftTransaction = fragmentManager.beginTransaction();
        ftTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        if (removeStack) {
            int size = fragmentManager.getBackStackEntryCount();
            fragmentManager.popBackStack(fragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ftTransaction.replace(mContainer, fragment);
        } else {
            ftTransaction.replace(mContainer, fragment);
            ftTransaction.addToBackStack(fragment.getTag());
        }

        ftTransaction.commit();

    }


    public Data getuserdata(){
        String userdata=MediusApp.getPreferences(API_GETPROFILE,"");
        Log.e(TAG, "getuserdata: "+new Gson().toJson(userdata) );
        Loginresponce login=new Gson().fromJson(userdata, com.example.newdoctorsapp.models.login.Loginresponce.class);
        return login.getData();
    }
    public SweetAlertDialog dialog(String massage,String hading,int type){
    SweetAlertDialog dialog=  new SweetAlertDialog(this, type);
        dialog.setTitleText(hading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentText(massage+"\n");
        dialog.show();
        return dialog;
    }



    public void addReplaceFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack, boolean isReplace) {

        try {
            Fragment fragment = Fragment.instantiate(this, fragmentName, bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (isReplace) {
                transaction.replace(container, fragment, fragmentName);
            } else {
                transaction.add(container, fragment, fragmentName);
            }
            transaction.addToBackStack(addToBackStack ? fragment.getClass().getName() : null);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getCurrentFragment() != null) {
            getCurrentFragment().onActivityResult(requestCode, resultCode, data);
        }
    }
    public Fragment getCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        return currentFragment;
    }

    public HorizontalPicker intitsinglerowcalender(Context context){
        horizontalCalendar = findViewById(R.id.schedule_datePicker);
        horizontalCalendar
                .setDays(90)
                .setOffset(10).setDateSelectedColor(getResources().getColor(R.color.red))
                .setDateSelectedTextColor(Color.WHITE)
                .setMonthAndYearTextColor(Color.DKGRAY)
                .setTodayButtonTextColor(Color.GREEN)
                .setTodayDateTextColor(Color.WHITE)
                .setTodayDateBackgroundColor(Color.GRAY)
                .setUnselectedDayTextColor(Color.DKGRAY)
                .setDayOfWeekTextColor(Color.DKGRAY)
                .setUnselectedDayTextColor(Color.BLACK)
                .showTodayButton(false)
                .init();
        return horizontalCalendar;
    }
}
