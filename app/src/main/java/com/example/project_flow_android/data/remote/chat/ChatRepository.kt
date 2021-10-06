package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import retrofit2.Response

interface ChatRepository{
    suspend fun getProjectUser(projectId: String) : Response<ProjectMemberResponse>
}