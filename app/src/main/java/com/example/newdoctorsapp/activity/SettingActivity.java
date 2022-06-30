package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.utility.Constants.SESSION;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;

import com.example.newdoctorsapp.MediusApp;
import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.utility.BaseActivity;

import java.util.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SettingActivity extends BaseActivityJava {

 //   Toolbar toolbar;

    RelativeLayout relativecontact,rel_noti,rel_privacypolicy, rel_termscondn, rel_aboutus,rel_logout;

    @Override
    public Observable getModel() {
        return serviceModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_setting);
        initToolbar(this);
        toolbar.setTitle("Settings");
        relativecontact = findViewById(R.id.relativecontact);
        rel_aboutus =  findViewById(R.id.rel_aboutus);
        rel_privacypolicy =  findViewById(R.id.rel_privacypolicy);
        rel_termscondn =  findViewById(R.id.rel_termscondn);
        rel_noti = findViewById(R.id.rel_noti);
        rel_logout = findViewById(R.id.rel_logout);

        relativecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),ContactUsAcitivity.class);
                startActivity(i);
            }
        });

        rel_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),NotificationSettingActivity.class);
                startActivity(i);
            }
        });



        rel_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(getApplicationContext(),OtheraActivity.class);
                i.putExtra("intentclass","About Us");
                startActivity(i);
            }
        });

        rel_privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),OtheraActivity.class);
                i.putExtra("intentclass","Privacy Policy");
                startActivity(i);
            }
        });

        rel_termscondn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),OtheraActivity.class);
                i.putExtra("intentclass","Terms and Condition");
                startActivity(i);
            }
        });

        rel_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }




    @Override
    public void update(Observable observable, Object o) {

    }
}
