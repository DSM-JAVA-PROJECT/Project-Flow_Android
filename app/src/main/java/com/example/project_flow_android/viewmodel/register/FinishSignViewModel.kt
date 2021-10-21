package com.example.project_flow_android.viewmodel.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage

class FinishSignViewModel(private val sharedPrefenceStorage: SharedPreferenceStorage): ViewModel() {

    private val _finishRegister = MutableLiveData(false)
    val finishRegister: LiveData<Boolean> get() = _finishRegister
    val userName = sharedPrefenceStorage.getInfo("userName")

    fun inputUserName(){
        _finishRegister.value = true
    }

}