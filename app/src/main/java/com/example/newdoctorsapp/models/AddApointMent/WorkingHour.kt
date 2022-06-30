package com.example.newdoctorsapp.models.AddApointMent

import com.google.gson.annotations.SerializedName

data class WorkingHour(
        @SerializedName("monday") val monday: Monday
)

data class WorkingHourTu(
        @SerializedName("tuesday") val tuesday: Tuesday
)

data class WorkingHourwe(
        @SerializedName("wednesday") val wednesday: Wednesday
)

data class WorkingHourThu(
        @SerializedName("thursday") val thursday: Thursday
)

data class WorkingHourfri(
        @SerializedName("friday") val friday: Friday
)

data class WorkingHourSut(
        @SerializedName("saturday") val saturday: Saturday
)

data class WorkingHourSun(
        @SerializedName("sunday") val sunday: Sunday
)