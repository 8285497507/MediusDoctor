package com.example.newdoctorsapp.models.login

import com.google.gson.annotations.SerializedName

data class UserVerifaction(
  @SerializedName("phoneNumber")  val phoneNumber: String
)