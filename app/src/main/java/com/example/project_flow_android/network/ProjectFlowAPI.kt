package com.example.project_flow_android.network

import com.example.project_flow_android.feature.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*
import java.io.File


interface ProjectFlowAPI {

    @POST("/auth/join")
    fun register(@Body request:RegisterRequest): Single<Response<Void>>

    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>

    @POST("/email/")
    fun postCertification(@Body request: CertificationRequest): Single<Response<Void>>

    @POST("/email/verifyCode")
    fun checkCertification(@Body request: PostCertificationRequest): Single<Response<Void>>

    @GET("/auth/myPage")
    fun getuserInfo(@Header("Authorization") token: String): Single<Response<GetUserInfoResponse>>

    @PATCH("/auth/password")
    fun changePassword(@Header("Authorization") token :String,@Body password : NewPasswordRequest) : Single<Response<Void>>

    @Multipart
    @PATCH("/auth/image")
    fun changeProfileImage(@Header("Authorization") token: String,@Part ("file") file : File) : Single<Response<Void>>

    @GET("/main")
    fun getMainInfo(@Header("Authorization") token: String) : Single<Response<GetUserInfoResponse>>

    @POST("/project")
    fun addProject(@Body request: AddProjectRequest) : Single<Response<Void>>

    @PATCH("/project/close/{id}")
    fun finishProject(@Body projectId : String) : Single<Response<Void>>





}