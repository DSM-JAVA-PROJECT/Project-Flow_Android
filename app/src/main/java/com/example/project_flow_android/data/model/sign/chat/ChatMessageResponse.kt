package com.example.project_flow_android.data.model.sign.chat

data class ChatMessageResponse(
    val oldChatMessageResponses : ArrayList<ChatReceiveResponse>,
    val size : Int,
    val hasNextPage: Boolean
){
    data class ChatReceiveResponse(
        val type: String,
        val id: String,
        val planId: String?,
        val planName: String?,
        val startDate: String?,
        val endDate: String?,
        val message: String?,
        val senderName: String,
        val senderImage: String,
        val readerList: ArrayList<String>,
        val createdAt: String,
        val mine: Boolean,
    )
}
