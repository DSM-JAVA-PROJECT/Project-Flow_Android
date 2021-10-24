package com.example.project_flow_android.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageRepositoryImpl
import com.example.project_flow_android.feature.GetUserTokenRequest
import kotlinx.coroutines.launch

class MyPageViewModel(private val myPageRepositorylmpl: MyPageRepositoryImpl, private val sharedPreferenceStorage: SharedPreferenceStorage,) : ViewModel(){

    val userName = MutableLiveData<String>()

    fun getUserInfo(){
        viewModelScope.launch {
            val token = sharedPreferenceStorage.getInfo("accessToken")
            myPageRepositorylmpl.getUserInfo(GetUserTokenRequest(token))
        }
    }
}