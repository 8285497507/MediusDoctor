
package com.example.newdoctorsapp.models.NotificationModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender {

    @SerializedName("registrationDetails")
    @Expose
    private RegistrationDetails registrationDetails;
    @SerializedName("paymentDetails")
    @Expose
    private PaymentDetails paymentDetails;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("modeOfAppointments")
    @Expose
    private List<Object> modeOfAppointments = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public RegistrationDetails getRegistrationDetails() {
        return registrationDetails;
    }

    public void setRegistrationDetails(RegistrationDetails registrationDetails) {
        this.registrationDetails = registrationDetails;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Object> getModeOfAppointments() {
        return modeOfAppointments;
    }

    public void setModeOfAppointments(List<Object> modeOfAppointments) {
        this.modeOfAppointments = modeOfAppointments;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
