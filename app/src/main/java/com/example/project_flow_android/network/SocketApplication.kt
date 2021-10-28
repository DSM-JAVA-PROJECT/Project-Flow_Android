package com.example.project_flow_android.network

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
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
    private val access_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzQyODMzMjMsImlkIjoiNjE2N2JhNTQyNjdjYTEwZWI1NDkwNGE5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.Y_smWBnm1RrvToFW9kB9pHhnmgZIu0O73OZH4Cy3iZ4"
    private lateinit var socket : Socket
    private var chatRoomId = ""
    private var projectId = "616c247724ffea704e117e6e"
    fun connect(){
        Thread {
            try {
                val options = IO.Options()
                options.transports = arrayOf(WebSocket.NAME)
                socket = IO.socket(url, options)
                socket.emit("/socket.io", access_token)
            } catch (e : Exception){
                e.printStackTrace()
            }

            socket.connect()
            socket.on(Socket.EVENT_CONNECT) {
                Log.i("Socket", "Connect")
                Log.d("Socket Connected - Thread", socket.connected().toString())
            }.on(Socket.EVENT_DISCONNECT) { args ->
                Log.i("Socket", "Disconnect: ${args[0]}")
            }
        }.start()
    }

    fun createRoom(emails: ArrayList<String>){
        Log.d("Socket Connected - CreateRoom", socket.connected().toString())
        val data = JSONObject()
        data.put("emails", emails)
        data.put("projectId", projectId)
        socket.emit("chatroom.create", data)
    }

    fun setChatRoomId(chatRoomId: String){
        this.chatRoomId = chatRoomId
    }

    fun setProjectId(projectId: String){
        this.projectId = projectId
    }

    fun getProjectId() = projectId

    fun getChatRoomId() = chatRoomId
}