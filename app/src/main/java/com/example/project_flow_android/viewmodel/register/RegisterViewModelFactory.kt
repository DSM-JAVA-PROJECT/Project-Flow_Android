package com.example.project_flow_android.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.SignApiImpl

class RegisterViewModelFactory(
    private val sharedPrefenceStorage: SharedPreferenceStorage
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(SharedPreferenceStorage::class.java)
            .newInstance(sharedPrefenceStorage)
}