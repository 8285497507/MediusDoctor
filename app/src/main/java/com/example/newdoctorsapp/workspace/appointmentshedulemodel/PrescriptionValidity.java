package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescriptionValidity {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("validateTill")
    @Expose
    private Integer validateTill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValidateTill() {
        return validateTill;
    }

    public void setValidateTill(Integer validateTill) {
        this.validateTill = validateTill;
    }
}
