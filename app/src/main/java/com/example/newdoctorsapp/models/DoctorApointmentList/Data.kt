package com.example.newdoctorsapp.models.DoctorApointmentList

data class Data(
    val __v: Int,
    val _id: String,
    val appointmentId: String,
    val appointmentToken: Int,
    val cancelled: Boolean,
    val createdAt: String,
    val doctors: Doctors,
    val done: Boolean,
    val hospital: Hospital,
    val patient: Patient,
    val rescheduled: Boolean,
    val subPatient: SubPatient,
    val time: Time
)