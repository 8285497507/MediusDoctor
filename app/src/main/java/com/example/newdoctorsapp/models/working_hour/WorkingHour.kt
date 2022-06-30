package com.example.newdoctorsapp.models.working_hour

data class WorkingHour(
    val Days: List<Day>,
    val from: From,
    val till: Till
)