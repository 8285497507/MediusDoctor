package com.example.newdoctorsapp.models.CreateDoctor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalDetail2 {
    @SerializedName("hospital")
    @Expose
    private String hospital;

    @SerializedName("consultationFee")
    @Expose
    private ConsultationFee consultationFee;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }



    public ConsultationFee getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(ConsultationFee consultationFee) {
        this.consultationFee = consultationFee;
    }
}
