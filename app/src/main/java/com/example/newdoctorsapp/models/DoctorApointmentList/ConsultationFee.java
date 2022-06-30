
package com.example.newdoctorsapp.models.DoctorApointmentList;

import com.google.gson.annotations.SerializedName;

public class ConsultationFee {

    @SerializedName("max")
    private Long mMax;
    @SerializedName("min")
    private Long mMin;

    public Long getMax() {
        return mMax;
    }

    public void setMax(Long max) {
        mMax = max;
    }

    public Long getMin() {
        return mMin;
    }

    public void setMin(Long min) {
        mMin = min;
    }

}
