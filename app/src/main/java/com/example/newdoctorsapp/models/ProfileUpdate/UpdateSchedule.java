package com.example.newdoctorsapp.models.ProfileUpdate;

import com.example.newdoctorsapp.models.AddApointMent.Friday;
import com.example.newdoctorsapp.models.AddApointMent.Monday;
import com.example.newdoctorsapp.models.AddApointMent.Saturday;
import com.example.newdoctorsapp.models.AddApointMent.Sunday;
import com.example.newdoctorsapp.models.AddApointMent.Thursday;
import com.example.newdoctorsapp.models.AddApointMent.Tuesday;
import com.example.newdoctorsapp.models.AddApointMent.Wednesday;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateSchedule {
    @SerializedName("workingHour")
    @Expose
    private String workingHour;
    @SerializedName("sunday")
    @Expose
    private Sunday sunday;
    @SerializedName("monday")
    @Expose
    private Monday monday;
    @SerializedName("friday")
    @Expose
    private Friday friday;
    @SerializedName("wednesday")
    @Expose
    private Wednesday wednesday;
    @SerializedName("tuesday")
    @Expose
    private Tuesday tuesday;
    @SerializedName("thursday")
    @Expose
    private Thursday thursday;
    @SerializedName("saturday")
    @Expose
    private Saturday saturday;
    public UpdateSchedule(String workingHour, Monday monday) {
        this.workingHour = workingHour;
        this.monday = monday;
    }

    public UpdateSchedule(String workingHour, Tuesday tuesday) {
        this.workingHour = workingHour;
        this.tuesday = tuesday;
    }

    public UpdateSchedule(String workingHour, Wednesday wednesday) {
        this.workingHour = workingHour;
        this.wednesday = wednesday;
    }

    public UpdateSchedule(String workingHour, Thursday thursday) {
        this.workingHour = workingHour;
        this.thursday = thursday;
    }

    public UpdateSchedule(String workingHour, Friday friday) {
        this.workingHour = workingHour;
        this.friday = friday;
    }

    public UpdateSchedule(String workingHour, Sunday sunday) {
        this.workingHour = workingHour;
        this.sunday = sunday;
    }

    public UpdateSchedule(String workingHour, Saturday saturday) {
        this.workingHour = workingHour;
        this.saturday = saturday;
    }


}
