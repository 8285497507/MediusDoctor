package com.example.newdoctorsapp.workspace.model.specialmodel;

import com.example.newdoctorsapp.models.SpecialityBodyPartAndDisease.Disease;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data  {

    @SerializedName("Speciality")
    @Expose
    private List<Speciality> speciality = null;
    @SerializedName("BodyPart")
    @Expose
    private List<BodyPart> bodyPart = null;
    @SerializedName("Disease")
    @Expose
    private List<Disease> disease = null;

    public List<Speciality> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(List<Speciality> speciality) {
        this.speciality = speciality;
    }

    public List<BodyPart> getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(List<BodyPart> bodyPart) {
        this.bodyPart = bodyPart;
    }

    public List<Disease> getDisease() {
        return disease;
    }

    public void setDisease(List<Disease> disease) {
        this.disease = disease;
    }

}
