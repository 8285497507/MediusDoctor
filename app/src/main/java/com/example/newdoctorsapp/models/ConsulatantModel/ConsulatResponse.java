package com.example.newdoctorsapp.models.ConsulatantModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsulatResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private ConsultantData data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ConsultantData getData() {
        return data;
    }

    public void setData(ConsultantData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
