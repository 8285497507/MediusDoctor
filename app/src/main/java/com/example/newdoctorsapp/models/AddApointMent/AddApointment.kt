package com.example.newdoctorsapp.models.AddApointMent
import com.google.gson.annotations.SerializedName

data class AddApointment(
        @SerializedName("hospitalId") val hospitalId : String,
        @SerializedName("workingHour") val workingHour : Any)
