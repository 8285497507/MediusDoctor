package com.example.newdoctorsapp.fragments;

import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
import com.example.newdoctorsapp.activity.MainActivity;
import com.example.newdoctorsapp.activity.SettingActivity;
import com.example.newdoctorsapp.adapter.PageviewAdopter;
import com.google.android.material.tabs.TabLayout;

import java.util.Observable;


public class NewProfile_Fragment extends BaseFragmentJava implements View.OnClickListener {
public static final String TAG=NewProfile_Fragment.class.getName();

    private TextView username, userdegree,tvmobile;
    private ImageView profileimage, editeimage,img_setting;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PageviewAdopter pagerAdapter;
    private Context context;

    public NewProfile_Fragment(Activity mainActivity) {
        this.context = mainActivity;
    }

    public static Fragment newInstance(Activity mainActivity) {

        return new NewProfile_Fragment(mainActivity);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //onchangefragment=(Appointments_fragment.Onchangefragment) context;

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
        return inflater.inflate(R.layout.fragment_new_profile, container, false);
    }





    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // initToolbar(view);
      //      toolbar.setTitle("Profile");


        initView(view);
    }

    private void initView(View view) {

        username = view.findViewById(R.id.username);
        img_setting = view.findViewById(R.id.img_setting);
        userdegree = view.findViewById(R.id.userdegree);
        tvmobile = view.findViewById(R.id.tvmobile);
        profileimage = view.findViewById(R.id.profileimage);
        editeimage = view.findViewById(R.id.profileedite);
        editeimage.setOnClickListener(this);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(null);
        viewPager.beginFakeDrag();

        tabLayout.setScrollContainer(false);
        viewPager.setScrollContainer(false);
        tabLayout.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                Log.e(TAG, "onSystemUiVisibilityChange: "+visibility );
            }
        });


        username.setText(getuserdata().getFirstName() + " " + getuserdata().getLastName());
        userdegree.setText(getuserdata().getQualification().getQualificationName().getAbbreviation());
        userdegree.setText(getuserdata().getPhoneNumber());
        if (getuserdata().getGender().equalsIgnoreCase("Male")) {
            profileimage.setImageResource(R.drawable.male);
        } else if (getuserdata().getGender().equalsIgnoreCase("Female")) {
            profileimage.setImageResource(R.drawable.female);
        }
        else if (getuserdata().getGender().equalsIgnoreCase("Other")) {
            profileimage.setImageResource(R.drawable.newprofile);
            profileimage.setVisibility(View.VISIBLE);
        }
        setViewpagerAdopter();

        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i = new Intent(getContext(),SettingActivity.class);
             startActivity(i);
            }
        });
    }

    private void setViewpagerAdopter() {

        mycustomdialog.show();
        pagerAdapter = new PageviewAdopter(getChildFragmentManager());
//        pagerAdapter.AddFragment(Appointments_fragment.newInstance(context), "Appointment");
//        pagerAdapter.AddFragment(Earning_Fragment.newInstance(context), "Earning");
        pagerAdapter.AddFragment(ProfileDetailFragment.newInstance(context), "");
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
       mycustomdialog.dismiss();

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profileedite:

                break;
        }

    }

    @Override
    public boolean onBackPressed() {
       // onDestroy();
        return false;
    }

    @Override
    public void onResume() {
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        pagerAdapter=null;
        super.onDestroy();

    }


}