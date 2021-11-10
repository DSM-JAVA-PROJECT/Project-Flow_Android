package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.feature.GetUserInfoResponse
import com.example.project_flow_android.feature.ImageRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class FlowApiImpl {

    private fun providerFlowApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(
        ProjectFlowAPI::class.java)

    fun getUserInfo(token: String): @NonNull Single<Response<GetUserInfoResponse>> =
        providerFlowApi().getuserInfo(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun addProject(token: String, request: AddProjectRequest, image : ImageRequest): @NonNull Single<Response<Void>> =
        providerFlowApi().addProject(token, request,image)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


}