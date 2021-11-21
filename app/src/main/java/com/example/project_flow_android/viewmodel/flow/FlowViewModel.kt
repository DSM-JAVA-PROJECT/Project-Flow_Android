package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FlowViewModel(private val myPageApiImpl: MyPageApiImpl,private val flowApiImpl: FlowApiImpl,private val sharedPreferenceStorage: SharedPreferenceStorage) :ViewModel(){

    //TODO 프로젝트 여부 검사 후 메인 이미지 visibltiy

    val getUserName = MutableLiveData<String>()

    //TODO 프로젝트 정보 가져오기
    val projectName = MutableLiveData<String>()
    val projectImage = MutableLiveData<String>()
    val today = MutableLiveData<String>()
    val projectLastDate = MutableLiveData<String>()

    private val _emptyProject = MutableLiveData<Boolean>()
    val emptyProject: LiveData<Boolean> get() = _emptyProject

    private val _successRemove = MutableLiveData<Boolean>()
    val successRemove: LiveData<Boolean> get() = _successRemove

//
//    //TODO 프로젝트 마감
//    fun finishProject(){
//        val token = sharedPreferenceStorage.getInfo("access_token")
//        //TODO 프로젝트 ID를 받는가?
//        //만약에 request로 보내는 거 아니면 다른 방법 사용해야 함
//        flowApiImpl.finishProject().subscribe({ response ->
//            if(response.isSuccessful){
//                //성공했을 때
//                _successRemove.value!!
//            }
//        })
//
//    }


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

    //메인 UI 제작 돌입
    fun getProjectInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")

        //TODO 시간 계산
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
        val formatted = current.format(formatter)

        //TODO 남은 날짜 계산
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                getUserName.value = response.body()!!.name
//                projectName.value = response.body()!!.projects
//                projectImage.value = response.body()!!.projects
                today.value = formatted.toString()
                projectLastDate.value


            } else {
                getUserName.value = "로딩 실패"
            }
        }, {
            getUserName.value = "로딩 실패"
        })
    }
}