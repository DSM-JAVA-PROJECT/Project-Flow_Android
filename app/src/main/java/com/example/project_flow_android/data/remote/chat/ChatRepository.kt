package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.model.sign.chat.*
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Response

interface ChatRepository{
    suspend fun getProjectUser(header:String, projectId: String) : Response<ProjectMemberResponse>
    suspend fun getRoomList(header: String, projectId: String) : Response<RoomListResponse>
    suspend fun getRoomMember(header: String, chatRoomId: String) : Response<RoomMemberResponse>
    suspend fun getChatList(header: String, chatRoomId: String, page: Int, size: Int) : Response<ChatMessageResponse>
    suspend fun getUserProfile(header: String, userId: String) : Response<UserProfileResponse>
    suspend fun modifyRoomName(header: String, chatRoomId: String, name: ModifyNameRequest) : Response<Unit>
    suspend fun fileUpload(header: String, file: MultipartBody.Part) : Response<FileResponse>
    suspend fun imageUpdate(header: String, imageUrl: ImageUpdateRequest) : Response<Unit>
}