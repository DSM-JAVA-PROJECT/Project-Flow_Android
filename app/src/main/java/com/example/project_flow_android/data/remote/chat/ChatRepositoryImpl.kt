package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import com.example.project_flow_android.data.model.sign.chat.RoomListResponse
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.SafeApiRequest
import retrofit2.Response

class ChatRepositoryImpl : ChatRepository, SafeApiRequest() {
    override suspend fun getProjectUser(header: String, projectId: String): Response<ProjectMemberResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getProjectUser(header, projectId) }
    }

    override suspend fun getRoomList(
        header: String,
        projectId: String,
    ): Response<RoomListResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getRoomList(header, projectId) }
    }
}