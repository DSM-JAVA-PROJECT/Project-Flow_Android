package com.example.project_flow_android.network

import ua.naiksoftware.stomp.Stomp

class StompClient : Thread() {
    override fun run() {
        val url = "ws://54.180.224.67:8080/websocket"
        val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

        stompClient.connect()

//        stompClient.topic("/topic/greetings").subscribe { topicMessage ->
//            Log.d("ChatFragment",
//                topicMessage.payload)
//        }
//
//        stompClient.send("/", "My first STOMP message!").subscribe();
//
//        stompClient.disconnect()
    }
}