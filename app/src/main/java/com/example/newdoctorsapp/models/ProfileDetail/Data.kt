package com.example.newdoctorsapp.models.ProfileDetail

data class Data(
    val DOB: String,
    val KYCDetails: KYCDetails,
    val __v: Int,
    val _id: String,
    val active: Boolean,
    val deleted: Boolean,
    val email: String,
    val firstName: String,
    val gender: String,
    val hospitalDetails: List<HospitalDetail>,
    val lastName: String,
    val overallExperience: String,
    val phoneNumber: String,
    val qualification: List<Qualification>,
    val registration: Registration,
    val specialization: List<Specialization>
)