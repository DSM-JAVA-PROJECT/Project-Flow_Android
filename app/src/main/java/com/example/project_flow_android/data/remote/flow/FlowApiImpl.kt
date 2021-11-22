package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.feature.*
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

    fun addProject(token : String, request : AddProjectRequest
    ): @NonNull Single<Response<GetProjectsId>> =
        providerFlowApi().addProject(token,request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun finishProject(ProjectId : String
    ): @NonNull Single<Response<Void>> =
        providerFlowApi().finishProject(ProjectId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())



}