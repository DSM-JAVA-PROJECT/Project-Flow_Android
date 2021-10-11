package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.FlowApilmpl

class ProjectViewModel(private val flowApilmpl: FlowApilmpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

    val user = MutableLiveData<String>()
    val userName = sharedPreferenceStorage.getInfo("userName")

    fun inputUser(){
        user.value = userName.toString()
    }


}