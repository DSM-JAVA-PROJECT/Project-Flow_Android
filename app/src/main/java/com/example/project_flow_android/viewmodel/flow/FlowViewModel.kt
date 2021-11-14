package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl

class FlowViewModel(private val myPageApiImpl: MyPageApiImpl,private val sharedPreferenceStorage: SharedPreferenceStorage) :ViewModel(){

    //TODO 프로젝트 여부 검사 후 메인 이미지 visibltiy

    val getUserName  = MutableLiveData<String>()

    private val _emptyProject = MutableLiveData<Boolean>()
    val emptyProject: LiveData<Boolean> get() = _emptyProject


    fun getMainUserInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                getUserName.value = response.body()!!.name
                //TODO 우선 response body에 project가 없는 경우에 empty 프로젝트 넣어주기
                    if(response.body()!!.projects.isEmpty()){
                    _emptyProject.value=true
                }
            } else {
                getUserName.value = "로딩 실패"
            }
        }, {
            getUserName.value = "로딩 실패"
        })
    }
}