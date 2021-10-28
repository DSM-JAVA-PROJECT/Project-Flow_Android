package com.example.project_flow_android.data.remote.mypage

import com.example.project_flow_android.feature.GetUserInfoResponse
import com.example.project_flow_android.feature.GetUserTokenRequest
import retrofit2.Response

interface MyPageRepository {
    suspend fun getUserInfo(request : GetUserTokenRequest): Response<GetUserInfoResponse>
}