package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.feature.AddProjectRequest
import retrofit2.Response

interface FlowRepository {
    suspend fun addProjectApi(request: AddProjectRequest): Response<Void>
}