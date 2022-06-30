package com.example.newdoctorsapp.models.ProfileUpdate

import com.google.gson.annotations.SerializedName

data class UpdateQulifaction(
    @SerializedName("qualification")val qualification: List<String>
)