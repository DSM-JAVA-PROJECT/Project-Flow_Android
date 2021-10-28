package com.example.project_flow_android.viewmodel.flow

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowRepositoryImpl
import com.example.project_flow_android.data.remote.mypage.MyPageRepository
import com.example.project_flow_android.data.remote.mypage.MyPageRepositoryImpl
import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.feature.GetUserTokenRequest
import kotlinx.coroutines.launch
import java.io.File


class ProjectViewModel(
    private val flowRepositoryImpl: FlowRepositoryImpl,
    private val myPageRepositoryImpl: MyPageRepositoryImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val inputusername = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val projectName = MutableLiveData<String>()
    val explanation = MutableLiveData<String>()
    val startDay = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    val projectImage = MutableLiveData<File>()
    val userEmails = MutableLiveData<Array<String>>()

    private val _goAddProject = MutableLiveData<Boolean>()
    val goAddProject: LiveData<Boolean> get() = _goAddProject

    private val _successAddProject = MutableLiveData(false)
    val successAddProject: LiveData<Boolean> get() = _successAddProject

    fun Addproject() {
        viewModelScope.launch {
            getUserInfo()
        }
    }

    private suspend fun getUserInfo() {
        val token = sharedPreferenceStorage.getInfo("access_Token")
        val response = myPageRepositoryImpl.getUserInfo(GetUserTokenRequest(token))
        if (response.isSuccessful) {
            inputusername.value = response.body()!!.name
        } else
            inputusername.value = "실패"
    }


    private suspend fun projectData() {
        val response = flowRepositoryImpl.addProjectApi(AddProjectRequest(projectName.value!!,
            explanation.value!!,
            startDay.value!!,
            endDate.value!!,
            projectImage.value!!,
            userEmails.value!!))
        if (response.isSuccessful) {
            if (response.code() == 201) {
                _successAddProject.value = true
            } else {

            }

        }

    }


}

