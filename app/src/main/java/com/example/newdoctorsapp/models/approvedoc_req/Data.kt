package com.example.medius.models.approvedoc_req

data class Data(
    val __v: Int,
    val _id: String,
    val approvalStatus: String,
    val createdAt: String,
    val delData: DelData,
    val ref_From: String,
    val ref_To: String,
    val requestFrom: String,
    val requestTo: String
)