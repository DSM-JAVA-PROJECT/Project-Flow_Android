package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.feature.CertificationRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FlowApi {

    @POST("/project")
    fun addProject(@Body request: AddProjectRequest): Single<Response<Void>>
}