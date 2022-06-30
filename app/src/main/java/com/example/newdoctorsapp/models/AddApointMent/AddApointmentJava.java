package com.example.newdoctorsapp.models.AddApointMent;

import com.google.gson.annotations.SerializedName;

public class AddApointmentJava {
    @SerializedName("hospitalId")
    private String hospitalId;
    @SerializedName("workingHour")
    private WorkingHourjava workingHourjava;

    public AddApointmentJava(String hospitalId, WorkingHourjava workingHourjava) {
        this.hospitalId = hospitalId;
        this.workingHourjava = workingHourjava;
    }
}
