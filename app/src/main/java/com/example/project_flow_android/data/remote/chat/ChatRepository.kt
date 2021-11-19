package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.model.sign.chat.*
import retrofit2.Response

interface ChatRepository{
    suspend fun getProjectUser(header:String, projectId: String) : Response<ProjectMemberResponse>
    suspend fun getRoomList(header: String, projectId: String) : Response<RoomListResponse>
    suspend fun getRoomMember(header: String, chatRoomId: String) : Response<RoomMemberResponse>
    suspend fun getChatList(header: String, chatRoomId: String, page: Int, size: Int) : Response<ChatMessageResponse>
    suspend fun getUserProfile(header: String, userId: String) : Response<UserProfileResponse>
}