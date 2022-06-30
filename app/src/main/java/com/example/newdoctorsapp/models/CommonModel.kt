package com.example.newdoctorsapp.models

import com.google.gson.annotations.SerializedName

class CommonModel {
    @SerializedName("id")
    var id : String ?=null
    @SerializedName("hospitalId")
    var hospitalId : String ?=null
    @SerializedName("doctorId")
    var doctorId : String ?=null
}