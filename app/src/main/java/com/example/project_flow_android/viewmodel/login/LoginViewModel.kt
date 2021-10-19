package com.example.project_flow_android.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.LoginApiImpl
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.feature.LoginResponse
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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
            } else {
                _changeComment.value = "로그인에 실패하였습니다"
            }
        }, {
            _changeComment.value = "로그인 실패하였습니다"
        })
    }
}

