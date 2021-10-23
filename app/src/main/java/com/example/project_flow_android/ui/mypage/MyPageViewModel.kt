package com.example.project_flow_android.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.LoginApiImpl
import com.example.project_flow_android.data.remote.MyPageApilmpl
import com.example.project_flow_android.feature.GetUserTokenRequest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

class MyPageViewModel(private val myPageApilmpl: MyPageApilmpl,private val sharedPreferenceStorage: SharedPreferenceStorage,) : ViewModel(){

    val userName = MutableLiveData<String>()

    fun getUserInfo(){
        viewModelScope.launch {
            val token = sharedPreferenceStorage.getInfo("accessToken")
            myPageApilmpl.getUserInfo(GetUserTokenRequest(token))
        }
    }
}