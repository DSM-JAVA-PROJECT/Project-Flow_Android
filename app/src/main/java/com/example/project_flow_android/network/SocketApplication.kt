package com.example.project_flow_android.network

import android.util.Log
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
    private val access_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzQyODMzMjMsImlkIjoiNjE2N2JhNTQyNjdjYTEwZWI1NDkwNGE5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.Y_smWBnm1RrvToFW9kB9pHhnmgZIu0O73OZH4Cy3iZ4"
    private val sub_access = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzU5ODgxMjUsImlkIjoiNjE4MzMyOTU0MzliNGU1Y2VhMjNhNjg0IiwiZW1haWwiOiJhYmgwOTIwb25lQG5hdmVyLmNvbSJ9.gO6C_afNJvyQoKTC5CN-cvhZuZaQRC5dHg9ptssTBag"
    private lateinit var socket : Socket
    private var chatRoomId = ""
    private var projectId = "618333504aa95ded53f3b359"
    private var chatImage = ""
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

    fun chatReceive(){
        socket.on("message", onMassage)
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
    }
}