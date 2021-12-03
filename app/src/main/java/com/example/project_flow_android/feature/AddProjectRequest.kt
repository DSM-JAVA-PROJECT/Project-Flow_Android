package com.example.project_flow_android.feature

import java.io.File

data class AddProjectRequest(
    val projectName: String,
    val explanation: String,
    val startDate: String,
    val endDate: String,
    val file  : File,
    val emails: List<String>
)
