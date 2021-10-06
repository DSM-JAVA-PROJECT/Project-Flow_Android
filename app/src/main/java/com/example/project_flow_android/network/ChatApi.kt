package com.example.project_flow_android.network

import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ChatApi {
    @GET("/{projectId}/member")
    suspend fun getProjectUser(
        @Header("Authorization") header : String,
        @Path("projectId") projectId : String) : Response<ProjectMemberResponse>
}