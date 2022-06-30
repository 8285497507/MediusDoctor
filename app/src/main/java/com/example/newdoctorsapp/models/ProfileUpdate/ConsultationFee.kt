package com.example.newdoctorsapp.models.ProfileUpdate

import com.google.gson.annotations.SerializedName

data class ConsultationFee(
        @SerializedName("max") val max: Int,
        @SerializedName ("min")val min: Int
)