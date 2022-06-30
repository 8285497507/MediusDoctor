package com.example.newdoctorsapp.models.ProfileUpdate

import com.example.newdoctorsapp.models.CreateDoctor.ConsultationFee
import com.google.gson.annotations.SerializedName

data class HospitalDetailX(
        @SerializedName("consultationFee")   val consultationFee: ConsultationFee,
        @SerializedName("hospital")   val hospital: String,
)