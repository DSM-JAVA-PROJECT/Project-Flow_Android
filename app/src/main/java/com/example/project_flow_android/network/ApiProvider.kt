package com.example.project_flow_android.network

import android.app.TaskStackBuilder.create
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiProvider {
    private const val BASE_URL: String = "http://18.210.203.222:8080/"
    private const val BASE_URL_CHAT = "http://3.36.224.130:8080"
    private const val CONNECT_TIME_OUT: Long = 15
    private const val WRITE_TIME_OUT: Long = 15
    private const val READ_TIME_OUT: Long = 15
    private var chatRetrofitBuilder : Retrofit
    private var retrofirBuilder : Retrofit
    private var chatApi : ChatApi
    private var Api : ProjectFlowAPI

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder().apply {
        addInterceptor(httpLoggingInterceptor)
        connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
    }.build()

    val RetroFitBuilder: Retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(okHttpClient)
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())

    }.build()

    init{
        retrofirBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        Api = retrofirBuilder.create(ProjectFlowAPI::class.java)
    }

    init {
        chatRetrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_CHAT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        chatApi = chatRetrofitBuilder.create(ChatApi::class.java)
    }

    fun getChatAPI() = chatApi
    fun getAPI() = Api

}