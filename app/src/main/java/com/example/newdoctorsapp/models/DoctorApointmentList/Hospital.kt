package com.example.newdoctorsapp.models.DoctorApointmentList

data class Hospital(
    val __v: Int,
    val _id: String,
    val address: String,
    val anemity: List<String>,
    val contactNumber: String,
    val deleted: Boolean,
    val doctors: List<String>,
    val location: Location,
    val modeOfAppointments: List<Any>,
    val name: String,
    val password: String,
    val payment: List<Any>,
    val paymentDetails: PaymentDetails,
    val registrationDetails: RegistrationDetails,
    val services: List<String>,
    val specialisedIn: List<Any>,
    val treatmentType: List<Any>,
    val type: String,
    val verified: Boolean
)