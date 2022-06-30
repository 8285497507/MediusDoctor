package com.example.newdoctorsapp.workspace.model.specialmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyPart {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("bodyPart")
    @Expose
    private String bodyPart;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
