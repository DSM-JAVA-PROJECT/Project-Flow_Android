package com.example.project_flow_android.data.remote.chat

import com.example.project_flow_android.data.chat.ProjectMemberResponse
import com.example.project_flow_android.data.chat.RoomListResponse
import com.example.project_flow_android.data.model.sign.chat.*
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.SafeApiRequest
import okhttp3.MultipartBody
import org.json.JSONObject
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

    override suspend fun getRoomMember(
        header: String,
        chatRoomId: String,
    ): Response<RoomMemberResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getRoomMember(header, chatRoomId) }
    }

    override suspend fun getChatList(
        header: String,
        chatRoomId: String,
        page: Int,
        size: Int,
    ): Response<ChatMessageResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getChatList(header, chatRoomId, page, size) }
    }

    override suspend fun getUserProfile(
        header: String,
        userId: String,
    ): Response<UserProfileResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getUserProfile(header, userId) }
    }

    override suspend fun modifyRoomName(
        header: String,
        chatRoomId: String,
        name: ModifyNameRequest,
    ): Response<Unit> {
        return safeApiCall { ApiProvider.getChatAPI().modifyRoomName(header, chatRoomId, name) }
    }

    override suspend fun fileUpload(
        header: String,
        file: MultipartBody.Part,
    ): Response<FileResponse> {
        return safeApiCall { ApiProvider.getChatAPI().fileUpload(header, file) }
    }

    override suspend fun imageUpdate(header: String, chatRoomId: String, imageUrl: ImageUpdateRequest): Response<Unit> {
        return safeApiCall { ApiProvider.getChatAPI().imageUpdate(header, chatRoomId, imageUrl) }
    }

    override suspend fun getNonParticipate(
        header: String,
        projectId: String,
        chatRoomId: String,
    ): Response<NonParticipateResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getNonParticipate(header, projectId, chatRoomId) }
    }

    override suspend fun getPin(header: String, chatRoomId: String): Response<GetPinResponse> {
        return safeApiCall { ApiProvider.getChatAPI().getPin(header, chatRoomId) }
    }

    override suspend fun getMonthPlan(
        header: String,
        project_id: String,
        year: String,
        month: String,
    ): Response<MonthPlanResponse> {
        return safeApiCall { ApiProvider.getScheduleAPI().getMonthPlan(header, project_id, year, month) }
    }

    override suspend fun getDatePlan(
        header: String,
        project_id: String,
        date: String,
    ): Response<DatePlanResponse> {
        return safeApiCall { ApiProvider.getScheduleAPI().getDatePlan(header, project_id, date) }
    }

    override suspend fun deletePlan(header: String, planId: String): Response<Unit> {
        return safeApiCall { ApiProvider.getChatAPI().deletePlan(header, planId) }
    }

    override suspend fun resignPlan(
        header: String,
        chatRoomId: String,
        planId: String,
    ): Response<Unit> {
        return safeApiCall { ApiProvider.getChatAPI().resignPlan(header, chatRoomId, planId) }
    }
}