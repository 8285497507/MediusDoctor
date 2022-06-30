package com.example.newdoctorsapp.models.SearchAddHospitalModel

data class PaymentDetails(
    val IFSC: String,
    val PAN: String,
    val accountHolderName: String,
    val accountNumber: String,
    val bankName: String
)