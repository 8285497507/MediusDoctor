package com.example.newdoctorsapp.models.ProfileDetail

import com.google.gson.annotations.SerializedName

data class Registration(
        @SerializedName("_id") val _id: String,
        @SerializedName("registrationCouncil")  val registrationCouncil: String,
        @SerializedName("registrationDate") val registrationDate: String,
        @SerializedName("registrationNumber") val registrationNumber: String
)