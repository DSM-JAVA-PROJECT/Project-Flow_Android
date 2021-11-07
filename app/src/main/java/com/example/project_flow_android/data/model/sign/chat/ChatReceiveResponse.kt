package com.example.project_flow_android.data.model.sign.chat

data class ChatReceiveResponse(
    val id: String,
    val message: String,
    val senderName: String,
    val readerList: ArrayList<String>,
    val createdAt: String
)