package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.example.newdoctorsapp.models.ProfileUpdate.HospitalDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fee {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("hospitalDetails")
    @Expose
    private HospitalDetailsappointment hospitalDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HospitalDetailsappointment getHospitalDetails() {
        return hospitalDetails;
    }

    public void setHospitalDetails(HospitalDetailsappointment hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }

}
