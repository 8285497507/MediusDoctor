package com.example.newdoctorsapp.models.Submitfeedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFeedbackData {

    @SerializedName("feedback")
    @Expose
    private String feedback;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("InModel")
    @Expose
    private String inModel;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInModel() {
        return inModel;
    }

    public void setInModel(String inModel) {
        this.inModel = inModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
