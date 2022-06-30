package com.example.newdoctorsapp.models.DoctorApointmentList;


import com.google.gson.annotations.SerializedName;


public class HospitalDetail {

    @SerializedName("consultationFee")
    private ConsultationFee mConsultationFee;
    @SerializedName("hospital")
    private String mHospital;
    @SerializedName("_id")
    private String m_id;

    public ConsultationFee getConsultationFee() {
        return mConsultationFee;
    }

    public void setConsultationFee(ConsultationFee consultationFee) {
        mConsultationFee = consultationFee;
    }

    public String getHospital() {
        return mHospital;
    }

    public void setHospital(String hospital) {
        mHospital = hospital;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
