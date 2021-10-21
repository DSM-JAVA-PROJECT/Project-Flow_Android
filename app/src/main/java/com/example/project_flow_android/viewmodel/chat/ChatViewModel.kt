package com.example.project_flow_android.viewmodel.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import com.example.project_flow_android.data.model.sign.chat.RoomListResponse
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.launch
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp

class ChatViewModel : ViewModel() {
    private val TAG = "StompClient"
    private val URL = "http://ca7c-223-39-206-60.ngrok.io"
    private val access_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzQyODMzMjMsImlkIjoiNjE2N2JhNTQyNjdjYTEwZWI1NDkwNGE5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.Y_smWBnm1RrvToFW9kB9pHhnmgZIu0O73OZH4Cy3iZ4"
    private val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL)
    private lateinit var socket : Socket
    private var chatRoomId = 0
    private var projectId = "616c247724ffea704e117e6e"

    private val chatRepository = ChatRepositoryImpl()
    private val _chatLiveData : MutableLiveData<ProjectMemberResponse> = MutableLiveData()
    private val _chatRoomLiveData : MutableLiveData<RoomListResponse> = MutableLiveData()
    val chatLiveData = _chatLiveData
    val chatRoomLiveData = _chatRoomLiveData

    fun connect(){
        Thread {
            try {
                socket = IO.socket(URL)
            } catch (e: Exception){
                e.printStackTrace()
            }
//            val headerList = ArrayList<StompHeader>()
//            headerList.add(StompHeader("Authorization", access_token))
//
//            stompClient.lifecycle().subscribe{
//                when(it.type){
//                    LifecycleEvent.Type.OPENED -> Log.d(TAG, "stomp connection opened")
//                    LifecycleEvent.Type.ERROR -> Log.d(TAG, "stomp connection error")
//                    LifecycleEvent.Type.CLOSED -> Log.d(TAG, "stomp connection closed")
//                    LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> Log.d(TAG, "stomp failed server heartbeat")
//                }
//            }
//            stompClient.connect(headerList)
            socket.connect()
            socket.on(Socket.EVENT_CONNECT) {
                Log.i("Socket", "Connect")
            }.on(Socket.EVENT_DISCONNECT) { args ->
                Log.i("Socket", "Disconnet: ${args[0]}")
            }
        }.start()
    }

    fun send(message: String){
        val data = JSONObject()
        data.put("message", message)
        stompClient.send("/chat/$chatRoomId/send", data.toString()).subscribe()
    }

    @SuppressLint("CheckResult")
    fun subscribe(destinationPath: String){
        stompClient.topic(destinationPath).subscribe({
            Log.i(TAG, it.payload)
        }) {throwable -> Log.e(TAG,"Error on subscribe topic", throwable)}
    }

    fun createRoom(emails : ArrayList<String>){
        val data = JSONObject()
        data.put("emails", emails)
        stompClient.send("/create/chatroom/$projectId", data.toString()).subscribe()
    }

    fun disconnect(){
        stompClient.disconnect()
    }

    fun getProjectUser(){
        viewModelScope.launch {
            val response = chatRepository.getProjectUser(access_token, projectId)
            if(response.isSuccessful){
                if(response.code() == 200){
                    _chatLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getRoomList(){
        viewModelScope.launch {
            val response = chatRepository.getRoomList(access_token, projectId)
            if(response.isSuccessful){
                if(response.code() == 200){
                    _chatRoomLiveData.postValue(response.body())
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

    fun getProjectId() = projectId
}