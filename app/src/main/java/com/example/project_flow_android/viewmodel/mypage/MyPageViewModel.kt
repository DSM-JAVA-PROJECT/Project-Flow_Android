package com.example.project_flow_android.viewmodel.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.GetUserInfoResponse
import java.io.File

class MyPageViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val userName = MutableLiveData<String>()

    val lastImage =MutableLiveData<String>()

    lateinit var imagePath: String

    val profileImage = MutableLiveData<File>()
    val getUserImage = MutableLiveData<String>()

    private val _clearAll = MutableLiveData<Boolean>()
    val clearAll: LiveData<Boolean> get() = _clearAll

    private val _successLogout = MutableLiveData<Boolean>()
    val successLogout: LiveData<Boolean> get() = _successLogout

    private val _projects = MutableLiveData<GetUserInfoResponse>()
    val projects: LiveData<GetUserInfoResponse> get() = _projects

    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean> get() = _successGet

    private val _successChange = MutableLiveData<Boolean>()
    val successChange: LiveData<Boolean> get() = _successChange

    private val _successImage = MutableLiveData<Boolean>()
    val successImage: LiveData<Boolean> get() = _successImage


    fun getUserInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                userName.value = response.body()!!.name
                getUserImage.value = response.body()!!.profileImage
            } else {
                userName.value = "로딩 실패"
            }
        }, {
            userName.value = "로딩 실패"
        })
    }

    fun postProfileImage() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.changeImage(token, File(imagePath)).subscribe({ response ->
            if (response.isSuccessful) {
                _successImage.value!!
            } else {
            }
        }, {
        })
    }

    fun getProjectInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                _projects.value = response.body()
            } else {

            }
        }, {

        })
    }

    fun loadImage() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getUserInfo(token).subscribe { response ->
            if (response.isSuccessful) {
                _successChange.value = true
                 lastImage.value = response.body()!!.profileImage
            } else {

            }
        }
    }
}