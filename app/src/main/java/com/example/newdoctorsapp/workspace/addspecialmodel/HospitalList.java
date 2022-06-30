package com.example.newdoctorsapp.workspace.addspecialmodel;

import com.example.newdoctorsapp.models.CreateDoctor.ConsultationFee;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalList {

    @SerializedName("consultationFee")
    @Expose
    private ConsultationFee consultationFee;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("_id")
    @Expose
    private String id;

    public ConsultationFee getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(ConsultationFee consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}