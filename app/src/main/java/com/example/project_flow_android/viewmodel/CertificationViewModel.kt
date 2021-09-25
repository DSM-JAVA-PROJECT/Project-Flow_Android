package com.example.project_flow_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.SignApiImpl

class CertificationViewModel(private val signApiImpl: SignApiImpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel()
