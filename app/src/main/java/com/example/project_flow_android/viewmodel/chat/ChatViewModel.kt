package com.example.project_flow_android.viewmodel.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

class ChatViewModel : ViewModel() {
    private val TAG = "StompClient"
    private val URL = "ws://54.180.224.67:8080/wxebsocket"
    private val access_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzM1MDA2NzcsImV4cCI6MTYzMzUwMTI3NywiaWQiOiI2MTVkM2UwMmUyMDFjYzI1OThlNWVkOWUiLCJlbWFpbCI6ImFiaDA5MjBvbmVAZ21haWwuY29tIn0.s-V-tDVXGaJ31AN060iP5zMtfEYTFKM4BW5lpvXjGYc"
    private val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL)
    private var chatRoomId = 0
    private var projectId = "615c083a91bc3016ebf4f5af"

    fun connect(){
        Thread {
            val headerList = arrayListOf<StompHeader>()
            headerList.add(StompHeader("Authorization", access_token))

            stompClient.lifecycle().subscribe{
                when(it.type){
                    LifecycleEvent.Type.OPENED -> Log.d(TAG, "stomp connection opened")
                    LifecycleEvent.Type.ERROR -> Log.d(TAG, "stomp connection error")
                    LifecycleEvent.Type.CLOSED -> Log.d(TAG, "stomp connection closed")
                    LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> Log.d(TAG,
                        "stomp failed server heartbeat")
                }
            }

            stompClient.connect(headerList)
        }.start()
    }

    fun send(message: String){
        val data = JSONObject()
        data.put("message", message)
        stompClient.send("/chat/$chatRoomId/send", data.toString()).subscribe()
    }

    @SuppressLint("CheckResult")
    fun subscribe(){
        stompClient.topic("/chat/$chatRoomId/send").subscribe{
            Log.i(TAG, it.payload)
        }
    }

    @SuppressLint("CheckResult")
    fun createRoom(){
        stompClient.send("/create/chatroom/$projectId").subscribe()
    }

    fun disconnect(){
        stompClient.disconnect()
    }

    fun setChatRoomId(chatRoomId: Int){
        this.chatRoomId = chatRoomId
    }

    fun setProjectId(projectId: String){
        this.projectId = projectId
    }
}