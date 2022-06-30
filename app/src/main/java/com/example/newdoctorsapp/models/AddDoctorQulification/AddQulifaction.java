package com.example.newdoctorsapp.models.AddDoctorQulification;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddQulifaction {

    @SerializedName("qualificationName")
    @Expose
    private String qualificationName;
    @SerializedName("certificationOrganisation")
    @Expose
    private String certificationOrganisation;
//    @SerializedName("duration")
//    @Expose
//    private adDuration duration;
//    @SerializedName("email")
//    @Expose
//    private String email;

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getCertificationOrganisation() {
        return certificationOrganisation;
    }

    public void setCertificationOrganisation(String certificationOrganisation) {
        this.certificationOrganisation = certificationOrganisation;
    }

//    public adDuration getDuration() {
//        return duration;
//    }
//
//    public void setDuration(adDuration duration) {
//        this.duration = duration;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }


}
