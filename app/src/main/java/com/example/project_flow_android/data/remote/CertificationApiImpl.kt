package com.example.project_flow_android.data.remote

import com.example.project_flow_android.feature.CertificationRequest
import com.example.project_flow_android.feature.CertificationResponse
import com.example.project_flow_android.network.ApiProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class CertificationApiImpl {
    private fun providerSignApi(): CertificationApi = ApiProvider.RetroFitBuilder.create(CertificationApi::class.java)

    fun checkCertification(request: CertificationResponse): @NonNull Single<Response<Void>> =
        providerSignApi().checkCertification(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun postCertification(request: CertificationRequest): @NonNull Single<Response<Void>> =
        providerSignApi().postCertification(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}