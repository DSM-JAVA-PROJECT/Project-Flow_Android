package com.example.project_flow_android.viewmodel.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.remote.sign.SignApiImpl
import com.example.project_flow_android.feature.NewPasswordRequest
import kotlinx.coroutines.*
import retrofit2.Callback

class ChangePasswordViewModel(private val signApiImpl: SignApiImpl):ViewModel() {

    val changePassword = MutableLiveData<String>()

    private val _successChange = MutableLiveData(false)
    val successChange: LiveData<Boolean> get() = _successChange

    fun editNewPassword() {
        viewModelScope.launch {
            val newpassword = changePassword.value!!
            if (changePassword.value != null) {
                val response = signApiImpl.changePassword(NewPasswordRequest(newpassword))
//                if (response.isSuccessful) {
//                    _successChange.value = true
//                }
            }
        }
    }
}