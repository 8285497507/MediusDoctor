package com.example.newdoctorsapp.models.DoctorApointmentList;
import com.google.gson.annotations.SerializedName;


public class Time {

    @SerializedName("date")
    private String mDate;
    @SerializedName("from")
    private From mFrom;
    @SerializedName("till")
    private Till mTill;
    @SerializedName("_id")
    private String m_id;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public From getFrom() {
        return mFrom;
    }

    public void setFrom(From from) {
        mFrom = from;
    }

    public Till getTill() {
        return mTill;
    }

    public void setTill(Till till) {
        mTill = till;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
