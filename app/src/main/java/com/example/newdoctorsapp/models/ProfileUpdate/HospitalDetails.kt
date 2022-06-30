package com.example.newdoctorsapp.models.ProfileUpdate

import com.google.gson.annotations.SerializedName

data class HospitalDetails(
        @SerializedName("hospitalDetails")  val hospitalDetails: List<HospitalDetailX>
)