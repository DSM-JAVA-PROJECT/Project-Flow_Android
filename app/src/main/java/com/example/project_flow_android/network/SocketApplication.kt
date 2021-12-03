package com.example.project_flow_android.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_flow_android.data.model.sign.chat.ChatErrorResponse
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import com.example.project_flow_android.data.model.sign.chat.PinResponse
import com.example.project_flow_android.data.model.sign.chat.ReJoinResponse
import com.example.project_flow_android.util.Event
import com.google.gson.Gson
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
    private val url = "http://3.80.121.3:8081"
    private val access_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2Mzc2NTAyODgsImlkIjoiNjE5YzhmNjk4ZDZlMjY3MzRiNTExY2M5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.kZkCt0TiXeWjT-zPwnDOENmLA3WB_NQg7yd4zAo2R1Q"
    //private val access_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzgxNzcwNzYsImV4cCI6MTYzODI2MzQ3NiwiaWQiOiI2MTljOTQzMzhkNmUyNjczNGI1MTFjY2QiLCJlbWFpbCI6InduZHVmMDQwNV9AbmF2ZXIuY29tIn0.ovevh3CFd9N7p1hK028bLnPdCPb45jJne7SL651XCu8"
    private val sub_access = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2Mzc2NTAzMDQsImlkIjoiNjE5YzhmNWU4ZDZlMjY3MzRiNTExY2M4IiwiZW1haWwiOiJhYmgwOTIwb25lQG5hdmVyLmNvbSJ9.chufW3OWC_lhzHeFQUDOjJA2b_Kx_01ls6_wgi0Etow"
    private lateinit var socket : Socket
    private var chatRoomId = ""
    private var projectId = "61a4d2e7b9d4a60b9a6a7ebc"
    //private var projectId = "61a4ecffb9d4a60b9a6a7ebe"
    private var chatImage = ""
    private var roomName = ""
    private val _receiveLiveData : MutableLiveData<Event<ChatMessageResponse.ChatReceiveResponse>> = MutableLiveData()
    private val _errorLiveData : MutableLiveData<Event<Int>> = MutableLiveData()
    private val _readerLiveData : MutableLiveData<Event<ReJoinResponse>> = MutableLiveData()
    private val _pinLiveData : MutableLiveData<Event<PinResponse>> = MutableLiveData()
    val receiveLiveData : LiveData<Event<ChatMessageResponse.ChatReceiveResponse>>
        get() = _receiveLiveData
    val errorLiveData : LiveData<Event<Int>>
        get() = _errorLiveData
    val pinLiveData : LiveData<Event<PinResponse>>
        get() = _pinLiveData

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
        data.put("message", message)
        data.put("chatRoomId", chatRoomId)
        socket.emit("chatroom.rejoin", data)
        socket.emit("message", data)
    }

    fun pin(chatId: String){
        val data = JSONObject()
        data.put("chatId", chatId)
        data.put("chatRoomId", chatRoomId)
        socket.emit("pin", data)
    }

    fun pinRemove(){
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        socket.emit("pin.remove", data)
    }

    fun chatReceive(){
        socket.on("message", onMassage)
        socket.on("pin", onPin)
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

    fun addPlan(planName: String, startDate: String, endDate: String, forced: Boolean){
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        data.put("planName", planName)
        data.put("planEndDate", endDate)
        data.put("planStartDate", startDate)
        data.put("isForced", forced)
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

    fun resignRoom(chatRoomId: String) {
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        socket.emit("chatroom.out", data)
    }

    fun inviteRoom(chatRoomId: String, users: ArrayList<String>) {
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        data.put("users", users)
        socket.emit("chatroom.participate")
    }

    fun leaveRoom(chatRoomId: String) {
        val data = JSONObject()
        data.put("chatRoomId", chatRoomId)
        socket.emit("chatroom.resign", data)
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
        _receiveLiveData.postValue(Event(message))
    }

    private val onPin = Emitter.Listener { args ->
        Log.i("Pin payload", args[0].toString())
        val json = args[0].toString()
        val pin = Gson().fromJson(json, PinResponse::class.java)
        _pinLiveData.postValue(Event(pin))
    }

    private val onAddPlan = Emitter.Listener { args ->
        Log.i("Plan payload", args[0].toString())
        val json = args[0].toString()
        val plan = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(Event(plan))
    }

    private val onJoinPlan = Emitter.Listener { args ->
        Log.i("JoinPlan payload", args[0].toString())
        val json = args[0].toString()
        val joinPlan = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(Event(joinPlan))
    }

    private val onResignPlan = Emitter.Listener { args ->
        Log.i("ResignPlan payload", args[0].toString())
        val json = args[0].toString()
        val resignPlan = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(Event(resignPlan))
    }

    private val onError = Emitter.Listener { args ->
        Log.i("Error payload", args[0].toString())
        val json = args[0].toString()
        val error = Gson().fromJson(json, ChatErrorResponse::class.java)
        _errorLiveData.postValue(Event(error.status))
    }

    private val onReJoin = Emitter.Listener { args ->
        Log.i("Rejoin payload", args[0].toString())
        val json = args[0].toString()
        val reader = Gson().fromJson(json, ReJoinResponse::class.java)
        _readerLiveData.postValue(Event(reader))
    }

    private val inviteRoom = Emitter.Listener { args ->
        Log.i("Invite payload", args[0].toString())
        val json = args[0].toString()
        val invite = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(Event(invite))
    }

    private val onLeave = Emitter.Listener { args ->
        Log.i("Leave payload", args[0].toString())
        val json = args[0].toString()
        val leave = Gson().fromJson(json, ChatMessageResponse.ChatReceiveResponse::class.java)
        _receiveLiveData.postValue(Event(leave))
    }
}