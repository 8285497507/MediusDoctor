package com.example.newdoctorsapp.models.CreateDoctor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDoctorResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private CreateDocorData data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CreateDocorData getData() {
        return data;
    }

    public void setData(CreateDocorData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
