package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.data.remote.toMultipartPart
import com.example.project_flow_android.feature.*
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import java.io.File

class FlowApiImpl {
    private fun providerFlowApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(
        ProjectFlowAPI::class.java)

    fun finishProject(
        token: String,
        ProjectId: String,
    ): @NonNull Single<Response<Void>> =
        providerFlowApi().finishProject(token, ProjectId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getMainInfo(
        token: String,
    ): @NonNull Single<Response<GetMainInfoResponse>> =
        providerFlowApi().getMainInfo(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun finishPlan(
        token: String,
        ProjectId: String,
        PlanId: String,
    ): @NonNull Single<Response<Void>> =
        providerFlowApi().finishPlan(token, ProjectId, PlanId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun gitOauth(): @NonNull Single<Response<GitToken>> =
        providerFlowApi().gitOauth()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun addProject2(
        token: String,
        request: AddProjectRequest,
    ): @NonNull Single<Response<GetProjectsId>> =
        providerFlowApi().addProject2(token, request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun postImage(file: File): @NonNull Single<Response<image>> =
        providerFlowApi().postImage(file.toMultipartPart())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}