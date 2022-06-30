package com.example.newdoctorsapp.models.DoctorApointmentList;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Location {

    @SerializedName("coordinates")
    private List<Object> mCoordinates;

    public List<Object> getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(List<Object> coordinates) {
        mCoordinates = coordinates;
    }

}
