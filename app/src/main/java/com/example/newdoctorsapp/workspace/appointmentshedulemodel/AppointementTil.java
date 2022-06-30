package com.example.newdoctorsapp.workspace.appointmentshedulemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointementTil
{

        @SerializedName("time")
        @Expose
        private Integer time;
        @SerializedName("division")
        @Expose
        private Integer division;

        public Integer getTime() {
        return time;
    }

        public void setTime(Integer time) {
        this.time = time;
    }

        public Integer getDivision() {
        return division;
    }

        public void setDivision(Integer division) {
        this.division = division;
    }
}
