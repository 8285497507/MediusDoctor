package com.example.newdoctorsapp.models.ProfileUpdate

import com.example.newdoctorsapp.models.CreateDoctor.Registration
import com.google.gson.annotations.SerializedName

data class UpDateRegistationDetail(
    @SerializedName("registration")val registration: Registration
)