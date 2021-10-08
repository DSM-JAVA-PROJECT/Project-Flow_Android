package com.example.project_flow_android.data.model.sign.chat

data class ProjectMemberResponse(
    val responses : ArrayList<User>
) {
    data class User(
        val name : String,
        val email : String,
        val profileImage: String
    )
}
