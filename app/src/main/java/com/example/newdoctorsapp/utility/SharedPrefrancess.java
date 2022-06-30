package com.example.newdoctorsapp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefrancess {

    public static final String USER_DATA = "USER_DATA";
    public static final String QULIFACTION = "QULIFACTION";
    public static final String REGISTATION = "REGISTATION";
    public static final String KYCDETALE = "KYCDETALE";
    public static final String HOSPITAL = "HOSPITAL";
    public static final String QUALISPECL = "QUALISPECL";

    private static SharedPrefrancess sharedPrefUtils;

    private SharedPrefrancess() {

    }

    public static SharedPrefrancess getInstance() {
        if (sharedPrefUtils == null) {
            sharedPrefUtils = new SharedPrefrancess();
        }
        return sharedPrefUtils;
    }

    public void setDoctorBasic(Context context, String key,
                               String value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);


        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        Log.d("sdasd","**"+ key+""+value);
        appInstallInfoEditor.apply();
    }


    public void setQualispecl(Context context, String key,
                               String value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(QUALISPECL, Context.MODE_PRIVATE);

        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        Log.d("sdasd","**"+ key+""+value);
        appInstallInfoEditor.apply();
    }

    public void setDoctorHospital(Context context, String key,
                                  String value) {
        SharedPreferences appInstallInfoSharedPref = context
                .getSharedPreferences(HOSPITAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }

    public void setDoctorRegistation(Context context, String key,
                                     String value) {
        SharedPreferences appInstallInfoSharedPref = context
                .getSharedPreferences(REGISTATION, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }

    public void setDoctorKycdetail(Context context, String key,
                                   String value) {
        SharedPreferences appInstallInfoSharedPref = context
                .getSharedPreferences(KYCDETALE, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }

    public void setDoctorqulifaction(Context context, String key, String value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(QULIFACTION, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }
public String GetDoctorBasic(Context context,String key){
    SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(key, Context.MODE_PRIVATE);
  return appInstallInfoSharedPref.getString(key,null);
}

    public  void clearSpecificSharePref(Context context,String PREF_NAME) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        try {
            SharedPreferences.Editor editor = appInstallInfoSharedPref.edit();
            editor.remove(PREF_NAME);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
