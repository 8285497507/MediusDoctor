//package com.example.newdoctorsapp.fragments;
//
//import static android.app.Activity.RESULT_OK;
//
//import android.accounts.AccountManager;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.os.Handler;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.newdoctorsapp.R;
//import com.example.newdoctorsapp.RXCalling.BaseFragmentJava;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.Observable;
//
//
//public class BottomBarFragment extends BaseFragmentJava implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener{
//
//    public static final String TAG = BottomBarFragment.class.getName();
//    public static final String TAGS = "Hometags";
//
//
//    private boolean doubleBackToExitPressedOnce = false;
//
//    private BottomNavigationView bottomNavigationView;
//
//
//
//
//
//
//
//
//
//    @Override
//    public Observable getModel() {
//        return serviceModel;
//    }
//
//
//
//    @Override
//    protected View onCreateViewPost(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_bottom_bar, container, false);
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        bottomNavigationView = view.findViewById(R.id.navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setOnNavigationItemReselectedListener(this);
//        replaceChildFragment(AppointmentsFragment.TAG, getArguments(), R.id.frame_layout, false);
//
//    }
//
//
//
//    @Override
//    public void backButtonPressed() {
//        if (doubleBackToExitPressedOnce) {
//
//
//            getActivity().finishAffinity();
//            getActivity().finish();
//            // Close all activites
//            // System.exit(0);  // closing files, releasing resources
//        } else {
//
//            if(bottomNavigationView.getSelectedItemId()!=R.id.home){
//                bottomNavigationView.setSelected(true);
//                bottomNavigationView.setSelectedItemId(R.id.home);
//                replaceChildFragment(AppointmentsFragment.TAG, getArguments(), R.id.frame_layout, false);
//
//
//            }else {
//                doubleBackToExitPressedOnce = true;
//              //  showMessage(getString(R.string.please_click_bACK_again_to_exit));
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        doubleBackToExitPressedOnce = false;
//
//                    }
//                }, 2000);
//
//            }
//
//        }
//
//    }
//
//
//
//    @Override
//    public boolean onBackPressed() {
//        return false;
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//
//    }
//
//    @Override
//    public void onNavigationItemReselected(@NonNull MenuItem item) {
//        Bundle bundle = new Bundle();
//        switch (item.getItemId()) {
//            case R.id.home:
//                replaceChildFragment(AppointmentsFragment.TAG, getArguments(), R.id.frame_layout, false);
//                break;
//            case R.id.chart:
//                replaceChildFragment(schedule_appointment_fragment.TAG, bundle, R.id.frame_layout, false);
//
//                break;
//            case R.id.time:
//                replaceChildFragment(HisteryOfApointmentFragment.TAG, bundle, R.id.frame_layout, false);
//                break;
//                case R.id.profile:
//                replaceChildFragment(NewProfile_Fragment.TAG, bundle, R.id.frame_layout, false);
//                break;
//
//        }
//
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (bottomNavigationView.getSelectedItemId() == R.id.nav_home) {
//            Fragment newHomeFragment = getChildFragmentManager().findFragmentByTag(AppointmentsFragment.TAG);
//            if (newHomeFragment instanceof Appointments_fragment) {
//                newHomeFragment.onActivityResult(requestCode, resultCode, data);
//            }
//        }
//    }
//
//}