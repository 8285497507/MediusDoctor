package com.example.newdoctorsapp.models.ConsulatantModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultantData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("orderId")
    @Expose
    private ConsultantOrderId orderId;
    @SerializedName("orderReceipt")
    @Expose
    private String orderReceipt;
    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("appointmentId")
    @Expose
    private String appointmentId;
    @SerializedName("paymentSignature")
    @Expose
    private String paymentSignature;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConsultantOrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(ConsultantOrderId orderId) {
        this.orderId = orderId;
    }

    public String getOrderReceipt() {
        return orderReceipt;
    }

    public void setOrderReceipt(String orderReceipt) {
        this.orderReceipt = orderReceipt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPaymentSignature() {
        return paymentSignature;
    }

    public void setPaymentSignature(String paymentSignature) {
        this.paymentSignature = paymentSignature;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
