package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.SafeApiRequest
import retrofit2.Response

class ChatRepositoryImpl : ChatRepository, SafeApiRequest() {
    override suspend fun getProjectUser(projectId: String): Response<ProjectMemberResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getProjectUser(projectId) }
    }
}