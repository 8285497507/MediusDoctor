package com.example.newdoctorsapp.models.CreateDoctor;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDoctorpost {


    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("KYCDetails")
    @Expose
    private String kYCDetails;
    @SerializedName("registration")
    @Expose
    private Registration registration;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("overallExperience")
    @Expose
    private String overallExperience;



    @SerializedName("specialization")
    @Expose
    private List<String> specialization = null;

    @SerializedName("qualification")
    @Expose
    private List<String> qualification = null;
    @SerializedName("hospitalDetails")
    @Expose
    private List<HospitalDetail2> hospitalDetails = null;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKYCDetails() {
        return kYCDetails;
    }

    public void setKYCDetails(String kYCDetails) {
        this.kYCDetails = kYCDetails;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<String> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<String> specialization) {
        this.specialization = specialization;
    }

    public List<String> getQualification() {
        return qualification;
    }

    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }

    public List<HospitalDetail2> getHospitalDetails() {
        return hospitalDetails;
    }

    public void setHospitalDetails(List<HospitalDetail2> hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }
    public String getOverallExperience() {
        return overallExperience;
    }

    public void setOverallExperience(String overallExperience) {
        this.overallExperience = overallExperience;
    }
}


