package com.example.project_flow_android.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.LoginApiImpl
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI

class LoginViewModel(private val loginApiImpl: LoginApiImpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment


}
