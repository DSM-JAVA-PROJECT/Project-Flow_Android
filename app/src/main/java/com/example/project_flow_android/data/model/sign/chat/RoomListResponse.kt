package com.example.project_flow_android.data.model.sign.chat

data class RoomListResponse(val responses: ArrayList<ChatRoomResponse>) {
    data class ChatRoomResponse(
        val chatRoomName: String,
        val id: String,
        val chatRoomImage: String
    )
}
