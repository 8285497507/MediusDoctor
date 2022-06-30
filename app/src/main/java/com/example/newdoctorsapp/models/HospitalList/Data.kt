package com.example.newdoctorsapp.models.HospitalList

data class Data(
    val __v: Int,
    val _id: String,
    val address: Address,
    val anemity: List<Any>,
    val contactNumber: String,
    val deleted: Boolean,
    val doctors: List<Any>,
    val name: String,
    val numberOfBed: Int,
    val payment: List<Any>,
    val specialisedIn: List<Any>,
    val treatmentType: List<Any>,
    val type: String,
   var   checked: Boolean
)