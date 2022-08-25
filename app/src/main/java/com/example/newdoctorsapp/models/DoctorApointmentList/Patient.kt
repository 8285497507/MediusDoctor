package com.example.newdoctorsapp.models.DoctorApointmentList

data class Patient(
    val DOB: String,
    val __v: Int,
    val _id: String,
    val active: Boolean,
    val age: String,
    val deleted: Boolean,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val location: LocationX,
    val phoneNumber: String
)