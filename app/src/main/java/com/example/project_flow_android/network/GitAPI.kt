package com.example.project_flow_android.network

import com.example.project_flow_android.feature.GetGitProjectIssue
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GitAPI {

    @GET("/repos/DSM-JAVA-PROJECT/Project-Flow_Android/issues")

    fun getGitInfo(
        @Header("Authorization") token: String
    ): Single<Response<GetGitProjectIssue>>


}