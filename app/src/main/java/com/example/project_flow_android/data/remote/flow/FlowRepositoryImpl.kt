package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.SafeApiRequest
import retrofit2.Response

class FlowRepositoryImpl : FlowRepository, SafeApiRequest() {

    override suspend fun addProjectApi(request: AddProjectRequest): Response<Void> {
        return safeApiCall {ApiProvider.getAPI().addProject(request)}
    }

}