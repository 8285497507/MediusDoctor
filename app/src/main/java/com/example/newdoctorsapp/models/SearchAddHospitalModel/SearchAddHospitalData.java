package com.example.newdoctorsapp.models.SearchAddHospitalModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchAddHospitalData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private SearchAddHospoitalAddress address;
    @SerializedName("doctors")
    @Expose
    private List<Object> doctors = null;
    @SerializedName("specialisedIn")
    @Expose
    private List<String> specialisedIn = null;
    @SerializedName("anemity")
    @Expose
    private List<String> anemity = null;
    @SerializedName("services")
    @Expose
    private List<String> services = null;
    @SerializedName("treatmentType")
    @Expose
    private List<Object> treatmentType = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("payment")
    @Expose
    private List<Object> payment = null;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("numberOfBed")
    @Expose
    private Integer numberOfBed;
    @SerializedName("ICUBeds")
    @Expose
    private Integer iCUBeds;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("modeOfAppointments")
    @Expose
    private List<String> modeOfAppointments = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

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

    public SearchAddHospoitalAddress getAddress() {
        return address;
    }

    public void setAddress(SearchAddHospoitalAddress address) {
        this.address = address;
    }

    public List<Object> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Object> doctors) {
        this.doctors = doctors;
    }

    public List<String> getSpecialisedIn() {
        return specialisedIn;
    }

    public void setSpecialisedIn(List<String> specialisedIn) {
        this.specialisedIn = specialisedIn;
    }

    public List<String> getAnemity() {
        return anemity;
    }

    public void setAnemity(List<String> anemity) {
        this.anemity = anemity;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<Object> getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(List<Object> treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getPayment() {
        return payment;
    }

    public void setPayment(List<Object> payment) {
        this.payment = payment;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(Integer numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public Integer getICUBeds() {
        return iCUBeds;
    }

    public void setICUBeds(Integer iCUBeds) {
        this.iCUBeds = iCUBeds;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public List<String> getModeOfAppointments() {
        return modeOfAppointments;
    }

    public void setModeOfAppointments(List<String> modeOfAppointments) {
        this.modeOfAppointments = modeOfAppointments;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}