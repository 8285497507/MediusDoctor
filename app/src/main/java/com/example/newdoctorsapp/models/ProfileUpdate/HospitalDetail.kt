package com.example.newdoctorsapp.models.ProfileUpdate

import com.google.gson.annotations.SerializedName

data class HospitalDetail(
        @SerializedName ("_id")val _id: String,
        @SerializedName ("consultationFee") val consultationFee: ConsultationFee,
        @SerializedName ("hospital") val hospital: String
)