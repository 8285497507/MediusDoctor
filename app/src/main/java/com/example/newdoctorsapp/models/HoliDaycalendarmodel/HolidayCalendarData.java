package com.example.newdoctorsapp.models.HoliDaycalendarmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayCalendarData {
    @SerializedName("doctorId")
    @Expose
    private String doctorId;
    @SerializedName("hospitalId")
    @Expose
    private String hospitalId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("delData")
    @Expose
    private DelData delData;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DelData getDelData() {
        return delData;
    }

    public void setDelData(DelData delData) {
        this.delData = delData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
