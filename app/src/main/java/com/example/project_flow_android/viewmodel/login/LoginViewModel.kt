package com.example.project_flow_android.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.sign.LoginApiImpl
import com.example.project_flow_android.feature.LoginRequest

class LoginViewModel(
    private val loginApiImpl: LoginApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val accessToken = MutableLiveData<String>()

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    private val _successLogin = MutableLiveData(false)
    val successLogin: LiveData<Boolean> get() = _successLogin

    fun doLogin() {
        loginApiImpl.loginApi(LoginRequest(userEmail.value!!, userPassword.value!!)).subscribe({
            if (it.isSuccessful) {
                _successLogin.value = true
                sharedPreferenceStorage.saveInfo("userEmail", userEmail.value!!)
                sharedPreferenceStorage.saveInfo("userPassword", userPassword.value!!)
                sharedPreferenceStorage.saveInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2Mzg0ODk5ODcsImV4cCI6MTYzODU3NjM4NywiaWQiOiI2MTljOTQzMzhkNmUyNjczNGI1MTFjY2QiLCJlbWFpbCI6InduZHVmMDQwNV9AbmF2ZXIuY29tIn0.b6omKBl59C7LT2zaGRNh9WoFM8Keu-tduNiCUsf_IXo", "access_token")
                sharedPreferenceStorage.saveInfo(it.body()!!.refreshToken, "refresh_token")
            } else {
                _changeComment.value = "로그인에 실패하였습니다"
            }
        }, {
            _changeComment.value = "로그인 실패하였습니다"
        })
    }
}

