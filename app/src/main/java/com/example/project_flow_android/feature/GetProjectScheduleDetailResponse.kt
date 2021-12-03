package com.example.project_flow_android.feature

data class GetProjectScheduleDetailResponse(
    val planId: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val mainPageUsers: List<String>,
)
