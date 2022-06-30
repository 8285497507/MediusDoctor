package com.example.newdoctorsapp.models.DeleteProfile

import com.google.gson.annotations.SerializedName

data class DeleteQulifaction(
        @SerializedName("qualification") val qualification: List<String>
)

data class DeleteSpeclization(
        @SerializedName("specialization") val specialization: List<String>
)

data class DeleteHospital(
        @SerializedName("hospital") val hospital: String
)
