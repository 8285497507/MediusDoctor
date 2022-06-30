package com.example.newdoctorsapp.models.DoctorApointmentList;


import com.google.gson.annotations.SerializedName;


public class Till {

    @SerializedName("division")
    private Long mDivision;
    @SerializedName("time")
    private Long mTime;
    @SerializedName("_id")
    private String m_id;

    public Long getDivision() {
        return mDivision;
    }

    public void setDivision(Long division) {
        mDivision = division;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
