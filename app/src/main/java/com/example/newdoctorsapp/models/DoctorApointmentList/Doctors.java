
package com.example.newdoctorsapp.models.DoctorApointmentList;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Doctors {

    @SerializedName("active")
    private Boolean mActive;
    @SerializedName("deleted")
    private Boolean mDeleted;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("hospitalDetails")
    private List<HospitalDetail> mHospitalDetails;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("overallExperience")
    private String mOverallExperience;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("qualification")
    private List<String> mQualification;
    @SerializedName("specialization")
    private List<String> mSpecialization;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public Boolean getDeleted() {
        return mDeleted;
    }

    public void setDeleted(Boolean deleted) {
        mDeleted = deleted;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public List<HospitalDetail> getHospitalDetails() {
        return mHospitalDetails;
    }

    public void setHospitalDetails(List<HospitalDetail> hospitalDetails) {
        mHospitalDetails = hospitalDetails;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getOverallExperience() {
        return mOverallExperience;
    }

    public void setOverallExperience(String overallExperience) {
        mOverallExperience = overallExperience;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public List<String> getQualification() {
        return mQualification;
    }

    public void setQualification(List<String> qualification) {
        mQualification = qualification;
    }

    public List<String> getSpecialization() {
        return mSpecialization;
    }

    public void setSpecialization(List<String> specialization) {
        mSpecialization = specialization;
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
