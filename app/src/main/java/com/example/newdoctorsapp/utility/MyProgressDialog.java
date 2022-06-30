package com.example.newdoctorsapp.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.example.newdoctorsapp.R;


/**
 * Created by Anil Tiwari on 15/12/2021.
 */
public final class MyProgressDialog {


    public final Dialog progressDialog( Context context) {
        Dialog dialog = new Dialog(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_my_progress_bar, null);
        dialog.setContentView(inflate);
        dialog.setCancelable(false);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }


}
