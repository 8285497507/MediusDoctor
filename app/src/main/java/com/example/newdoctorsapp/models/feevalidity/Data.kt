package com.example.newdoctorsapp.models.feevalidity

data class Data(
    val consultationFee: ConsultationFee,
    val prescription: List<Prescription>
)