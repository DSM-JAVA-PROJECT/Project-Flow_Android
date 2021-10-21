package com.example.project_flow_android.viewmodel.certification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.sign.CertificationApiImpl
import com.example.project_flow_android.data.remote.sign.SignApiImpl

class CertificationViewModelFactory(
    private val certificationApiImpl : CertificationApiImpl,
    private val signApiImpl: SignApiImpl,
    private val sharedPrefenceStorage: SharedPreferenceStorage
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(CertificationApiImpl::class.java,
            SignApiImpl::class.java, SharedPreferenceStorage::class.java)
            .newInstance(certificationApiImpl,signApiImpl, sharedPrefenceStorage)
}