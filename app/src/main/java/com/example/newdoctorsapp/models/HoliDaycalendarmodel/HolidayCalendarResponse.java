package com.example.newdoctorsapp.models.HoliDaycalendarmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayCalendarResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private HolidayCalendarData data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public HolidayCalendarData getData() {
        return data;
    }

    public void setData(HolidayCalendarData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
