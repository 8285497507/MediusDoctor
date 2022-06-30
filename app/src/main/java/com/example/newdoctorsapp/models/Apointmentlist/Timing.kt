package com.example.newdoctorsapp.models.Apointmentlist

data class Timing(
    val _id: String,
    val capacity: Int,
    val from: From,
    val till: Till,
    val working: Boolean,
    val workingHour: String
)