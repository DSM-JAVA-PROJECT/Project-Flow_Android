package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.data.remote.toMultipartPart
import com.example.project_flow_android.feature.*
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import java.io.File

class FlowApiImpl {
    private fun providerFlowApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(
        ProjectFlowAPI::class.java)

    fun addProject(
        token: String, request: AddProjectRequest
    ): @NonNull Single<Response<GetProjectsId>> =
        providerFlowApi().addProject(token, request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

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

    fun addProjectQuery(
        token: String,
        projectName: String,
        explanation: String,
        startDate: String,
        endDate: String,
        file: File,
        emails: Array<String>,
    ): @NonNull Single<Response<Void>> =
        providerFlowApi().addProjectQuery(token,
            projectName,
            explanation,
            startDate,
            endDate,
            file.toMultipartPart(),
            emails)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun gitOauth(): @NonNull Single<Response<GitToken>> =
        providerFlowApi().gitOauth()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun addProjectPart(
        token: String, projectName: String,
        explanation: String,
        startDate: String,
        endDate: String,
        file: File,
        emails: Array<String>,
    ): @NonNull Single<Response<GetProjectsId>> =
        providerFlowApi().addProjectPart(token,
            projectName,
            explanation,
            startDate,
            endDate,
            file.toMultipartPart(),
            emails)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}