package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.utility.Constants.SESSION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.RXCalling.Framentcallback;
import com.example.newdoctorsapp.RXCalling.OnBackPressedListener;
import com.example.newdoctorsapp.adapter.PageviewAdopter;
import com.example.newdoctorsapp.fragments.AppointmentsFragment;
import com.example.newdoctorsapp.fragments.Appointments_fragment;
import com.example.newdoctorsapp.fragments.Earning_Fragment;
import com.example.newdoctorsapp.fragments.HisteryOfApointmentFragment;
import com.example.newdoctorsapp.fragments.NewProfile_Fragment;
import com.example.newdoctorsapp.fragments.ProfileDetailFragment;
import com.example.newdoctorsapp.fragments.schedule_appointment_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Profile_Activity extends BaseActivityJava implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener  {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View navigationHader;
    private AppCompatImageView profileimage;
    private AppCompatTextView username, userphonembur;
    private static int  tabselectid=0;
    private static String  tabselecttitel;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initToolbar(this);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.navigation);
        navigationHader = navigationView.getHeaderView(0);
        profileimage = navigationHader.findViewById(R.id.imageView);
        username = navigationHader.findViewById(R.id.username);
        userphonembur = navigationHader.findViewById(R.id.userphonenumber);
        navigationView.setNavigationItemSelectedListener(this);
     //   navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(this);
        initView();
        tabselecttitel="Hi Dr." + getuserdata().getFirstName() + " " + getuserdata().getLastName();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabselectid>0){
                    onBackPressed();
                }else {
                    drawerLayout.open();
                }

            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void initView() {

        username.setText("Dr."+getuserdata().getFirstName() + " " + getuserdata().getLastName());
        userphonembur.setText(getuserdata().getPhoneNumber());
        navigationView.getMenu().getItem(0).setChecked(true);
        settoolbartitel(tabselecttitel,R.drawable.ic_baseline_apps_24);
        ssetFragment( tabselectid);

    }

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.close();
        switch (item.getItemId()) {
            case R.id.nav_home:
                tabselectid=0;
                if(!item.isChecked())
                ssetFragment(tabselectid);
                return true;
            case R.id.chart:
                tabselectid=1;
                if(!item.isChecked())
                    ssetFragment(tabselectid);
                return true;
            case R.id.time:
                tabselectid=2;
                if(!item.isChecked())
                    ssetFragment(tabselectid);
                return true;
            case R.id.profile:
                tabselectid=3;
                if(!item.isChecked())
                    ssetFragment(tabselectid);
                return true;
            case R.id.aboutus:
                tabselectid=4;
                if(!item.isChecked())
                return true;
            case R.id.logout:
                    logout();
                return true;
            default:
                if(!item.isChecked())
                    ssetFragment(tabselectid);
        }

        return false;
    }

    public  void ssetFragment(int i) {
        navigationView.getMenu().getItem(i).setChecked(true);
        switch (i) {
            case 0:
          //   tabselecttitel="Hi Dr." + getuserdata().getFirstName() + " " + getuserdata().getLastName();
             tabselecttitel="Appointment Calendar";
             settoolbartitel(tabselecttitel,0);
                setFragment(new AppointmentsFragment(), true, this, R.id.container);

                break;
            case 1:
                tabselecttitel="Schedule Appointment";
                setFragment(schedule_appointment_fragment.newInstance(this), true, this, R.id.container);
                break;
            case 2:
                tabselecttitel="Appointment history";
                settoolbartitel(tabselecttitel,R.drawable.ic_back_arrow);
                setFragment(HisteryOfApointmentFragment.newInstance(this), true, this, R.id.container);

                break;
            case 3:
                tabselecttitel="Profile";
                setFragment(NewProfile_Fragment.newInstance(this), true, this, R.id.container);

                break;
            case 4:
                break;
            case 5:

                break;
            default:

        }
    }


    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        settoolbartitel(tabselecttitel,R.drawable.ic_back_arrow);
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

        settoolbartitel(tabselecttitel,R.drawable.ic_baseline_apps_24);
    }

    private void settoolbartitel(String titel, int id) {
        if(tabselectid>0){
            toolbar.setTitle(titel);
            toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow));

        }else {
          //  toolbar.setTitle("Hi Dr." + getuserdata().getFirstName() + " " + getuserdata().getLastName());
            toolbar.setTitle(" Appointment Calendar");
            toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_apps_24));

        }


    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()) {
            drawerLayout.close();
        }
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();

        } else if (!(fragment instanceof OnBackPressedListener) || !((OnBackPressedListener) fragment).onBackPressed()) {
            if (tabselectid==0) {
                dialog(getuserdata().getFirstName() + " " + getuserdata().getLastName(), "Do you want to Exit!", SweetAlertDialog.WARNING_TYPE).setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                        finishAffinity();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();

                    }
                });


            } else {
               // navigationView.setCheckedItem(R.id.nav_home);
                tabselectid=0;
                ssetFragment(tabselectid);
            }

        }


    }


    private void logout() {
        dialog(getuserdata().getFirstName() + " " + getuserdata().getLastName(), "Do you wont to logout?", SweetAlertDialog.WARNING_TYPE).setConfirmText("Logout").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
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


}
