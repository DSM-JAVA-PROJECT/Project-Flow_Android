package com.example.project_flow_android.viewmodel.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.GetUserInfoResponse
import okhttp3.MultipartBody
import java.io.File

class MyPageViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val userName = MutableLiveData<String>()
    val profileImage = MutableLiveData<File>()

    private val _clearAll = MutableLiveData<Boolean>()
    val clearAll: LiveData<Boolean> get() = _clearAll

    val successLogout = MutableLiveData<Boolean>()

    private val _projects = MutableLiveData<GetUserInfoResponse>()
    val projects: LiveData<GetUserInfoResponse> get() = _projects

    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean> get() = _successGet

    private val _successChange = MutableLiveData<Boolean>()
    val successChange: LiveData<Boolean> get() = _successChange

    private val _successImage = MutableLiveData<Boolean>()
    val successImage: LiveData<Boolean> get() = _successImage

    private val _getUserImage = MutableLiveData<String>()
    val getUserImage: LiveData<String> get() = _getUserImage

    lateinit var imagePath: String
    val token = sharedPreferenceStorage.getInfo("access_token")
    val responseImage = MutableLiveData<String>()

    fun getUserInfo() {
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                _successGet.value = true
                userName.value = response.body()!!.name
                _getUserImage.value = response.body()!!.profileImage
            } else {
                userName.value = "로딩 실패"
            }
        }, {
            userName.value = "로딩 실패"
        })
    }

    fun firstImagePost() {
        myPageApiImpl.postProfileImage(File(imagePath)).subscribe({ response ->
            if (response.isSuccessful) {
                responseImage.value = response.body()!!.image
            } else {
                response
            }

        }, {
            it
        })
    }

    fun postProfileImage() {
        myPageApiImpl.patchImage(token, responseImage.value!!).subscribe({ response ->
            if (response.isSuccessful) {
                _successImage.value = true
            } else {
            }
        }, {
        })
    }

    fun getProjectInfo() {
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                _projects.value = response.body()
            } else {

            }
        }, {

        })
    }
}