package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.GetUserInfoResponse
import com.example.project_flow_android.feature.GetUserTokenRequest
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.feature.LoginResponse
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MyPageApilmpl {
    private fun providerUserApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(
        ProjectFlowAPI::class.java)

    fun getUserInfo(request: GetUserTokenRequest):@NonNull Single<Response<GetUserInfoResponse>> = providerUserApi().userInfo(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

}