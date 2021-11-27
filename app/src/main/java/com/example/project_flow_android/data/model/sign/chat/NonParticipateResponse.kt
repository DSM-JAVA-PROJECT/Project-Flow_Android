package com.example.project_flow_android.data.model.sign.chat

data class NonParticipateResponse(
    val responses: ArrayList<NonUserList>
){
    data class NonUserList(
        val name: String,
        val email: String,
        val profileImage: String
    )
}
