
package com.example.newdoctorsapp.models.DoctorApointmentList;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("cancelled")
    private Boolean mCancelled;
    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("doctors")
    private Doctors mDoctors;
    @SerializedName("done")
    private Boolean mDone;
    @SerializedName("hospital")
    private Hospital mHospital;
    @SerializedName("patient")
    private Patient mPatient;
    @SerializedName("rescheduled")
    private Boolean mRescheduled;
    @SerializedName("time")
    private Time mTime;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public Boolean getCancelled() {
        return mCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        mCancelled = cancelled;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Doctors getDoctors() {
        return mDoctors;
    }

    public void setDoctors(Doctors doctors) {
        mDoctors = doctors;
    }

    public Boolean getDone() {
        return mDone;
    }

    public void setDone(Boolean done) {
        mDone = done;
    }

    public Hospital getHospital() {
        return mHospital;
    }

    public void setHospital(Hospital hospital) {
        mHospital = hospital;
    }

    public Patient getPatient() {
        return mPatient;
    }

    public void setPatient(Patient patient) {
        mPatient = patient;
    }

    public Boolean getRescheduled() {
        return mRescheduled;
    }

    public void setRescheduled(Boolean rescheduled) {
        mRescheduled = rescheduled;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public Long get_V() {
        return m_V;
    }

    public void set_V(Long _V) {
        m_V = _V;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
