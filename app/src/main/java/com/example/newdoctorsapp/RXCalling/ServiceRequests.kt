package com.example.demod.RXCalling

import android.content.Context
import com.example.newdoctorsapp.models.Appointment.Datetime
import com.example.newdoctorsapp.models.ProfileDetail.PostGetdata
import com.example.newdoctorsapp.models.ProfileDetail.Qualification
import com.example.newdoctorsapp.utility.Constants.*
import com.google.gson.Gson

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by Anil Tiwari on 15/12/2021.
 */
class ServiceRequests {

    private var serviceName = ""
    private var serviceInterface: ServiceInterface? = null
    private var serviceResponseInterface: ServiceCallback? = null
    private var stringHashMap: HashMap<String, String>? = null
    private var jsonValue: Any? = null
    private var jsonValue2: Any? = null
    private var paymentToken: String? = null
    private var Header: String? = null
    private var url: String? = null
    private var myvalue: String? = null
    var response: Observable<*>? = null

    constructor(
            observable: java.util.Observable,
            stringHashMap: HashMap<String, String>,
            serviceName: String
    ) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.stringHashMap = stringHashMap
    }

    constructor(
            observable: java.util.Observable,
            stringHashMap: HashMap<String, String>,
            serviceName: String,
            header: String
    ) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.stringHashMap = stringHashMap
        this.Header = header
    }

    constructor(observable: java.util.Observable, serviceName: String) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
    }

    constructor(observable: java.util.Observable, serviceName: String, jsonValue: Any) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.jsonValue = jsonValue
    }

    constructor(
            observable: java.util.Observable,
            serviceName: String,
            hashMap: HashMap<String, String>
    ) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.stringHashMap = hashMap
    }


    constructor(observable: java.util.Observable, serviceName: String, header: String) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.Header = header
    }

    constructor(
            observable: java.util.Observable,
            serviceName: String,
            jsonValue: Any,
            header: String
    ) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.jsonValue = jsonValue
        this.Header = header
    }

    constructor(
            observable: java.util.Observable,
            serviceName: String,
            url: String,
            header: String
    ) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.url = url
        this.Header = header
    }

    constructor(
            observable: java.util.Observable,
            serviceName: String,
            value: String,
            url: String,
            header: String) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.url = url
        this.myvalue = value
        this.Header = header
    }

    constructor(observable: java.util.Observable, serviceName: String, url: String, header: String, jsonValue: Any) {
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName = serviceName
        this.url = url
        this.Header = header
        this.jsonValue = jsonValue
    }


    constructor(observable: java.util.Observable, serviceName: String, hashMap: HashMap<String, String>, jsonValue: Any){
        this.serviceResponseInterface = observable as ServiceCallback
        this.serviceName=serviceName
        this.stringHashMap=hashMap
        this.jsonValue=jsonValue
    }

    fun execute() {
        serviceInterface = ApiClient.client!!.create(ServiceInterface::class.java)
        doRequest()
    }

    fun execute(myvalue: String) {
        this.myvalue = myvalue
        serviceInterface = ApiClient.client!!.create(ServiceInterface::class.java)
        doRequest()
    }

    fun execute(context: Context) {
        serviceInterface = ApiClient.client!!.create(ServiceInterface::class.java)
        doRequest()
    }


    private fun doRequest() {
        if (serviceName == API_LOGIN) {
            response = serviceInterface!!.GetOTP(
                    stringHashMap!!.get("phoneNumber")
            )
        } else if (serviceName == API_OTPVERIFACTION) {
            response = serviceInterface!!.OTPVerifaction(
                    stringHashMap!!.get("phoneNumber"),
                    stringHashMap!!.get("OTP")
            )
        } else if (serviceName == API_ADDQULIFACTION) {
            response = serviceInterface!!.AddQulifaction(
                    jsonValue
            )
        } else if (serviceName == API_CREATEDOCTOR) {
            response = serviceInterface!!.Createdoctor(
                    jsonValue
            )
        } else if (serviceName == API_GETCITYSTATECUNTRY) {
            response = serviceInterface!!.Getcitycuntrystate()
        } else if (serviceName == API_GETHOSPITALLIST) {
            response = serviceInterface!!.GetHospitallist(

            )
        } else if (serviceName == API_POSTAPPOINTMENTLIST) {
            response = serviceInterface!!.SetSession(
                    stringHashMap!!.get("auth-header")!!,
                    stringHashMap!!.get("page"),
                    jsonValue

            )
        } else if (serviceName == API_GETPROFILE) {
            response = serviceInterface!!.GetDoctorInformation(
                    stringHashMap!!.get("tokentid"),
                    stringHashMap!!.get("id"),
                   PostGetdata(true)

            )
        } else if (serviceName == API_GETSPECIALITY) {
            response = serviceInterface!!.GetSpicelist(
                    Header!!
            )
        } else if (serviceName == API_ADDSESSION) {
            response = serviceInterface!!.ADDdoctorSchedulellist(
                    Header!!, jsonValue!!
            )
        } else if (serviceName == API_KYCDETAIL) {
            response = serviceInterface!!.SetKyc(
                    jsonValue!!
            )
        } else if (serviceName == API_GTHOSPITALLISTBYDOCTORID) {
            response = serviceInterface!!.GetHospitalbydoctorid(
                    Header!!, url!!
            )
        } else if (serviceName == API_APPOINTMENTLIST) {
            response = serviceInterface!!.GetSession(
                   Header,
                   jsonValue!!
            )
        }else if (serviceName == API_GETTOTALEARNING) {
            response = serviceInterface!!.GetTotalEarning(
                   Header!!
            )
        }else if (serviceName == API_GETPENDINGAMOUNT) {
            response = serviceInterface!!.GetPendingAmount(
                   Header!!
            )
        }else if (serviceName == API_GETAPPOINTMENTSUMMARY) {
            response = serviceInterface!!.GetappointmentSummary(
                   Header!!
            )
        }else if (serviceName == API_PROFILEUPDATE) {
            response = serviceInterface!!.ProfileUpdate(
                   Header!!,jsonValue!!
            )
        }else if (serviceName == API_KYCDETAILUPDATE) {
            response = serviceInterface!!.UpdateKyc(
                   jsonValue!!
            )
        }else if (serviceName == API_DELETEPROFILEHOSPITAL) {
            response = serviceInterface!!.DeleteHospitalFromDoctor(
                  Header!!, jsonValue!!
            )
        }else if (serviceName == API_DELETESPECLIZATIONANDQU) {
            response = serviceInterface!!.DeleteSpecializationAndQualification(
                    Header!!, jsonValue!!
            )
        }else if (serviceName == API_UPDATESCHEDULE) {
            response = serviceInterface!!.UpdateWorkingHour(
                    Header!!, jsonValue!!)
        }else if (serviceName == API_UPDATEQULIFACTION) {
            response = serviceInterface!!.UpDateQulifaction(
                    stringHashMap?.get("token")!!,
                    stringHashMap?.get("id")!!,
                    jsonValue!!
            )
        }else if (serviceName == API_USERVERIFACTION) {
            response = serviceInterface!!.CheckVerificationStatus(
                    jsonValue!!
            )


        }

        else if (serviceName == "setSchedule") {
            response = serviceInterface!!.setSchedule(Header!!,jsonValue
            )
        }
        else if (serviceName == "getFeeAndValidity") {
            response = serviceInterface!!.getFeeAndValidity(Header!!,jsonValue
            )
        }


        else  if (serviceName == "deleteWorkingHour") {
            response = serviceInterface!!.deleteWorkingHour(
                Header!!, jsonValue
            )
        }
        else  if (serviceName == "setConsultationFeeForDoctor") {
            response = serviceInterface!!.setConsultationFeeForDoctor(
                Header!!, jsonValue
            )
        }

        else  if (serviceName == "setPrescriptionValidity") {
            response = serviceInterface!!.setPrescriptionValidity(
                Header!!, jsonValue
            )
        }
        else  if (serviceName == "setPrescriptionValidity") {
            response = serviceInterface!!.setPrescriptionValidity(
                Header!!, jsonValue
            )
        }
        else  if (serviceName == "getaddhospital") {
            response = serviceInterface!!.getaddhospital(
                Header!!,jsonValue
            )
        }

        else  if (serviceName == "getnotification") {
            response = serviceInterface!!.getnotification(
                Header!!
            )
        }

//  else if (serviceName == "AddRegistration") {
//            response = serviceInterface!!.AddRegistration(
//                Header!!,jsonValue
//            )
//        }
//  else if (serviceName == "AddBankDetails") {
//            response = serviceInterface!!.AddBankDetails(
//                Header!!,jsonValue
//            )
//        }
//  else if (serviceName == "GetProfile") {
//            response = serviceInterface!!.GetProfile(
//                Header!!,url!!
//            )
//        }
//  else if (serviceName == "GetAppoitmentHistory") {
//            response = serviceInterface!!.getappohis(
//               Header!!, url!!, myvalue!!
//            )
//        }
//
//
//
//        else if (serviceName == "AddSlot") {
//            response = serviceInterface!!.AddSlot(
//                Header!!,jsonValue
//            )
//        }
//
//
//        else if (serviceName == "GetDoctorAppointmentEarning") {
//            response = serviceInterface!!.GetDoctorAppointmentEarning(
//                Header!!, url!!, myvalue!!
//            )
//        }
//
//        else if (serviceName == "GetQualification") {
//            response = serviceInterface!!.GetQualification(
//                Header!!, url!!, myvalue!!
//            )
//        }
//
//        else if (serviceName == "GetRegistration") {
//            response = serviceInterface!!.GetRegistration(
//                Header!!, url!!, myvalue!!
//            )
//        }


        /* else if (serviceName == "AddProfile") {
                   response = serviceInterface!!.AddProfile(
                       jsonValue
                   )
               }*/

        response!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { o -> o }
                .subscribe(
                        Consumer<Any> { this.handleResults(it) },
                        Consumer<Throwable> { this.handleError(it) })
    }

    private fun handleResults(o: Any) {
//        Log.e("responsechcek>>", "response from service");
        serviceResponseInterface!!.onSuccess(o)

    }

    private fun handleError(t: Throwable) {
        serviceResponseInterface!!.onFail(t.message!!)
    }

}
