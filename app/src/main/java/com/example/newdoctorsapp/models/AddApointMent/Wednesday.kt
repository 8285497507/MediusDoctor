package com.example.newdoctorsapp.models.AddApointMent

import com.google.gson.annotations.SerializedName

data class Wednesday(
        @SerializedName("working") val working : Boolean,
        @SerializedName("from") val from : From,
        @SerializedName("till") val till : Till,
        @SerializedName("capacity") val capacity : Int
)