package com.example.newdoctorsapp.models.DoctorApointmentList

data class SubPatient(
    val DOB: String,
    val __v: Int,
    val _id: String,
    val deleted: Boolean,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val parentPatient: String
)