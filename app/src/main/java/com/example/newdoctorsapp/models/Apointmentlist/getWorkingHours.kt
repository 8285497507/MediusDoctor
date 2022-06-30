package com.example.newdoctorsapp.models.Apointmentlist

import com.google.gson.annotations.SerializedName

data class getWorkingHours(
        @SerializedName("doctorDetails") val doctorDetails: String,
        @SerializedName("hospitalDetails") val hospitalDetails: String
)