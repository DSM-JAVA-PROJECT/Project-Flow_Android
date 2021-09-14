package com.example.project_flow_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel() : ViewModel() {

    val loginInterface = ApiProvider.getInstnace().create(ProjectFlowAPI::class.java)

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    fun doLogin() {
//        if (userEmail.value != null || userPassword.value != null) {
//            val loginCall = loginInterface.doLogin(
//                LoginRequest(
//                    SharedPreferenceStorage.getInfo(userEmail.value!!),
//                    SharedPreferenceStorage.getInfo(userPassword.value!!)
//                )
//            )
//            loginCall.enqueue(object : Callback<Unit> {
//                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                    while (response.isSuccessful) {
//
//                    }
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//
//                }
//            })
//        } else {
//            _changeComment.value = "비밀번호가 일치하지 않습니다"
//        }
    }
}
