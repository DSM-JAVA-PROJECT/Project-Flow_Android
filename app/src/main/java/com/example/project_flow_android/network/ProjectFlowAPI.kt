package com.example.project_flow_android.network

import android.telecom.Call
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.feature.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProjectFlowAPI {

    //Register
    @POST("/auth/join")
    fun doRegister(@Body request:RegisterRequest):retrofit2.Call<Unit>

    //Login
    @POST("/auth/login")
    fun doLogin(@Body request:LoginRequest):retrofit2.Call<Unit>




}