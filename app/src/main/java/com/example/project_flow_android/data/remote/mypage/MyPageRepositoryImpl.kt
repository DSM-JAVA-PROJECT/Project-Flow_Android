package com.example.project_flow_android.data.remote.mypage

import com.example.project_flow_android.feature.GetUserInfoResponse
import com.example.project_flow_android.feature.GetUserTokenRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.SafeApiRequest
import retrofit2.Response

class MyPageRepositoryImpl : MyPageRepository,SafeApiRequest() {

    override suspend fun getUserInfo(request: GetUserTokenRequest): Response<GetUserInfoResponse> {
        return safeApiCall { ApiProvider.getAPI().userInfo(request) }
    }


}