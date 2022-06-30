package com.example.newdoctorsapp.models.AddDoctorQulification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class adDuration {

    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("till")
    @Expose
    private String till;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

}
