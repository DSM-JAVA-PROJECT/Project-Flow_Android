package com.example.project_flow_android.data.remote.sign

import com.example.project_flow_android.feature.*
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class SignApiImpl {

    private fun providerSignApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(ProjectFlowAPI::class.java)

    fun registerApi(request: RegisterRequest):@NonNull Single<Response<Void>> = providerSignApi().register(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun changePassword(request: NewPasswordRequest):@NonNull Single<Response<Void>> = providerSignApi().changePassword(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())


}