package com.example.project_flow_android.network

import com.example.project_flow_android.feature.CertificationRequest
import com.example.project_flow_android.feature.PostCertificationRequest
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.feature.RegisterRequest
import retrofit2.Call
import retrofit2.http.*


interface ProjectFlowAPI {

    //Register
    @POST("/auth/join")
    fun doRegister(@Body request:RegisterRequest):Call<Unit>

    //Login
    @POST("/auth/login")
    fun doLogin(@Body request:LoginRequest):Call<Unit>

    //인증코드 전송
    @POST("/email")
    fun doCertification(@Body request:CertificationRequest):Call<Unit>

    //인증 코드 확인
    @POST("/email/verifyCode")
    fun checkCertification(@Body request:PostCertificationRequest):Call<Unit>





}