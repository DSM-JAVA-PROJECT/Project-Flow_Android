package com.example.project_flow_android.viewmodel.certification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.CertificationApiImpl
import com.example.project_flow_android.data.remote.SignApiImpl

class CertificationViewModel(
    private val certificationApiImpl: CertificationApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel(){

}
