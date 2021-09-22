package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.*
import com.example.project_flow_android.network.ApiProvider
import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class SignApiImpl {

    private fun providerSignApi(): SignApi = ApiProvider.RetroFitBuilder.create(SignApi::class.java)

    fun loginApi(request: LoginRequest):@NonNull Single<Response<LoginResponse>> = providerSignApi().login(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun registerApi(request: RegisterRequest):@NonNull Single<Response<Void>> = providerSignApi().register(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun checkCertification(request: CertificationResponse):@NonNull Single<Response<Void>> = providerSignApi().checkCertification(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun postCertification(request: CertificationRequest):@NonNull Single<Response<Void>> = providerSignApi().postCertification(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())


}