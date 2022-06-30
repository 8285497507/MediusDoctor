package com.example.newdoctorsapp.models.InviteHospoital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InviteHospitalResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private InviteHospitalData data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public InviteHospitalData getData() {
        return data;
    }

    public void setData(InviteHospitalData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
