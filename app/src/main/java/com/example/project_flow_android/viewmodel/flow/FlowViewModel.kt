package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.feature.ImageRequest
import java.io.File
import java.util.*

class FlowViewModel(
    private val flowApiImpl: FlowApiImpl,
    private val sharedPrefenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val inputName = MutableLiveData<String>()

    val projectImage = MutableLiveData<File>()
    val projectName = MutableLiveData<String>()
    val projectExplain = MutableLiveData<String>()
    val projectMember = MutableLiveData<Array<String>>()

    //TODO memeber는 split으로 자르기
    val projectStartDate = MutableLiveData<String>()
    val projectEndDate = MutableLiveData<String>()

    private val _addProject = MutableLiveData(false)
    val addProject: LiveData<Boolean> get() = _addProject

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> get() = _toast

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

    fun addProject() {
        val token = sharedPrefenceStorage.getInfo("access_token")

        flowApiImpl.addProject(token,
            AddProjectRequest(projectName.value!!,
                projectExplain.value!!,
                projectStartDate.value!!,
                projectEndDate.value!!,
                projectMember.value!!),
            ImageRequest(projectImage.value!!)).subscribe { it ->
            if (it.isSuccessful) {
                _addProject.value = true
                _toast.value = "프로젝트 생성에 성공하였습니다"
            } else {
                _toast.value = "프로젝트 생성에 성공하였습니다"
            }
        }

    }
}