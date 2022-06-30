package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppoinetemntFrom {
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("division")
    @Expose
    private Integer division;
    @SerializedName("_id")
    @Expose
    private String id;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
