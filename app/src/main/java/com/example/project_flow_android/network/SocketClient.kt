package com.example.project_flow_android.network

import android.util.Log
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.net.Socket

class SocketClient : Thread() {
    override fun run() {
        super.run()
        val host = ""
        val port = 5000

        try {
            val socket = Socket(host, port)
            val outStream = ObjectOutputStream(socket.getOutputStream())
            outStream.writeObject("Hello!")
            outStream.flush()
            Log.d("ClientStream", "Sent to server.")

            val inputStream = ObjectInputStream(socket.getInputStream())
            val input = inputStream.readObject()
            Log.d("ClientThread", "Received data: $input");
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}