package com.example.project_flow_android.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.CertificationApiImpl

class CertificationViewModelFactory(
    private val certificationApiImpl : CertificationApiImpl,
    private val sharedPrefenceStorage: SharedPreferenceStorage
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(CertificationApiImpl::class.java, SharedPreferenceStorage::class.java)
            .newInstance(certificationApiImpl, sharedPrefenceStorage)
}