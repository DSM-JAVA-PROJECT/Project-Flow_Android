package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.feature.LoginResponse
import com.example.project_flow_android.feature.NewPasswordRequest
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

    fun addProject(request : AddProjectRequest
    ): @NonNull Single<Response<Void>> =
        providerFlowApi().addProject(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun finishProject(ProjectId : String
    ): @NonNull Single<Response<Void>> =
        providerFlowApi().finishProject(ProjectId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())



}