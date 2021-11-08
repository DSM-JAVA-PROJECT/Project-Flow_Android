package com.example.project_flow_android.data.model.sign.chat

data class ChatMessageResponse(
    val oldChatMessageResponses : ArrayList<ChatReceiveResponse>
){
    data class ChatReceiveResponse(
        val id: String,
        val message: String,
        val senderName: String,
        val senderImage: String,
        val readerList: ArrayList<String>,
        val createdAt: String,
        val mine: Boolean
    )
}
