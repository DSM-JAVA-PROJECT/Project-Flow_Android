package com.example.project_flow_android.data.model.sign.chat

data class RoomMemberResponse(
    val responses : ArrayList<MemberResponse>
) {
    data class MemberResponse(
        val name: String,
        val id: String,
        val profileImage: String
    )
}