package com.example.newdoctorsapp.models.Submitfeedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFeedbackResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private SubmitFeedbackData data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SubmitFeedbackData getData() {
        return data;
    }

    public void setData(SubmitFeedbackData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
