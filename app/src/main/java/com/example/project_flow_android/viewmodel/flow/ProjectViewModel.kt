package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.FlowApilmpl
import java.io.File

class ProjectViewModel(private val flowApilmpl: FlowApilmpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

    val user = MutableLiveData<String>()
    val userName = sharedPreferenceStorage.getInfo("userName")

    val projectName = MutableLiveData<String>()
    val explanation = MutableLiveData<String>()
    val startDay = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    val projectImage = MutableLiveData<File>()
    val userEmails = MutableLiveData<Array<String>>()


    fun inputUser(){
        user.value = userName.toString()
    }


}

