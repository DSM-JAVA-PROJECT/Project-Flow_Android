package com.example.project_flow_android.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.project_flow_android.data.model.sign.chat.ChatErrorResponse
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
    private var projectId = "6194967186cfc21756269e3c"
    private var chatImage = ""
    private var roomName = ""
    private val _receiveLiveData : MutableLiveData<ChatMessageResponse.ChatReceiveResponse> = MutableLiveData()
    private val _errorLiveData : MutableLiveData<Int> = MutableLiveData()
    private val _readLiveData : MutableLiveData<String> = MutableLiveData()
    val receiveLiveData = _receiveLiveData
    val errorLiveData = _errorLiveData
    val readLiveData = _readLiveData

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
        socket.on("plan.create", onAddPlan)
        socket.on("plan.join", onJoinPlan)
        socket.on("plan.resign", onResignPlan)
        socket.on("rejoin", onReJoin)
        socket.on("error", onError)
    }

    fun rejoin(){
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        socket.emit("chatroom.rejoin", data)
    }

    fun addPlan(planName: String, startDate: String, endDate: String){
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        data.put("planName", planName)
        data.put("planEndDate", endDate)
        data.put("planStartDate", startDate)
        socket.emit("plan.create", data)
    }

    fun joinPlan(planId: String) {
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        data.put("planId", planId)
        socket.emit("plan.join", data)
    }

    fun resignPlan(planId: String) {
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        data.put("planId", planId)
        socket.emit("plan.resign", data)
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

    fun setRoomName(roomName: String){
        this.roomName = roomName
    }

    fun getProjectId() = projectId

    fun getChatRoomId() = chatRoomId

    fun getChatImage() = chatImage

    fun getRoomName() = roomName

    private val onMassage = Emitter.Listener { args ->
        Log.i("Message payload", args[0].toString())
        val json = args[0].toString()
        val message = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(message)
    }

    private val onAddPlan = Emitter.Listener { args ->
        Log.i("Plan payload", args[0].toString())
        val json = args[0].toString()
        val plan = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(plan)
    }

    private val onJoinPlan = Emitter.Listener { args ->
        Log.i("JoinPlan payload", args[0].toString())
        val json = args[0].toString()
        val joinPlan = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(joinPlan)
    }

    private val onResignPlan = Emitter.Listener { args ->
        Log.i("ResignPlan payload", args[0].toString())
        val json = args[0].toString()
        val resignPlan = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(resignPlan)
    }

    private val onError = Emitter.Listener { args ->
        Log.i("Error payload", args[0].toString())
        val json = args[0].toString()
        val error = Gson().fromJson(json, ChatErrorResponse::class.java)
        _errorLiveData.postValue(error.status)
    }

    private val onReJoin = Emitter.Listener { args ->
        Log.i("Rejoin payload", args[0].toString())
        val json = args[0].toString()
        readLiveData.postValue(json)
    }
}