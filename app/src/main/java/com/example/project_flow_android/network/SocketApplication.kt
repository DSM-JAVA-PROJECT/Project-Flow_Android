package com.example.project_flow_android.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import com.example.project_flow_android.data.model.sign.chat.ChatReceiveResponse
import com.google.gson.Gson
import com.google.gson.JsonParser
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.transports.WebSocket
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

class SocketApplication {

    companion object{
        private var socketApplication : SocketApplication? = null
        fun getSocket() : SocketApplication {
            if(socketApplication == null){
                socketApplication = SocketApplication()
            }
            return socketApplication!!
        }
    }
    private val url = "http://54.89.202.59:8081"
    private val access_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzY0MzQyNDYsImlkIjoiNjE4OWZlOTcwYzliZmQyYjk4MDRmZjg2IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.6cNSlsTiL4UG4arInBRPaJjV4MeemeXmDiMZiDxXKVQ"
    private val sub_access = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzY0MzQyNjMsImlkIjoiNjE4OWZlYTMwYzliZmQyYjk4MDRmZjg3IiwiZW1haWwiOiJhYmgwOTIwb25lQG5hdmVyLmNvbSJ9.lklPsE4KpZRqSxi5EYahxxTeXigL47eYxbE3UL7ZtMY"
    private lateinit var socket : Socket
    private var chatRoomId = ""
    private var projectId = "618a002c0c9bfd2b9804ff8a"
    private var chatImage = ""
    private val _receiveLiveData : MutableLiveData<ChatMessageResponse.ChatReceiveResponse> = MutableLiveData()
    val receiveLiveData = _receiveLiveData

    fun connect(){
        Thread {
            try {
                val options = IO.Options()
                options.transports = arrayOf(WebSocket.NAME)
                options.query = "Authorization=$access_token"
                socket = IO.socket(url, options)
            } catch (e : Exception){
                e.printStackTrace()
            }

            socket.connect()
            socket.on(Socket.EVENT_CONNECT) {
                Log.i("Socket", "Connect")
                Log.d("Socket Connected - Thread", socket.connected().toString())
            }.on(Socket.EVENT_DISCONNECT) { args ->
                Log.i("Socket", "Disconnect: ${args[0]}")
            }.on(Socket.EVENT_CONNECT_ERROR) { args ->
                Log.i("Socket connecting error", "${args[0]}")
            }.on(Socket.EVENT_ERROR) {args ->
                Log.i("Socket error", "${args[0]}")
            }
        }.start()
    }

    fun createRoom(users: ArrayList<String>){
        Log.d("Socket Connected - CreateRoom", socket.connected().toString())
        val data = JSONObject()
        val list = JSONArray(users)
        data.put("userIds", list)
        data.put("projectId", projectId)
        socket.emit("chatroom.create", data)
    }

    fun send(message: String){
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        data.put("message", message)
        socket.emit("message", data)
    }

    fun chatReceive(){
        socket.on("message", onMassage)
    }

    fun rejoin(){
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        socket.emit("chatroom.rejoin", data)
    }

    fun setChatRoomId(chatRoomId: String){
        this.chatRoomId = chatRoomId
    }

    fun setProjectId(projectId: String){
        this.projectId = projectId
    }

    fun setChatImage(chatImage: String){
        this.chatImage = chatImage
    }

    fun getProjectId() = projectId

    fun getChatRoomId() = chatRoomId

    fun getChatImage() = chatImage

    private val onMassage = Emitter.Listener { args ->
        Log.i("Message payload", args[0].toString())
        val json = args[0].toString()
        val message = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(message)
    }
}