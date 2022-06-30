package com.example.demod.RXCalling

import com.example.medius.models.deletehour.DeleteResp
import com.example.newdoctorsapp.models.AddApointMent.ApointmentResponce
import com.example.newdoctorsapp.models.AddDoctorQulification.QulifactionResponce
import com.example.newdoctorsapp.models.AddHospital.ResponcerAddHospital
import com.example.newdoctorsapp.models.Apointmentlist.apointmentcount
import com.example.newdoctorsapp.models.CityList.CityStatelocalityCountryList
import com.example.newdoctorsapp.models.CommonResp
import com.example.newdoctorsapp.models.DoctorApointmentList.DoctorAppointmentList
import com.example.newdoctorsapp.models.HospitalList.HospitalList
import com.example.newdoctorsapp.models.HospitalList.HospitalListBydocId
import com.example.newdoctorsapp.models.Kycdetail.KycrRsponce
import com.example.newdoctorsapp.models.NotificationModel.NotificationResponse
import com.example.newdoctorsapp.models.ProfileDetail.PostGetdata
import com.example.newdoctorsapp.models.ProfileDetail.ProfileDetail
import com.example.newdoctorsapp.models.ProfileUpdate.UpdateProfileResponce
import com.example.newdoctorsapp.models.SearchAddHospitalModel.SearchAddHospitalRespone
import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.SpecialityBodyPartAndDisease
import com.example.newdoctorsapp.models.feevalidity.GetFeeValidity
import com.example.newdoctorsapp.models.login.Loginresponce
import com.example.newdoctorsapp.models.login.OtpVerifaction
import com.example.newdoctorsapp.models.working_hour.Working_Hour_Resp
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Anil Tiwari on 15/12/2021.
 */

interface ServiceInterface {

    companion object {
        // val BASE_URL = "https://care360-net-dev.azurewebsites.net/api/"
      //  val BASE_URL = "http://18.224.1.250:3000/"
        val BASE_URL = "http://18.218.162.226:3000/"

    }

    // yha prtype kro
//
    @POST("doctor/login")
    fun GetOTP(@Query("phoneNumber") phon: String?): Observable<OtpVerifaction>

    @POST("doctor/login")
    fun OTPVerifaction(
            @Query("phoneNumber") phon: String?,
            @Query("OTP") OTP: String?
    ): Observable<Loginresponce>

    @POST("doctor")
    fun Createdoctor(@Body createDdoctor: Any?): Observable<Loginresponce>

    // @Headers("Content-Type: application/json")
    @POST("doctor/getDoctorById/{id}")
    fun GetDoctorInformation(
            @Header("auth-header") header: String?,
            @Path("id") id: String?, @Body postGetdata: PostGetdata?): Observable<ProfileDetail>

    @PUT("doctor/addDoctorQualification")
    fun AddQulifaction(@Body AddQulifaction: Any?): Observable<QulifactionResponce>

    @PUT("doctor/updateQualification/{id}")
    fun UpDateQulifaction(@Header("auth-header") Authorization: String, @Path("id") id: String, @Body AddQulifaction: Any?): Observable<QulifactionResponce>

    @GET("admin/getCityStateLocalityCountry")
    fun Getcitycuntrystate(): Observable<CityStatelocalityCountryList>

    @GET("hospital")
    fun GetHospitallist(): Observable<HospitalList>

    @GET("patient/getSpecialityBodyPartAndDisease")
    fun GetSpicelist(@Header("auth-header") Authorization: String): Observable<SpecialityBodyPartAndDisease>

    @GET("hospital")
    fun ADDHospitallist(@Body addhospital: Any): Observable<ResponcerAddHospital>

    @PUT("doctor/setSchedule")
    fun ADDdoctorSchedulellist(@Header("auth-header") Hader: String, @Body AddApointment: Any): Observable<ApointmentResponce>

    @POST("doctor/viewAppointmentsByDate/{page}")
    fun SetSession(@Header("auth-header") Authorization: String, @Path("page") page: String?,@Body datee: Any?): Observable<DoctorAppointmentList>

    @POST("doctor/setKYC")
    fun SetKyc(@Body date: Any): Observable<KycrRsponce>

    @POST("doctor/updateKyc")
    fun UpdateKyc(@Body date: Any): Observable<KycrRsponce>


    @POST("doctor/getWorkingHours")
    fun GetSession(@Header("auth-header") Authorization: String?,
                   @Body working: Any): Observable<Working_Hour_Resp>

    @GET("doctor/getHospitalListByDoctorId/{id}")
    fun GetHospitalbydoctorid(@Header("auth-header") Authorization: String, @Path("id") id: String): Observable<HospitalListBydocId>

    @GET("doctor/getTotalEarnings")
    fun GetTotalEarning(@Header("auth-header") Authorization: String): Observable<HospitalListBydocId>

    @GET("doctor/getPendingAmount")
    fun GetPendingAmount(@Header("auth-header") Authorization: String): Observable<HospitalListBydocId>

    @GET("doctor/appointmentSummary")
    fun GetappointmentSummary(@Header("auth-header") Authorization: String): Observable<apointmentcount>

    @POST("doctor/updateProfile")
    fun ProfileUpdate(@Header("auth-header") Authorization: String, @Body profile: Any): Observable<UpdateProfileResponce>

    @HTTP(method = "DELETE", path = "doctor/deleteHospitalFromDoctor", hasBody = true)
    fun DeleteHospitalFromDoctor(@Header("auth-header") Authorization: String, @Body Hospital: Any): Observable<Loginresponce>

    @HTTP(method = "DELETE", path = "doctor/deleteSpecializationAndQualification", hasBody = true)
    fun DeleteSpecializationAndQualification(@Header("auth-header") Authorization: String, @Body SpliQuli: Any): Observable<Loginresponce>

    @PUT("doctor/updateWorkingHour")
    fun UpdateWorkingHour(@Header("auth-header") Authorization: String, @Body SpliQuli: Any): Observable<ApointmentResponce>

 @PUT("doctor/checkVerificationStatus")
    fun CheckVerificationStatus( @Body UserVerifaction: Any): Observable<Loginresponce>

    @Headers("Content-Type: application/json")
    @PUT ("/doctor/setSchedule")
    fun setSchedule(@Header("auth-header") Authorization: String,      @Body any: Any?    ): Observable<com.example.medius.models.AppoinmentModel.AppointResp.AppoinmentResp>

    @Headers("Content-Type: application/json")
    @PUT("/doctor/getFeeAndValidity")
    fun getFeeAndValidity(@Header("auth-header") Authorization: String, @Body any: Any?): Observable<GetFeeValidity>


    @Headers("Content-Type: application/json")
    @PUT("/doctor/deleteWorkingHour")
    fun deleteWorkingHour(@Header("auth-header") Authorization: String, @Body any: Any?): Observable<DeleteResp>


    @Headers("Content-Type: application/json")
    @PUT("/doctor/setConsultationFeeForDoctor")
    fun setConsultationFeeForDoctor(@Header("auth-header") Authorization: String, @Body any: Any?): Observable<CommonResp>

    @Headers("Content-Type: application/json")
    @PUT("/doctor/setPrescriptionValidity")
    fun setPrescriptionValidity(@Header("auth-header") Authorization: String, @Body any: Any?): Observable<CommonResp>

    @GET("hospital/searchHospitalByPhoneNumber/{input}")
    fun getaddhospital(@Header("auth-header") Authorization: String,@Path("input") input: Any?): Observable<SearchAddHospitalRespone>

    @Headers("Content-Type: application/json")
    @GET("doctor/getDoctorsNotification")
    fun getnotification(@Header("auth-header") Authorization: String): Observable<NotificationResponse>



//    @Headers("Content-Type: application/json")
//    @GET
//   fun GetHospitallist(@Header("auth-header") Authorization: String, @Url url: String): Observable<HospitalList>

//
//   @POST("Auth/GetAccessToken")
//   fun GetAccessToken(@Body any: Any?): Observable<GetToken>
//
//
//   @Headers("Content-Type: application/json")
//    @POST("Doctor/profile/AddProfile")
//   fun AddProfile(@Header("Authorization") Authorization: String, @Body any: Any?): Observable<AddProfile>
//
//   @Headers("Content-Type: application/json")
//    @POST("Doctor/profile/AddQualification")
//   fun AddQualification(@Header("Authorization") Authorization: String, @Body any: Any?): Observable<AddQualification>
//
//
//   @Headers("Content-Type: application/json")
//    @POST("Doctor/profile/AddRegistration")
//   fun AddRegistration(@Header("Authorization") Authorization: String, @Body any: Any?): Observable<CommonResposne>
//
//   @Headers("Content-Type: application/json")
//    @POST("Doctor/profile/AddBankDetails")
//   fun AddBankDetails(@Header("Authorization") Authorization: String, @Body any: Any?): Observable<CommonResposne>
//
//
//    @Headers("Content-Type: application/json")
//    @GET
//    fun getappohis(@Header("Authorization") Authorization: String, @Url url: String,@Query("profileId") input: String): Observable<AppoHisResponse>
//
//
//    @Headers("Content-Type: application/json")
//    @POST("Doctor/schedule/AddSlot")
//    fun AddSlot(@Header("Authorization") Authorization: String, @Body any: Any?): Observable<CommonResposne>
//
//    @Headers("Content-Type: application/json")
//    @GET
//    fun GetDoctorAppointmentEarning(@Header("Authorization") Authorization: String, @Url url: String,@Query("profileId") input: String?): Observable<AppointmentResponse>
//
//    @Headers("Content-Type: application/json")
//    @GET
//    fun GetQualification(@Header("Authorization") Authorization: String, @Url url: String,@Query("profileId") input: String?): Observable<DrQualificationResponse>
//
//    @Headers("Content-Type: application/json")
//    @GET
//    fun GetRegistration(@Header("Authorization") Authorization: String, @Url url: String,@Query("profileId") input: String): Observable<DrRegResponse>
//
//
//    @POST("UesrTest/GetUserTestByMedicalTestId")
//   fun GetUserTestByMedicalTestId(@Body any: Any?): Observable<String>
//
//


}

