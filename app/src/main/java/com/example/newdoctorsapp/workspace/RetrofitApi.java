package com.example.newdoctorsapp.workspace;

import com.example.newdoctorsapp.models.AppointmentHistoryModel.AppointMentHistoryResponse;
import com.example.newdoctorsapp.models.ConsulatantModel.ConsulatResponse;
import com.example.newdoctorsapp.models.ConvinienceModel.ConvieneceResponse;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDocotorModel;
import com.example.newdoctorsapp.models.CreateDoctor.CreateDoctorResponse;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.GetHoliDayDataModel;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayCalendarDatamodel;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayCalendarResponse;
import com.example.newdoctorsapp.models.HoliDaycalendarmodel.HolidayGetResponse;
import com.example.newdoctorsapp.models.HospitalApprovalList.HospitalApprovalResponse;
import com.example.newdoctorsapp.models.InviteHospoital.InviteHospitalResponse;
import com.example.newdoctorsapp.models.InviteHospoital.Invitemodeldata;
import com.example.newdoctorsapp.models.NotificationModel.NotificationResponse;
import com.example.newdoctorsapp.models.NotificationModel.SendDataHospitalAccpet;
import com.example.newdoctorsapp.models.SearchAddHospitalModel.SearchAddHospitalRespone;
import com.example.newdoctorsapp.models.SlotDelModel.SlotDelResponse;
import com.example.newdoctorsapp.models.SlotDelModel.slotdelsendmodel;
import com.example.newdoctorsapp.models.Submitfeedback.SubmitFeedbackResponse;
import com.example.newdoctorsapp.models.Submitfeedback.sumitfeedbackmodel;
import com.example.newdoctorsapp.models.doctoraddqual.DoctorAddQualModel;
import com.example.newdoctorsapp.models.doctoraddqual.DoctorAddQulResponse;
import com.example.newdoctorsapp.models.getQualiModel.GetQualificationResponse;
import com.example.newdoctorsapp.workspace.addspecialmodel.AddSpecialResponse;
import com.example.newdoctorsapp.workspace.addspecialmodel.FcmTokenModel;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentSheduleResponse;
import com.example.newdoctorsapp.workspace.model.specialmodel.SpecializationResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("patient/getSpecialityBodyPartAndDisease")
    Call<SpecializationResponse> getspecial();

    @FormUrlEncoded
    @POST("doctor/updateProfile")
    Call<AddSpecialResponse> updatspecial(@Field("specialization[]") ArrayList<String> specialization);

    @POST("doctor/updateProfile")
    Call<AddSpecialResponse> sendtoken( @Body FcmTokenModel body);


  //  @FormUrlEncoded
    @POST("doctor/getWorkingHours")
    Call<AppointmentSheduleResponse> schedule (@Body getworkingworkihgoursmodel body);

    @POST("feedback/submitFeedback")
    Call<SubmitFeedbackResponse> feedback (@Body sumitfeedbackmodel body);

    @GET("hospital/searchHospitalByPhoneNumber/{input}")
    Call<SearchAddHospitalRespone> getaddhospital(@Path("input") String input);

  //  @FormUrlEncoded
    @PUT("doctor/requestApprovalFromHospital")
    Call<InviteHospitalResponse> invitehospoital(@Body Invitemodeldata body);

     @PUT("doctor/getListOfRequestedApprovals_ByDoctor")
    Call<HospitalApprovalResponse> hospitalapproval();

    @POST("doctor/setHolidayCalendar")
    Call<HolidayCalendarResponse> holidaysenddata (@Body HolidayCalendarDatamodel body);

    @GET("doctor/getDoctorsNotification")
    Call<NotificationResponse> getnotification();

    @PUT("doctor/approveHospitalRequest")
    Call<InviteHospitalResponse> approvehospoital(@Body SendDataHospitalAccpet body);

    @PUT("doctor/denyHospitalRequest")
    Call<InviteHospitalResponse> denyhospoital(@Body SendDataHospitalAccpet body);

    @PUT("doctor/getDoctorsHolidayList")
    Call<HolidayGetResponse> getholiday(@Body GetHoliDayDataModel body);

    @POST("doctor/getListOfAllAppointments/0")
    Call<AppointMentHistoryResponse> apnmnthistory ();

    @GET("hospital/getFees")
    Call<ConvieneceResponse> getconvfee();

    @GET("doctor/getAppointmentFeeFromAppointmentId/{input}")
    Call<ConsulatResponse> getcnsltfee(@Path("input") String input);

    @PUT("doctor/deleteWorkingHour")
    Call<SlotDelResponse> slotdel(@Body slotdelsendmodel body);

  @POST("/doctor")
  Call<CreateDoctorResponse> createdoctor(@Body CreateDocotorModel body);

  @GET("doctor/getQualificationList")
  Call<GetQualificationResponse> getqualification();

  @PUT("doctor/addDoctorQualification")
  Call<DoctorAddQulResponse> adddocotorqual(@Body DoctorAddQualModel body);
}


// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiNWE1MzhiNGMtZTI3ZS00ZWZiLWFhMDMtMGNkY2VjMGNiZjhlIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbW9iaWxlcGhvbmUiOiI5NjUwNjE3NzIzIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvY291bnRyeSI6Iis5MSIsImp0aSI6ImJmOTY3ODM5LWFmZjAtNDUwNy04ZjM5LWE2MzUxM2RmYmY3NyIsImV4cCI6MTYzNzk0NDcyMiwiaXNzIjoiQWxhVGVjaCIsImF1ZCI6ImNhcmUzNjBDbGllbnQifQ.A3tYFQiCTXZlbWnt3oTtvch6n4gUG75E9RotEZRA6_4