package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppoitmentData  {

    @SerializedName("workingHours")
    @Expose
    private List<AppointmentWorkingHour> workingHours = null;
    @SerializedName("prescriptionValidity")
    @Expose
    private PrescriptionValidity prescriptionValidity;

    @SerializedName("fee")
    @Expose
    private Fee fee;

    public List<AppointmentWorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<AppointmentWorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    public PrescriptionValidity getPrescriptionValidity() {
        return prescriptionValidity;
    }

    public void setPrescriptionValidity(PrescriptionValidity prescriptionValidity) {
        this.prescriptionValidity = prescriptionValidity;
    }
    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }
}
