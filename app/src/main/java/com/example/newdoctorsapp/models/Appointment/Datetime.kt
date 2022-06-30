package com.example.newdoctorsapp.models.Appointment

import com.google.gson.annotations.SerializedName
import java.util.*

data class Datetime(
        @SerializedName("date")val date: Any?
)
data class HospitalidwithDate(
        @SerializedName("date")val date: Any?,
        @SerializedName("hospital")val hospital: String?
)