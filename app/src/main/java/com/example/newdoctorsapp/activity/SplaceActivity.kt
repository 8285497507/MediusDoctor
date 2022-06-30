package com.example.newdoctorsapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.demod.RXCalling.ServiceModel
import com.example.newdoctorsapp.MediusApp
import com.example.newdoctorsapp.R
import com.example.newdoctorsapp.RXCalling.BaseActivityJava
import com.example.newdoctorsapp.models.login.Loginresponce
import com.example.newdoctorsapp.models.login.UserVerifaction
import com.example.newdoctorsapp.utility.Constants
import com.example.newdoctorsapp.utility.Constants.*
import com.example.newdoctorsapp.utility.Utils
import com.google.gson.Gson
import java.util.*

class SplaceActivity : BaseActivityJava() {
    var serviceModel = ServiceModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splace2)
         if(MediusApp.GetPhonenumber(this,"number")!=null && MediusApp.GetPhonenumber(this,"number")!=""&& !MediusApp.getBoolean(this,Constants.SESSION,false)){
            if(Utils.haveInternet(this)){
                mycustomdialog.show()
                serviceModel.doPostJSonRequest( UserVerifaction(MediusApp.GetPhonenumber(this,"number")),API_USERVERIFACTION)
            }
        }else{
             Handler().postDelayed({
                 if(MediusApp.getBoolean(this,Constants.SESSION,false)){
                     // MediusApp.getBoolean(this,SESSION, false)
                      //  startActivity(Intent(this, Profile_Activity::class.java))
                        startActivity(Intent(this, MainActivity::class.java))

                 }else{
                   startActivity(Intent(this, PhoneNumberActivity::class.java))
                 // startActivity(Intent(this, Basic_Details_Activity2::class.java))
              // startActivity(Intent(this, AddQualificationDetailActivity::class.java))
             // startActivity(Intent(this, Registration_Details_Activity::class.java))
               // startActivity(Intent(this, Activity_Kyc_details::class.java))

                 }
                 finish()
             }, 3000)
        }

//        if(Utils.haveInternet(this)){
//            serviceModel.doPostJSonRequest( UserVerifaction("8601500190"),API_USERVERIFACTION)
//        }



    }

    override fun update(o: Observable?, arg: Any?) {
        mycustomdialog.dismiss()
        if(arg is Loginresponce){
            val loginresponce= arg as? Loginresponce;
            if(loginresponce?.status== SUCCESS_STATUS){

                Log.d("sddd","***"+MediusApp.getBoolean(this,Constants.SESSION, false))
                if( MediusApp.getBoolean(this,Constants.SESSION, false)){
                    return;
                }else{
                    MediusApp.saveBoolean(Constants.SESSION, true)
                    var gsonvalu=Gson().toJson(loginresponce)
                    MediusApp.savePreferences(Constants.API_GETPROFILE, gsonvalu)
                    // Log.e(TAG, "update: "+Gson().toJson(loginresponce) )

                    dialog(loginresponce.message, "Profile Status", SweetAlertDialog.SUCCESS_TYPE).setConfirmText("Ok").setConfirmClickListener { sweetAlertDialog ->
                        sweetAlertDialog.dismissWithAnimation()
                        startActivity(Intent(this, Profile_Activity::class.java))
                        finish()
                    }
                }

            }else if (loginresponce?.status== ERROR_STATUS){
                startActivity(Intent(this, PhoneNumberActivity::class.java))
              /*  dialog(loginresponce.message, "Profile Status", SweetAlertDialog.WARNING_TYPE).setConfirmText("Ok").setConfirmClickListener { sweetAlertDialog ->
                    sweetAlertDialog.dismissWithAnimation()
                    finishAffinity()
                    finish()
                }*/


            }
        }else{

        }
//when(arg){
//    is Loginresponce ->
//
//}
    }

    override fun getModel(): Observable {

       return serviceModel
    }


}