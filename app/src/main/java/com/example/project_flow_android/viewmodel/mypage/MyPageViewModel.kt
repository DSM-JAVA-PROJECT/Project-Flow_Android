package com.example.project_flow_android.viewmodel.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageRepositoryImpl
import com.example.project_flow_android.feature.GetUserTokenRequest
import kotlinx.coroutines.launch

class MyPageViewModel(private val myPageRepositorylmpl: MyPageRepositoryImpl, private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel(){


    val userName = MutableLiveData<String>()
    val token = sharedPreferenceStorage.getInfo("access_Token")

    fun getUserInfo(){
        viewModelScope.launch {
            myPageRepositorylmpl.getUserInfo(GetUserTokenRequest(token))

            val response = myPageRepositorylmpl.getUserInfo(GetUserTokenRequest(token))
            if(response.isSuccessful){
                userName.value = response.body()!!.name

            }
            else{
                Log.d("ere","error")
            }
        }
    }
}