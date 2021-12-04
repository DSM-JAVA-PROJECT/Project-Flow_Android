package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.feature.GetProjectsId
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.SafeApiRequest
import okhttp3.MultipartBody
import retrofit2.Response

class FlowRepositoryImpl:FlowRepository,SafeApiRequest() {

    override suspend fun fileUpload(
        token: String,
        projectName: String,
        explanation: String,
        startDate: String,
        endDate: String,
        file: MultipartBody.Part,
        emails: Array<String>,
    ): Response<GetProjectsId> {
        return safeApiCall { ApiProvider.getAPI().addProjectSuspend(token,projectName, explanation, startDate, endDate, file, emails) }
    }
}