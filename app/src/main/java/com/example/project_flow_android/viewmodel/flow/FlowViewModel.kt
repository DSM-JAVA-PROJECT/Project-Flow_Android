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

class FlowViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val flowApiImpl: FlowApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val getUserName = MutableLiveData<String>()

    val projectName = MutableLiveData<String>()
    val projectImage = MutableLiveData<String>()
    val today = MutableLiveData<String>()
    val projectLastDate = MutableLiveData<String>()
    val personalProgress = MutableLiveData<String>()
    val projectProgress = MutableLiveData<String>()

    val dialogContext = MutableLiveData<String>()

    private val _emptyProject = MutableLiveData<Boolean>()
    val emptyProject: LiveData<Boolean> get() = _emptyProject

    private val _successRemove = MutableLiveData<Boolean>()
    val successRemove: LiveData<Boolean> get() = _successRemove

    val projectsId = sharedPreferenceStorage.getInfo("projectId")
    val token = sharedPreferenceStorage.getInfo("access_token")

    fun finishProject(){
        flowApiImpl.finishProject(projectsId).subscribe({ response ->
            if(response.isSuccessful){
                _successRemove.value!!
            }
        })

    }


    fun inputDialogProjectName(){
        myPageApiImpl.getUserInfo(token).subscribe({ it ->
            if(it.isSuccessful){
                dialogContext.value = it.body()!!.projects[0].projectName
            }
        })
    }
    fun getMainUserInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                getUserName.value = response.body()!!.name
                if (response.body()!!.projects.isEmpty()) {
                    _emptyProject.value = true
                }
            } else {
                getUserName.value = "로딩 실패"
            }
        }, {
            getUserName.value = "로딩 실패"
        })
    }

    fun getProjectInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
        val formatted = current.format(formatter)

        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                getUserName.value = response.body()!!.name
                projectName.value = response.body()!!.projects[0].projectName
                projectImage.value = response.body()!!.projects[0].logoImage
                today.value = formatted.toString()
                projectLastDate.value = response.body()!!.projects[0].remainingDays
                projectProgress.value = response.body()!!.projects[0].projectProgress
                personalProgress.value = response.body()!!.projects[0].personalProgress

            } else {
                getUserName.value = "로딩 실패"
            }
        }, {
            getUserName.value = "로딩 실패"
        })
    }
}