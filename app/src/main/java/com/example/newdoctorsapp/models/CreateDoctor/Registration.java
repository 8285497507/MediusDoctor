package com.example.newdoctorsapp.models.CreateDoctor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("registrationCouncil")
    @Expose
    private String registrationCouncil;
    @SerializedName("registrationDate")
    @Expose
    private String registrationDate;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationCouncil() {
        return registrationCouncil;
    }

    public void setRegistrationCouncil(String registrationCouncil) {
        this.registrationCouncil = registrationCouncil;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

}