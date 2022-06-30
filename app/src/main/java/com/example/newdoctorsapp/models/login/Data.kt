package com.example.newdoctorsapp.models.login

data class Data(
    val _id: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val phoneNumber: String,
    val qualification: Qualification,
    val token: String,
    val verified: Boolean
)