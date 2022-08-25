package com.example.newdoctorsapp;


import static com.example.newdoctorsapp.utility.Constants.API_GETPROFILE;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.google.gson.Gson;

/**
 * Created by Anil Tiwari on 15/12/2021.
 */
public class MediusApp extends Application {
    static SharedPreferences sharedPreferences;
    public static final String TAG = MediusApp.class.getSimpleName();
    private static MediusApp mInstance;
    private static Application context;
    Context actagedoctor;

    private static final String PREF_NAME = "actage";
    private static final String PREF_NUMBER = "actage";
   private  static  MyDatabase myDatabase=null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;

        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        //
        actagedoctor = this.getApplicationContext();

    }

    public static Context getContext() {
        return context.getApplicationContext();
    }

    public static void hideNotificationBar(Activity activity) {
        // activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public static void savePreferences(String key, String value) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPreferences(String key, String val) {
        String value = "";
        try {
            value = sharedPreferences.getString(key, val);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    public static void saveBoolean(String key, boolean value) {
        try {
            SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = info.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void saveBoolean(Context context,String key, boolean value) {
        try {
            SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = info.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /*  public static boolean getBoolean(String key, boolean defValue) {

        return sharedPreferences.getBoolean(key, defValue);
    }*/

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return info.getBoolean(key, defValue);
    }


    public static void SavePhonenumber (Context context,String key,String number){
        try {
            SharedPreferences info = context.getSharedPreferences(PREF_NUMBER,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = info.edit();
            editor.putString(key, number);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }  public static String GetPhonenumber (Context context,String key){

        SharedPreferences info = context.getSharedPreferences(PREF_NUMBER,
                Context.MODE_PRIVATE);
return info.getString(key,"");
    }
    public static void clearSharePref() {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void clearSpecificSharePref(String PREF_NAME) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(PREF_NAME);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static synchronized MediusApp getInstance() {
        return mInstance;
    }
  public static synchronized MyDatabase  getDatabase() {
        if(myDatabase==null){
            myDatabase=new MyDatabase(getContext());
        }
        return myDatabase;
    }


}
