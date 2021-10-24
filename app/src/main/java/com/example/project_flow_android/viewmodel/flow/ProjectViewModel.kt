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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class ProjectViewModel : ViewModel() {

    private val flowRepository = FlowRepositoryImpl()
    private val myPageRepository = MyPageRepositoryImpl()



    val userName = MutableLiveData<String>()
    val projectName = MutableLiveData<String>()
    val explanation = MutableLiveData<String>()
    val startDay = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    val projectImage = MutableLiveData<File>()
    val userEmails = MutableLiveData<Array<String>>()

    private val _successAddProject = MutableLiveData(false)
    val successAddProject: LiveData<Boolean> get() = _successAddProject

    fun Addproject() = runBlocking {
        viewModelScope.launch {
            projectData()
        }
    }

    fun inputUserNmae(){

    }

    private suspend fun projectData()  {
        val response = flowRepository.addProjectApi(AddProjectRequest(projectName.value!!,explanation.value!!,startDay.value!!,endDate.value!!,projectImage.value!!,userEmails.value!!))
        if (response.isSuccessful) {
            if (response.code() == 201) {
                _successAddProject.value = true
            }
            else{

            }

        }

    }


}

