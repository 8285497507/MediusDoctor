package com.example.newdoctorsapp.models.doctoraddqual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocrorAddData {

    @SerializedName("qualificationName")
    @Expose
    private QualificationName qualificationName;
    @SerializedName("certificationOrganisation")
    @Expose
    private String certificationOrganisation;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public QualificationName getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(QualificationName qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getCertificationOrganisation() {
        return certificationOrganisation;
    }

    public void setCertificationOrganisation(String certificationOrganisation) {
        this.certificationOrganisation = certificationOrganisation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
