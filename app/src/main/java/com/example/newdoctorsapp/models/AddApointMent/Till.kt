package com.example.newdoctorsapp.models.AddApointMent

import com.google.gson.annotations.SerializedName

data class Till(
        @SerializedName("time") val time : Int,
        @SerializedName("division") val division : Int
)