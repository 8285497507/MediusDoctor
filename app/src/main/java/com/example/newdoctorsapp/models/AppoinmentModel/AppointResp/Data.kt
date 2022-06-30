package com.example.medius.models.AppoinmentModel.AppointResp

data class Data(
    val __v: Int,
    val _id: String,
    val byHospital: Boolean,
    val deleted: Deleted,
    val doctorDetails: String,
    val hospitalDetails: String
)