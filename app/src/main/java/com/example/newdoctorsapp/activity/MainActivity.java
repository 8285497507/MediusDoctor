package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;
import static com.example.newdoctorsapp.utility.Constants.SESSION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.demod.RXCalling.ServiceModel;
import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.RXCalling.OnBackPressedListener;
import com.example.newdoctorsapp.fragments.AppointmentsFragment;
import com.example.newdoctorsapp.fragments.Appointments_fragment;

import com.example.newdoctorsapp.fragments.HisteryOfApointmentFragment;
import com.example.newdoctorsapp.fragments.NewProfile_Fragment;
import com.example.newdoctorsapp.fragments.ProfileDetailFragment;
import com.example.newdoctorsapp.fragments.SessionApointmentListFragment;
import com.example.newdoctorsapp.fragments.schedule_appointment_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends BaseActivityJava implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener ,BottomNavigationView.OnNavigationItemReselectedListener{
    private BottomNavigationView bottomNavigationView;
    int menuItem;
    private AppCompatImageView logImage;

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar(this).setVisibility(View.GONE);
        initview();

    }

    private void initview() {
        toolbar.setTitle(getuserdata().getFirstName() + " " + getuserdata().getLastName());
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.newprofile));
        bottomNavigationView = findViewById(R.id.navigation);
        this.findViewById(R.id.logout).setOnClickListener(this);
        bottomNavigationView.setItemHorizontalTranslationEnabled(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setItemIconTintList(null);
        setFragment(new AppointmentsFragment(), true, this, R.id.container);

        //  addReplaceFragment(BottomBarFragment.TAG, getIntent().getExtras(), R.id.container, true, false);

    }

    @Override
    public void update(Observable o, Object arg) {

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                 //  setFragment(AppointmentsFragment.newInstance(this), true, this, R.id.container);
                   setFragment(new AppointmentsFragment(), true, this, R.id.container);
                return true;
            case R.id.chart:
                setFragment(HisteryOfApointmentFragment.newInstance(this), true, this, R.id.container);
              //  item.setIcon(R.drawable.ic_hospitallist2);
                return true;
            case R.id.profile:
                setFragment(NewProfile_Fragment.newInstance(this), true, this, R.id.container);
             //   item.setIcon(R.drawable.ic_profile2);
                return true;
            case R.id.time:
              //  setFragment(schedule_appointment_fragment.newInstance(this), true, this, R.id.container);
                setFragment(new AppoinmentScedule(), true, this, R.id.container);

             //   startActivity(new Intent(MainActivity.this,AppoinmentScedule.class));
                return true;
        }
        return true;
    }



    @Override
    public void onBackPressed() {
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();

        } else if (!(fragment instanceof OnBackPressedListener) || !((OnBackPressedListener) fragment).onBackPressed()) {
            if (bottomNavigationView.getMenu().getItem(0).isChecked()) {
                dialog(getuserdata().getFirstName() + " " + getuserdata().getLastName(), "Do You wont to Exit !", SweetAlertDialog.WARNING_TYPE).setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finishAffinity();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                      //  logout();
                        sweetAlertDialog.dismissWithAnimation();

                    }
                });


            } else {
                bottomNavigationView.setSelectedItemId(R.id.nav_home);
            }

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                logout();

                break;
        }
    }

    private void logout() {
        dialog(getuserdata().getFirstName() + " " + getuserdata().getLastName(), "Do You wont to logout ??", SweetAlertDialog.WARNING_TYPE).setConfirmText("Logout").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                MediusApp.clearSharePref();
                MediusApp.saveBoolean(SESSION, false);
                Intent intent = new Intent(getApplicationContext(), PhoneNumberActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                sweetAlertDialog.dismissWithAnimation();
            }
        }).setCancelText("Cancel").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });

    }

    @Override
    protected void onResume() {

//initview();
        super.onResume();
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}