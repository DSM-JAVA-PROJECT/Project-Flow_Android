package com.example.project_flow_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.feature.LoginRequest
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginViewModel(private val signApiImpl: SignApiImpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment


}
