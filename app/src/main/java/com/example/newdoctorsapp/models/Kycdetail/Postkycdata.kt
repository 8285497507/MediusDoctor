package com.example.newdoctorsapp.models.Kycdetail

import com.google.gson.annotations.SerializedName

data class Postkycdata(
        @SerializedName("IFSC")val IFSC: String,
        @SerializedName("bankAccountNumber")val bankAccountNumber: String,
        @SerializedName("bankName") val bankName: String,
        @SerializedName("panCard")val panCard: String

)
data class Updatekycdata(
        @SerializedName("id")val id: String,
        @SerializedName("IFSC")val IFSC: String,
        @SerializedName("adhaarCard")val adhaarCard: String,
        @SerializedName("bankAccountNumber")val bankAccountNumber: String,
        @SerializedName("bankName") val bankName: String,
        @SerializedName("panCard")val panCard: String

)