package com.example.newdoctorsapp.models.ProfileUpdate

data class Data(
    val __v: Int,
    val _id: String,
    val active: Boolean,
    val deleted: Boolean,
    val email: String,
    val firstName: String,
    val gender: String,
    val hospitalDetails: List<HospitalDetail>,
    val id: String,
    val image: String,
    val lastName: String,
    val overallExperience: String,
    val phoneNumber: String,
    val qualification: List<String>,
    val specialization: List<String>
)