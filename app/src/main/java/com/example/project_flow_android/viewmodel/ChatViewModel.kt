package com.example.project_flow_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

class ChatViewModel : ViewModel() {
    private val TAG = "StompClient"
    private val URL = "ws://54.180.224.67:8080/websocket"
    private val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL)
    private var chatRoomId = 0

    fun stompConnect(){
        Thread {
            val headerList = arrayListOf<StompHeader>()
            headerList.add(StompHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzI2MzY1MzUsImV4cCI6MTYzMjYzNzEzNSwiaWQiOnsidGltZXN0YW1wIjoxNjMyMzYxNjgzLCJkYXRlIjoxNjMyMzYxNjgzMDAwfSwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.oYbKcXFA0z25gJJUMPM_86nSybVQqzLgbv8cG-SVbPk"))

            stompClient.lifecycle().subscribe{
                when(it.type){
                    LifecycleEvent.Type.OPENED -> Log.d(TAG, "stomp connection opened")
                    LifecycleEvent.Type.ERROR -> Log.d(TAG, "stomp connection error")
                    LifecycleEvent.Type.CLOSED -> Log.d(TAG, "stomp connection closed")
                    LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> Log.d(TAG, "stomp failed server heartbeat")
                }
            }
            stompClient.connect(headerList)
        }
    }

    fun send(message: String){
        val data = JSONObject()
        data.put("message", message)
        stompClient.send("/chat/$chatRoomId/send", data.toString()).subscribe()
    }

    fun setChatRoomId(chatRoomId: Int){
        this.chatRoomId = chatRoomId
    }
}