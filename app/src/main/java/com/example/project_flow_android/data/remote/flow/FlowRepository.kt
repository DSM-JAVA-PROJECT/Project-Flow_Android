package com.example.project_flow_android.data.remote.flow

import com.example.project_flow_android.data.model.sign.chat.FileResponse
import com.example.project_flow_android.feature.GetProjectsId
import okhttp3.MultipartBody
import retrofit2.Response

interface FlowRepository {
    suspend fun fileUpload(token: String, projectName: String,explanation:String,startDate : String,endDate : String,file: MultipartBody.Part, emails : Array<String>) : Response<GetProjectsId>
}