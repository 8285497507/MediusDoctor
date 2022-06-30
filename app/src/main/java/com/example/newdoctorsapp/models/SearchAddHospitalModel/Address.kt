package com.example.newdoctorsapp.models.SearchAddHospitalModel

data class Address(
    val __v: Int,
    val _id: String,
    val addressLine_1: String,
    val city: City,
    val country: Any,
    val locality: Locality,
    val pincode: String,
    val state: State
)