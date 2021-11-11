package com.example.project_flow_android.feature

data class GetMainInfoDetailResponse(
    val name: String,
    val startDate: String,
    val endDate: String,
    val personalProgress: Int,
    val projectProgress: Int,
)
