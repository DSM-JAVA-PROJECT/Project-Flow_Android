package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import com.example.project_flow_android.data.model.sign.chat.RoomListResponse
import retrofit2.Response

interface ChatRepository{
    suspend fun getProjectUser(header:String, projectId: String) : Response<ProjectMemberResponse>
    suspend fun getRoomList(header: String, projectId: String) : Response<RoomListResponse>
}