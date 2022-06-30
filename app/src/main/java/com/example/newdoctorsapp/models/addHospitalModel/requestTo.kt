package com.example.newdoctorsapp.models.addHospitalModel

data class requestTo(
    val _id: String,
    val name: String,
    val `address`: List<address>,
)
