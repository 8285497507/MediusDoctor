package com.example.newdoctorsapp.workspace;

import static com.example.newdoctorsapp.RXCalling.BaseFragmentJava.getuserdata;

import android.content.Context;
import android.util.Log;


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
import com.example.newdoctorsapp.utility.Constants;
import com.example.newdoctorsapp.workspace.addspecialmodel.AddSpecialResponse;
import com.example.newdoctorsapp.workspace.addspecialmodel.FcmTokenModel;
import com.example.newdoctorsapp.workspace.appointmentshedulemodel.AppointmentSheduleResponse;
import com.example.newdoctorsapp.workspace.model.specialmodel.SpecializationResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper ourInstance = new RetrofitHelper();

    public static RetrofitHelper getInstance() {
        return ourInstance;
    }




    private RetrofitHelper() {
    }

    RetrofitApi retrofitapi;

    public void init(final Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        ongoing.addHeader("Accept", "application/json;versions=1");
                       // ongoing.addHeader("Content-Type", "application/json");

                        //  if (Prefs.getBoolean(context, "session", false)) {
                      //  Log.d(">>>>>>>>>", "intercept: " +getuserdata().getToken());
                       //  ongoing.addHeader("auth-header",getuserdata().getToken());
                        try {
                            Log.d(">>>>>>>>>", "intercept: " +getuserdata().getToken());
                            ongoing.addHeader("auth-header",getuserdata().getToken());
                        } catch (Exception e){

                        }

                      // ongoing.addHeader("auth-header","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjNhMTBjYjg2YTY5NzczN2YwYTNlZDYiLCJmaXJzdE5hbWUiOiJhYmhpc2hlayIsImxhc3ROYW1lIjoic2luZ2giLCJnZW5kZXIiOiJGZW1hbGUiLCJwaG9uZU51bWJlciI6Ijg4MjYzMzI0NDIiLCJlbWFpbCI6Im5hIiwiYWN0aXZlIjp0cnVlLCJkZWxldGVkIjpmYWxzZSwiaG9zcGl0YWxEZXRhaWxzIjpbeyJjb25zdWx0YXRpb25GZWUiOnsibWluIjo2MDAsIm1heCI6NjAwfSwiaG9zcGl0YWwiOiI2MjNhMGJjMjg2YTY5NzczN2YwYTNlODQiLCJfaWQiOiI2MjNhMTBjYjg2YTY5NzczN2YwYTNlZDcifSx7ImNvbnN1bHRhdGlvbkZlZSI6eyJtaW4iOjg0MCwibWF4Ijo4NDB9LCJob3NwaXRhbCI6IjYyMzk5YzFhODZhNjk3NzM3ZjBhM2UzZSIsIl9pZCI6IjYyM2ExMTgxODZhNjk3NzM3ZjBhM2YwYSJ9XSwic3BlY2lhbGl6YXRpb24iOlsiNjIzMzY0MDc4MjM3Njc2ODQ2ZTdjZjkyIl0sInF1YWxpZmljYXRpb24iOlsiNjIzYTE2NGY4NmE2OTc3MzdmMGE0MTY3Il0sIm92ZXJhbGxFeHBlcmllbmNlIjoiMSBtb250aHMiLCJpbWFnZSI6InN0YXRpYy91c2VyL2RlZmF1bHQucG5nIiwidmVyaWZpZWQiOnRydWUsIl9fdiI6MCwiaWQiOiI2MjNhMTBjYjg2YTY5NzczN2YwYTNlZDYiLCJpYXQiOjE2NTEzODQwNDB9.UyWONOgh-hiSSb-ZdWomUGStWCCIFbQ40z2uvTRnwRg");
                        return chain.proceed(ongoing.build());
                        // }
                        //return chain.proceed(ongoing.build());

                    }
                })
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        try {
            retrofitapi = retrofit.create(RetrofitApi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void callspecialization(Callback<SpecializationResponse> callback) {
        Call<SpecializationResponse> response;
        response = retrofitapi.getspecial();
        response.enqueue(callback);
    }

//    public void callAddspecial(Callback<AddSpecialResponse> callback, ArrayList<String> specialization) {
//        Call<AddSpecialResponse> response;
//        response = retrofitapi.updatspecial(specialization);
//        response.enqueue(callback);
//    }

    public void callAddspecial(Callback<AddSpecialResponse> callback,  FcmTokenModel body) {
        Call<AddSpecialResponse> response;
        response = retrofitapi.sendtoken(body);
        response.enqueue(callback);
    }

    public void CallscheduleAppoinment(Callback<AppointmentSheduleResponse> callback, getworkingworkihgoursmodel body) {
        Call<AppointmentSheduleResponse> response;
        response = retrofitapi.schedule(body);
        response.enqueue(callback);
    }

    public void CallAddFeedback(Callback<SubmitFeedbackResponse> callback, sumitfeedbackmodel body) {
        Call<SubmitFeedbackResponse> response;
        response = retrofitapi.feedback(body);
        response.enqueue(callback);
    }

    public void callAddSearchHospital(Callback<SearchAddHospitalRespone> callback, String input ) {
        Call<SearchAddHospitalRespone> response;
        response = retrofitapi.getaddhospital(input);
        response.enqueue(callback);
    }

    public void callinvitehospital(Callback<InviteHospitalResponse> callback, Invitemodeldata body) {
        Call<InviteHospitalResponse> response;
        response = retrofitapi.invitehospoital(body);
        response.enqueue(callback);
    }

    public void callhospitalApproval(Callback<HospitalApprovalResponse> callback) {
        Call<HospitalApprovalResponse> response;
        response = retrofitapi.hospitalapproval();
        response.enqueue(callback);
    }

    public void calladdholiday(Callback<HolidayCalendarResponse> callback, HolidayCalendarDatamodel body) {
        Call<HolidayCalendarResponse> response;
        response = retrofitapi.holidaysenddata(body);
        response.enqueue(callback);
    }

    public void callnotofication(Callback<NotificationResponse> callback) {
        Call<NotificationResponse> response;
        response = retrofitapi.getnotification();
        response.enqueue(callback);
    }

    public void callapprovehospoital(Callback<InviteHospitalResponse> callback, SendDataHospitalAccpet body) {
        Call<InviteHospitalResponse> response;
        response = retrofitapi.approvehospoital(body);
        response.enqueue(callback);
    }

    public void calldenyhospoital(Callback<InviteHospitalResponse> callback, SendDataHospitalAccpet body) {
        Call<InviteHospitalResponse> response;
        response = retrofitapi.denyhospoital(body);
        response.enqueue(callback);
    }

    public void callGetHoliDay(Callback<HolidayGetResponse> callback, GetHoliDayDataModel body) {
        Call<HolidayGetResponse> response;
        response = retrofitapi.getholiday(body);
        response.enqueue(callback);
    }

    public void callApnmntHistory(Callback<AppointMentHistoryResponse> callback) {
        Call<AppointMentHistoryResponse> response;
        response = retrofitapi.apnmnthistory();
        response.enqueue(callback);
    }
    public void callconvfee(Callback<ConvieneceResponse> callback) {
        Call<ConvieneceResponse> response;
        response = retrofitapi.getconvfee();
        response.enqueue(callback);
    }

    public void callcnsltfee(Callback<ConsulatResponse> callback, String input ) {
        Call<ConsulatResponse> response;
        response = retrofitapi.getcnsltfee(input);
        response.enqueue(callback);
    }
    public void calldelslot(Callback<SlotDelResponse> callback, slotdelsendmodel body) {
        Call<SlotDelResponse> response;
        response = retrofitapi.slotdel(body);
        response.enqueue(callback);
    }

    public void CallCreateDoctor(Callback<CreateDoctorResponse> callback, CreateDocotorModel body) {
        Call<CreateDoctorResponse> response;
        response = retrofitapi.createdoctor(body);
        response.enqueue(callback);
    }

    public void Callgetqualification(Callback<GetQualificationResponse> callback) {
        Call<GetQualificationResponse> response;
        response = retrofitapi.getqualification();
        response.enqueue(callback);
    }

    public void calladddoctorqual(Callback<DoctorAddQulResponse> callback, DoctorAddQualModel body) {
        Call<DoctorAddQulResponse> response;
        response = retrofitapi.adddocotorqual(body);
        response.enqueue(callback);
    }
}