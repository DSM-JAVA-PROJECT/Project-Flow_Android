package com.example.project_flow_android.data.model.sign.chat

data class ChatListResponse(val chatListResponse: ArrayList<ChatContentResponse>) {
    data class ChatContentResponse(
        val id: String,
        val type: String,
        val message: String,
        val createdAt: String,
        val planTitle: String,
        val planId: String,
        val readers: List<String>,
        val isParticipated: Boolean,
        val chatRoomId: String,
        val isMine: Boolean
    )
}
