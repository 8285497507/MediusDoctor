package com.example.newdoctorsapp.models.SearchAddHospitalModel

import com.google.gson.annotations.SerializedName

data class City(
    val __v: Int,
    val _id: String,
    @SerializedName("city-id")
    val city_id: String,
    val city_state: String,
    val name: String,
    val url: String
)