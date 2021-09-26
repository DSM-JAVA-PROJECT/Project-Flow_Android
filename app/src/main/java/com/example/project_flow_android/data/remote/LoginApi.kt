package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.feature.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/auth")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>
}