package com.example.project_flow_android.network

import com.example.project_flow_android.feature.GetGitProjectIssue
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GitAPI {

    @GET("https://api.github.com/")

    fun getGitInfo(
        @Header("Authorization") token: String
    ): Single<Response<GetGitProjectIssue>>


}