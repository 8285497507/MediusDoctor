
package com.example.newdoctorsapp.models.AppointmentHistoryModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("patient")
    @Expose
    private Patient patient;
    @SerializedName("doctors")
    @Expose
    private Doctors doctors;
    @SerializedName("hospital")
    @Expose
    private Hospital hospital;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("cancelled")
    @Expose
    private Boolean cancelled;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("rescheduled")
    @Expose
    private Boolean rescheduled;
    @SerializedName("appointmentToken")
    @Expose
    private Integer appointmentToken;
    @SerializedName("appointmentId")
    @Expose
    private String appointmentId;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("appointmentType")
    @Expose
    private String appointmentType;
    @SerializedName("specials")
    @Expose
    private List<Special> specials = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRescheduled() {
        return rescheduled;
    }

    public void setRescheduled(Boolean rescheduled) {
        this.rescheduled = rescheduled;
    }

    public Integer getAppointmentToken() {
        return appointmentToken;
    }

    public void setAppointmentToken(Integer appointmentToken) {
        this.appointmentToken = appointmentToken;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

}
