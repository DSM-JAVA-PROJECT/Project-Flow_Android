package com.example.project_flow_android.network

import com.example.project_flow_android.feature.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File


interface ProjectFlowAPI {

    @POST("/auth/join")
    fun register(@Body request: RegisterRequest): Single<Response<Void>>

    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>

    @POST("/email/")
    fun postCertification(@Body request: CertificationRequest): Single<Response<Void>>

    @POST("/email/verifyCode")
    fun checkCertification(@Body request: PostCertificationRequest): Single<Response<Void>>

    @GET("/auth/myPage")
    fun getuserInfo(@Header("Authorization") token: String): Single<Response<GetUserInfoResponse>>

    @PATCH("/auth/password")
    fun changePassword(
        @Header("Authorization") token: String,
        @Query("password") password: String,
    ): Single<Response<Void>>

    @GET("/main")
    fun getMainInfo(@Header("Authorization") token: String): Single<Response<GetMainInfoResponse>>

    @PATCH("/project/close/{id}")
    fun finishProject(
        @Header("Authorization") token: String,
        @Path("id") projectId: String,
    ): Single<Response<Void>>

    @PATCH("/plan/close?project={projectId}&plan={planId}")
    fun finishPlan(
        @Header("Authorization") token: String,
        @Part("projectId") projectId : String,
        @Path("plan_id") PlanId: String,
    ): Single<Response<Void>>


    @GET("/auth/oauth")
    fun gitOauth() : Single<Response< GitToken>>


    @Multipart
    @POST("/project")
    fun addProjectQuery(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @PartMap request: HashMap<String, RequestBody>
    ): Single<Response<GetProjectsId>>


    //이미지 최종 전송
    @POST("/project/body")
    fun addProject2(
        @Header("Authorization") token: String,
        @Body request: AddProjectRequest
    ): Single<Response<GetProjectsId>>


    //이미지 변환을 위한
    @Multipart
    @POST("/project/image")
    fun postImage(
        @Part file : MultipartBody.Part
    ) : Single<Response<image>>


    //이미지 수정
    @PATCH("/auth/image")
    fun patchImage(
        @Header("Authorization") token: String,
        @Body request : String
    ): Single<Response<Void>>


    //이미지 수정
    @Multipart
    @POST("/auth/image")
    fun postProfileImage(
        @Part file : MultipartBody.Part
    ): Single<Response<image>>





}