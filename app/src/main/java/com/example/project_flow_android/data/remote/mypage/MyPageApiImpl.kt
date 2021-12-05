package com.example.project_flow_android.data.remote.mypage

import com.example.project_flow_android.data.remote.toMultipartPart
import com.example.project_flow_android.feature.GetGitProjectIssue
import com.example.project_flow_android.feature.GetUserInfoResponse
import com.example.project_flow_android.feature.NewPasswordRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.GitAPI
import com.example.project_flow_android.network.ProjectFlowAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class MyPageApiImpl {

    private fun providerMypageApi(): ProjectFlowAPI = ApiProvider.RetroFitBuilder.create(
        ProjectFlowAPI::class.java)

    private fun providerGitApi() : GitAPI = ApiProvider.GitRetroFitBuilder.create(
        GitAPI::class.java
    )

    fun getUserInfo(token: String): @NonNull Single<Response<GetUserInfoResponse>> =
        providerMypageApi().getuserInfo(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun changePassword(
        token: String,
        password: String,
    ): @NonNull Single<Response<Void>> =
        providerMypageApi().changePassword(token, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun changeImage(token: String,file : File): @NonNull Single<Response<Void>> =
        providerMypageApi().changeProfileImage(token, file.toMultipartPart())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getGitInfo(token : String): @NonNull Single<Response<GetGitProjectIssue>> =
        providerGitApi().getGitInfo(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}
