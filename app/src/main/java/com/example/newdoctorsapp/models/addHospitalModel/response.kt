package com.example.newdoctorsapp.models.addHospitalModel

import com.example.newdoctorsapp.models.Appointment.Data

data class response(
    val `addhospitaldata`: List<addhospitaldata>,
    val message: String,
    val status: Int
)
