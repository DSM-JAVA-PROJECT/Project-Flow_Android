package com.example.project_flow_android.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.LoginApiImpl

class LoginViewModelFactory(
    private val loginApiImpl: LoginApiImpl,
    private val sharedPrefenceStorage: SharedPreferenceStorage
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(LoginApiImpl::class.java, SharedPreferenceStorage::class.java)
            .newInstance(loginApiImpl, sharedPrefenceStorage)
}