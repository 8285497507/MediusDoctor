package com.example.newdoctorsapp.models.HospitalApprovalList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalApprovalData {

    @SerializedName("delData")
    @Expose
    private HospitalDelData delData;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("ref_From")
    @Expose
    private String refFrom;
    @SerializedName("requestTo")
    @Expose
    private RequestTo requestTo;
    @SerializedName("ref_To")
    @Expose
    private String refTo;
    @SerializedName("approvalStatus")
    @Expose
    private String approvalStatus;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("requestFrom")
    @Expose
    private RequestFrom requestFrom;

    public HospitalDelData getDelData() {
        return delData;
    }

    public void setDelData(HospitalDelData delData) {
        this.delData = delData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefFrom() {
        return refFrom;
    }

    public void setRefFrom(String refFrom) {
        this.refFrom = refFrom;
    }

    public RequestTo getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(RequestTo requestTo) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public RequestFrom getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(RequestFrom requestFrom) {
        this.requestFrom = requestFrom;
    }
}