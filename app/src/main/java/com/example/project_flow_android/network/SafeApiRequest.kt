package com.example.project_flow_android.network

import retrofit2.Response
import java.lang.Exception

abstract class SafeApiRequest {
    suspend fun <T : Any> safeApiCall(result: suspend () -> Response<T>): Response<T> {
        val response = result.invoke()
        return try {
            if(response.isSuccessful){
                Response.success(response.body())
            } else {
                Response.error(response.code(), response.errorBody()!!)
            }
        } catch (e : Exception){
            e.printStackTrace()
            Response.error(response.code(), response.errorBody()!!)
        }
    }
}