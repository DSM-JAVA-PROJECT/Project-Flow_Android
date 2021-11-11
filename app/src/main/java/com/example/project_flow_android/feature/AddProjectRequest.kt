package com.example.project_flow_android.feature

data class AddProjectRequest(
    val projectName: String,
    val explanation: String,
    val startDate: String,
    val endDate: String,
    val emails: List<Any>
)
