package com.example.project_flow_android.viewmodel.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageRepositoryImpl
import com.example.project_flow_android.feature.GetUserTokenRequest
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val myPageRepositorylmpl: MyPageRepositoryImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {
    val userName = MutableLiveData<String>()
    val token = sharedPreferenceStorage.getInfo("access_Token")

    private val _clearAll = MutableLiveData<Boolean>()
    val clearAll: LiveData<Boolean> get() = _clearAll

    init {
        viewModelScope.launch {
            launchAll()
        }
    }

    private suspend fun launchAll() = coroutineScope {
        launchA()

    }

    private suspend fun launchA() {
        logout()
        getUserInfo()

    }

    private fun logout() {
        sharedPreferenceStorage.clearAll()
        _clearAll.value = true
    }

    private suspend fun getUserInfo() {
        myPageRepositorylmpl.getUserInfo(GetUserTokenRequest(token))
        val response = myPageRepositorylmpl.getUserInfo(GetUserTokenRequest(token))
        if (response.isSuccessful) {
            userName.value = response.body()!!.name
        } else {
            userName.value = "load error😮‍💨"
        }
    }
}