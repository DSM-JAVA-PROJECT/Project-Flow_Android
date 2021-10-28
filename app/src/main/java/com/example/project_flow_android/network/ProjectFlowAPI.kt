package com.example.project_flow_android.network

import com.example.project_flow_android.feature.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*


interface ProjectFlowAPI {

    @POST("/auth/join")
    fun register(@Body request:RegisterRequest): Single<Response<Void>>

    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>

    @POST("/email/")
    fun postCertification(@Body request: CertificationRequest): Single<Response<Void>>

    @POST("/email/verifyCode")
    fun checkCertification(@Body request: PostCertificationRequest): Single<Response<Void>>

    @POST("/project")
    suspend fun addProject(@Body request: AddProjectRequest): Response<Void>

    @GET("/auth/myPage")
    suspend fun userInfo(@Header("Authorization") request: GetUserTokenRequest): Response<GetUserInfoResponse>



}