package com.example.project_flow_android.viewmodel.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.GetUserTokenRequest
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val userName = MutableLiveData<String>()

    private val _clearAll = MutableLiveData<Boolean>()
    val clearAll: LiveData<Boolean> get() = _clearAll

    private val _successLogout = MutableLiveData<Boolean>()
    val successLogout: LiveData<Boolean> get() = _successLogout


    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean> get() = _successGet

    private val _successChange = MutableLiveData<Boolean>()
    val successChange: LiveData<Boolean> get() = _successChange

    fun getUserInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")

        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                userName.value = response.body()!!.name
            } else {
                userName.value = "loading error😳"
            }
        }, {
            userName.value = "loading error😳"
        })
    }


    fun doLogout() {
        sharedPreferenceStorage.clearAll()
        _successLogout.value = true
    }

}