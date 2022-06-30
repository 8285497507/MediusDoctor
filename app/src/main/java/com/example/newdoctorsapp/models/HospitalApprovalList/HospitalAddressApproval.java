package com.example.newdoctorsapp.models.HospitalApprovalList;

import com.example.newdoctorsapp.models.CityList.Locality;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalAddressApproval {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private HospitalApproveAddress address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HospitalApproveAddress getAddress() {
        return address;
    }

    public void setAddress(HospitalApproveAddress address) {
        this.address = address;
    }
}