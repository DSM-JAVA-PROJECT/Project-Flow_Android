package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.*
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SignApi {
    @POST("/auth/join")
    fun register(@Body request:RegisterRequest): Single<Response<Void>>

    @POST("/auth")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>

    @POST("/email/")
    fun postCertification(@Body request: CertificationRequest): Single<Response<Void>>

    @POST("/email/verifyCode")
    fun checkCertification(@Body request: CertificationResponse): Single<Response<Void>>




}