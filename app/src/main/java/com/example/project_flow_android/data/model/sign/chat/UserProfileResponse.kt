package com.example.project_flow_android.data.model.sign.chat

data class UserProfileResponse(
    val userName: String,
    val userEmail: String,
    val phoneNumber: String,
    val profileImage: String,
    val projectResponse: ArrayList<ProjectResponse>
){
    data class ProjectResponse(
        val projectImage: String,
        val projectName: String,
        val projectStartDate: String,
        val projectEndDate: String,
        val finished: Boolean
    )
}
