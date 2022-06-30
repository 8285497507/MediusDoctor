package com.example.newdoctorsapp.models.HospitalApprovalList;

import com.example.newdoctorsapp.models.NotificationModel.Locality;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalApproveAddress {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("city")
    @Expose
    private HospitalCityApproval city;
    @SerializedName("state")
    @Expose
    private HospitalApprovalState state;
    @SerializedName("locality")
    @Expose
    private Locality locality;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("addressLine_1")
    @Expose
    private String addressLine1;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HospitalCityApproval getCity() {
        return city;
    }

    public void setCity(HospitalCityApproval city) {
        this.city = city;
    }

    public HospitalApprovalState getState() {
        return state;
    }

    public void setState(HospitalApprovalState state) {
        this.state = state;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
