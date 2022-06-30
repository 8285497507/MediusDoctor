package com.example.newdoctorsapp.models.addHospitalModel

data class address(

    val `city`: List<city>,
    val `state`: List<state>,
    val `locality`: List<locality>,
    val _id: String,
    val country: String,
    val addressLine_1: String,
    val __v: String,

)
