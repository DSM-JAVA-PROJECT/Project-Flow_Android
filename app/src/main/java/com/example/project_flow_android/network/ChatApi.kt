package com.example.project_flow_android.network

import com.example.project_flow_android.data.model.sign.chat.*
import okhttp3.MultipartBody
import retrofit2.Response
import com.example.project_flow_android.data.chat.ProjectMemberResponse
import com.example.project_flow_android.data.chat.RoomListResponse
import retrofit2.http.*

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

    @GET("/chatroom/profile/{userId}")
    suspend fun getUserProfile(
        @Header("Authorization") header: String,
        @Path("userId") userId: String
    ) : Response<UserProfileResponse>

    @PATCH("/chatroom/name/{chatRoomId}")
    suspend fun modifyRoomName(
        @Header("Authorization") header: String,
        @Path("chatRoomId") chatRoomId: String,
        @Body name: ModifyNameRequest
    ) : Response<Unit>

    @Multipart
    @POST("/file")
    suspend fun fileUpload(
        @Header("Authorization") header: String,
        @Part file: MultipartBody.Part
    ) : Response<FileResponse>

    @PATCH("/chatroom/image/{chatRoomId}")
    suspend fun imageUpdate(
        @Header("Authorization") header: String,
        @Path("chatRoomId") chatRoomId: String,
        @Body imageUrl: ImageUpdateRequest
    ) : Response<Unit>

    @GET("/project/{projectId}/member/{chatRoomId}")
    suspend fun getNonParticipate(
        @Header("Authorization") header: String,
        @Path("projectId") projectId: String,
        @Path("chatRoomId") chatRoomId: String
    ) : Response<NonParticipateResponse>

    @GET("/pin/{chatRoomId}")
    suspend fun getPin(
        @Header("Authorization") header: String,
        @Path("chatRoomId") chatRoomId: String
    ) : Response<GetPinResponse>

    @GET("/plan/calendar")
    suspend fun getMonthPlan(
        @Header("Authorization") header: String,
        @Query("id") project_id: String,
        @Query("year") year: String,
        @Query("month") month: String
    ) : Response<MonthPlanResponse>

    @GET("/plan/detail/")
    suspend fun getDatePlan(
        @Header("Authorization") header: String,
        @Query("id") project_id: String,
        @Query("date") date: String
    ) : Response<DatePlanResponse>
}