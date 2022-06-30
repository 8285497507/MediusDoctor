package com.example.newdoctorsapp.models.AddApointMent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkingHourjava {
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

    public WorkingHourjava(Monday monday) {
        this.monday = monday;
    }

    public WorkingHourjava(Tuesday tuesday) {
        this.tuesday = tuesday;
    }

    public WorkingHourjava(Wednesday wednesday) {
        this.wednesday = wednesday;
    }

    public WorkingHourjava(Thursday thursday) {
        this.thursday = thursday;
    }

    public WorkingHourjava(Friday friday) {
        this.friday = friday;
    }

    public WorkingHourjava(Saturday saturday) {
        this.saturday = saturday;
    }

    public WorkingHourjava(Sunday sunday) {
        this.sunday = sunday;
    }
}
