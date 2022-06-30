package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppointmentWorkingHour {

    @SerializedName("from")
    @Expose
    private AppoinetemntFrom from;
    @SerializedName("till")
    @Expose
    private AppointementTil till;
    @SerializedName("Days")
    @Expose
    private List<AppointmentDay> days = null;

    public AppoinetemntFrom getFrom() {
        return from;
    }

    public void setFrom(AppoinetemntFrom from) {
        this.from = from;
    }

    public AppointementTil getTill() {
        return till;
    }

    public void setTill(AppointementTil till) {
        this.till = till;
    }

    public List<AppointmentDay> getDays() {
        return days;
    }

    public void setDays(List<AppointmentDay> days) {
        this.days = days;
    }
}