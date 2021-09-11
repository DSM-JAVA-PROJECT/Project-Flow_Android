package com.example.project_flow_android.network

import android.util.Log
import ua.naiksoftware.stomp.Stomp

class StompClient : Thread() {
    override fun run() {
        val url = "ws://211.38.86.92:8010/websocket"
        val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

        stompClient.connect()

        stompClient.topic("/topic/greetings").subscribe { topicMessage ->
            Log.d("ChatFragment",
                topicMessage.payload)
        }

        stompClient.send("/topic/hello-msg-mapping", "My first STOMP message!").subscribe();

        stompClient.disconnect()
    }
}