package com.example.newdoctorsapp.models.Appointment

data class AppointmentList(
    val `data`: List<Data>,
    val message: String,
    val status: Int
)