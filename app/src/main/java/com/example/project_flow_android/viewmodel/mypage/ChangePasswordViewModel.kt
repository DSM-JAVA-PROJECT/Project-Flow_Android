package com.example.project_flow_android.viewmodel.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.data.remote.sign.SignApiImpl
import com.example.project_flow_android.feature.NewPasswordRequest
import kotlinx.coroutines.*
import retrofit2.Callback

class ChangePasswordViewModel(val myPageApiImpl: MyPageApiImpl, private val sharedPreferenceStorage: SharedPreferenceStorage):ViewModel() {

    val changePassword = MutableLiveData<String>()
    val token = sharedPreferenceStorage.getInfo("access_Token")

    private val _successChange = MutableLiveData(false)
    val successChange: LiveData<Boolean> get() = _successChange

    fun changePassword(){
        myPageApiImpl.changePassword(token,
            NewPasswordRequest(changePassword.value!!)).subscribe({ it ->
            if(it.isSuccessful){
                _successChange.value!!
            }
            else {

            }
        },{

        })
    }
}