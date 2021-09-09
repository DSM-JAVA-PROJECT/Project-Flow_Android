package com.example.project_flow_android.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*

import kotlinx.coroutines.runBlocking
import java.util.*

class SocketClient : Thread() {
    override fun run() {
        val client = HttpClient(CIO) {
            install(WebSockets)
        }
        runBlocking {
            client.webSocket(method = HttpMethod.Get, host = "54.180.224.67", port = 8080, path = "/websocket"){
                while (true){
                    val othersMessage = incoming.receive() as? Frame.Text
                    println(othersMessage?.readText())
                    val myMessage = Scanner(System.`in`).next()
                    if(myMessage != null) {
                        send(myMessage)
                    }
                }
            }
        }
        client.close()
    }
}