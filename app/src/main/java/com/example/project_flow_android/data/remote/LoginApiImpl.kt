package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.*
import com.example.project_flow_android.network.ApiProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class LoginApiImpl {

    private fun providerSignApi(): LoginApi = ApiProvider.RetroFitBuilder.create(LoginApi::class.java)

    fun loginApi(request: LoginRequest):@NonNull Single<Response<LoginResponse>> = providerSignApi().login(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())



}