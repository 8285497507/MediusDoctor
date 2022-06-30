package com.example.newdoctorsapp.models.doctoraddqual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Del {
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
