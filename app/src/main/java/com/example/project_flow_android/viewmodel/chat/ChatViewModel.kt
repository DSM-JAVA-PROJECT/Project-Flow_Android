package com.example.project_flow_android.viewmodel.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import kotlinx.coroutines.launch
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

class ChatViewModel : ViewModel() {
    private val TAG = "StompClient"
    private val URL = "ws://54.180.224.67:8080/websocket"
    private val access_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzM1MTA5ODMsImlkIjoiNjE1ZDNlMDJlMjAxY2MyNTk4ZTVlZDllIiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.v4-p1mdDS3fvk56TEKoS1KbCj7FFMclGihC3abYcICY"
    private val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL)
    private var chatRoomId = 0
    private var projectId = "615d3e2ae201cc2598e5ed9f"

    private val chatRepository = ChatRepositoryImpl()
    private val _chatLiveData : MutableLiveData<ProjectMemberResponse> = MutableLiveData()
    val chatLiveData = _chatLiveData

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

    fun getProjectUser(){
        viewModelScope.launch {
            val response = chatRepository.getProjectUser(access_token ,projectId)
            if(response.isSuccessful){
                if(response.code() == 200){
                    _chatLiveData.postValue(response.body())
                }
            }
        }
    }

    fun setChatRoomId(chatRoomId: Int){
        this.chatRoomId = chatRoomId
    }

    fun setProjectId(projectId: String){
        this.projectId = projectId
    }
}