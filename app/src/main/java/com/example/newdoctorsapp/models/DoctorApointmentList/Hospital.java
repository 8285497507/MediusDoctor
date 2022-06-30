package com.example.newdoctorsapp.models.DoctorApointmentList;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Hospital {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("anemity")
    private List<Object> mAnemity;
    @SerializedName("contactNumber")
    private String mContactNumber;
    @SerializedName("deleted")
    private Boolean mDeleted;
    @SerializedName("doctors")
    private List<Object> mDoctors;
    @SerializedName("location")
    private Location mLocation;
    @SerializedName("name")
    private String mName;
    @SerializedName("numberOfBed")
    private Long mNumberOfBed;
    @SerializedName("payment")
    private List<Object> mPayment;
    @SerializedName("services")
    private List<Object> mServices;
    @SerializedName("specialisedIn")
    private List<Object> mSpecialisedIn;
    @SerializedName("treatmentType")
    private List<Object> mTreatmentType;
    @SerializedName("type")
    private String mType;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public List<Object> getAnemity() {
        return mAnemity;
    }

    public void setAnemity(List<Object> anemity) {
        mAnemity = anemity;
    }

    public String getContactNumber() {
        return mContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        mContactNumber = contactNumber;
    }

    public Boolean getDeleted() {
        return mDeleted;
    }

    public void setDeleted(Boolean deleted) {
        mDeleted = deleted;
    }

    public List<Object> getDoctors() {
        return mDoctors;
    }

    public void setDoctors(List<Object> doctors) {
        mDoctors = doctors;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getNumberOfBed() {
        return mNumberOfBed;
    }

    public void setNumberOfBed(Long numberOfBed) {
        mNumberOfBed = numberOfBed;
    }

    public List<Object> getPayment() {
        return mPayment;
    }

    public void setPayment(List<Object> payment) {
        mPayment = payment;
    }

    public List<Object> getServices() {
        return mServices;
    }

    public void setServices(List<Object> services) {
        mServices = services;
    }

    public List<Object> getSpecialisedIn() {
        return mSpecialisedIn;
    }

    public void setSpecialisedIn(List<Object> specialisedIn) {
        mSpecialisedIn = specialisedIn;
    }

    public List<Object> getTreatmentType() {
        return mTreatmentType;
    }

    public void setTreatmentType(List<Object> treatmentType) {
        mTreatmentType = treatmentType;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public Long get_V() {
        return m_V;
    }

    public void set_V(Long _V) {
        m_V = _V;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
