package com.example.project_flow_android.viewmodel.mypage

import android.widget.Toast
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


    val token by lazy {
        sharedPreferenceStorage.getInfo("access_token")
    }

    val changePassword = MutableLiveData<String>()
    val _toastContent = MutableLiveData<String>()
    private val toastContent : LiveData<String> get() = _toastContent
    val successChange: LiveData<Boolean> get() = _successChange
    private val _successChange = MutableLiveData(false)

    fun changePassword(){
        myPageApiImpl.changePassword(token, changePassword.value!!).subscribe({
            if(it.isSuccessful){
                _successChange.value = true
                _toastContent.value = "변경에 성공하였습니다"
            }
            else {
            }
        },{
        })
        _toastContent.value = "새로운 비밀번호를 입력해주세요"
    }
}