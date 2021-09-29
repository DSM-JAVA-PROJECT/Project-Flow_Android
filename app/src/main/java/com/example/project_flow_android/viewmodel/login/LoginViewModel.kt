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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginViewModel(private val loginApiImpl: LoginApiImpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    fun doLogin(){
        viewModelScope.launch {
            val request = LoginRequest(userEmail.value!!,userPassword.value!!)
        }

    }

    fun checkSuccess(response : Response<LoginResponse>){
        if(response.code() == 201){
            _changeComment.value = "인증에 성공하였습니다"
        }
        else if(response.code() == 400){
            _changeComment.value = "인증번호가 일치하지 않습니다"
        }
        else _changeComment.value = "재시도를 해주세요"
    }
}
