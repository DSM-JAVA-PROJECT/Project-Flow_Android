package com.example.project_flow_android.feature

data class Projects(
    val projectName: String,
    val logoImage: String,
    val startDate: String,
    val endDate: String,
    val finished: Boolean,
    val remainingDays: String,
    val personalProgress : String,
    val projectProgress: String
)
