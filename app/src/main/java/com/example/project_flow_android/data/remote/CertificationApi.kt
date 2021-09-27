package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.CertificationRequest
import com.example.project_flow_android.feature.CertificationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CertificationApi {

    @POST("/email/")
    fun postCertification(@Body request: CertificationRequest): Single<Response<Void>>

    @POST("/email/verifyCode")
    fun checkCertification(@Body request: CertificationResponse): Single<Response<Void>>



}