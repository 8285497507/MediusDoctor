package com.example.newdoctorsapp.models.ProfileUpdate

import com.google.gson.annotations.SerializedName

data class basicdetailupdate(
        @SerializedName("DOB") val DOB: String,
        @SerializedName("_id") val _id: String,
        @SerializedName("active") val active: Boolean,
        @SerializedName("deleted") val deleted: Boolean,
        @SerializedName("firstName") val firstName: String,
        @SerializedName("gender")  val gender: String,
        @SerializedName("lastName") val lastName: String
)