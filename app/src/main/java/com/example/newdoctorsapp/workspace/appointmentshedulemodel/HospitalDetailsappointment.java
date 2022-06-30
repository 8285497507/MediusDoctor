package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalDetailsappointment {
    @SerializedName("consultationFee")
    @Expose
    private consultantfeeAppointment consultationFee;

    public consultantfeeAppointment getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(consultantfeeAppointment consultationFee) {
        this.consultationFee = consultationFee;
    }
}
