package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl

class FlowViewModel(private val flowApiImpl: FlowApiImpl,private val sharedPrefenceStorage: SharedPreferenceStorage) :ViewModel(){

    val inputName = MutableLiveData<String>()

    fun getUserInfo() {
        val token = sharedPrefenceStorage.getInfo("access_token")

        flowApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                inputName.value = response.body()!!.name
            } else {
                inputName.value = "loading error😳"
            }
        }, {
            inputName.value = "loading error😳"
        })
    }
}