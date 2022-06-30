package com.example.newdoctorsapp.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Anil Tiwari on 15/12/2021.
 */

public class Utils {

    public static String appversion = "1.0";



    public static void showKeyboard(Activity activity, EditText editText) {

        if (editText.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isShowing = imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
            if (!isShowing)
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }


    }

    public static void showKeyboardOreo(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        editText.requestFocus();
    }

    public static void showKeyboardInAdapter(Activity activity) {
        InputMethodManager inm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void blink(final TextView tv) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tv.getVisibility() == View.VISIBLE) {
                            tv.setVisibility(View.INVISIBLE);
                        } else {
                            tv.setVisibility(View.VISIBLE);
                        }
                        blink(tv);
                    }
                });
            }
        }).start();
    }

    public static void hideKeyboard(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean haveInternet(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        Utils.showToastCenter(ctx,"No internet ");

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected() && cm.isActiveNetworkMetered();

    }

    public static boolean checkNetworkStatus(Context context) {
        boolean status = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo tempNetworkInfo : networkInfos) {

            if (tempNetworkInfo.isConnected()) {
                status = true;
                break;
            }
        }
        return status;
    }

    public static void showToastCenter(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public final static boolean isEmailValid(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public static String getCurrentDateAndTimeSql() {
        String formattedDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getCurrentDateAndTimeSql1() {
        String formattedDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getTwoDecimalPlaces(BigDecimal convertableValue) {
        BigDecimal doubleValue = new BigDecimal(String.valueOf(convertableValue));
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(doubleValue);
    }

    public static Bitmap convertBase64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
    public static void ShowNotFound(Context context,String msg){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Data")
                .setContentText(msg).setConfirmText("OK")
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();
                }).show();
    }
    public static String ChangeDatefromet(String date,String input_pat,String getPat){
        String formattedDate = null;
        Date ab;
     //   SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat(input_pat, Locale.ENGLISH);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        try {
            ab=sdf.parse(date);
            @SuppressLint("SimpleDateFormat")
            //  SimpleDateFormat format=new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss zzz ");
          //  SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
           // SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format= new SimpleDateFormat(getPat);
            formattedDate = format.format(ab);
            Log.e("TAG", "onViewCreated: "+formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

}
