package com.example.newdoctorsapp.models.InviteHospoital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InviteHospitalData {
    @SerializedName("requestFrom")
    @Expose
    private String requestFrom;
    @SerializedName("ref_From")
    @Expose
    private String refFrom;
    @SerializedName("requestTo")
    @Expose
    private String requestTo;
    @SerializedName("ref_To")
    @Expose
    private String refTo;
    @SerializedName("approvalStatus")
    @Expose
    private String approvalStatus;
    @SerializedName("delData")
    @Expose
    private HospitalDelData delData;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRefFrom() {
        return refFrom;
    }

    public void setRefFrom(String refFrom) {
        this.refFrom = refFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public String getRefTo() {
        return refTo;
    }

    public void setRefTo(String refTo) {
        this.refTo = refTo;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public HospitalDelData getDelData() {
        return delData;
    }

    public void setDelData(HospitalDelData delData) {
        this.delData = delData;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
