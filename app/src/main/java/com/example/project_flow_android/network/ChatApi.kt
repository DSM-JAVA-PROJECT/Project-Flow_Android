package com.example.project_flow_android.network

import com.example.project_flow_android.data.model.sign.chat.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {
    @GET("/project/{projectId}/member")
    suspend fun getProjectUser(
        @Header("Authorization") header : String,
        @Path("projectId") projectId : String) : Response<ProjectMemberResponse>

    @GET("/chatroom/{projectId}/rooms")
    suspend fun getRoomList(
        @Header("Authorization") header: String,
        @Path("projectId") projectId: String) : Response<RoomListResponse>

    @GET("/chatroom/{chatRoomId}/members")
    suspend fun getRoomMember(
        @Header("Authorization") header: String,
        @Path("chatRoomId") chatRoomId: String
    ) : Response<RoomMemberResponse>

    @GET("/chat/{chatRoomId}")
    suspend fun getChatList(
        @Header("Authorization") header: String,
        @Path("chatRoomId") chatRoomId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Response<ChatMessageResponse>

    @GET("/chatroom/profile/{userId")
    suspend fun getUserProfile(
        @Header("Authorization") header: String,
        @Path("userId") userId: String
    ) : Response<UserProfileResponse>
}