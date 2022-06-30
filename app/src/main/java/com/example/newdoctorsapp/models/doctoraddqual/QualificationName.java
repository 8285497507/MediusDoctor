package com.example.newdoctorsapp.models.doctoraddqual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QualificationName {
    @SerializedName("del")
    @Expose
    private Del del;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Del getDel() {
        return del;
    }

    public void setDel(Del del) {
        this.del = del;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
