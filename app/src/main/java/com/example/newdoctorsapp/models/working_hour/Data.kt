package com.example.newdoctorsapp.models.working_hour

data class Data(
    val fee: Fee,
    val prescriptionValidity: PrescriptionValidity,
    val workingHours: List<WorkingHour>
)