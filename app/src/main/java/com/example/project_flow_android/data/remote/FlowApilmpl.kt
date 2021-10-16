package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class FlowApilmpl {

    private fun providerFlowApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(ProjectFlowAPI::class.java)

    fun addProjectApi(request: AddProjectRequest):@NonNull Single<Response<Void>> = providerFlowApi().addProject(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())


}