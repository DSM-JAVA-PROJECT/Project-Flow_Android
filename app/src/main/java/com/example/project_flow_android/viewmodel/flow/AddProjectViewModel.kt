package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.feature.AddProjectRequest

class AddProjectViewModel(
    private val flowApiImpl: FlowApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val token = sharedPreferenceStorage.getInfo("access_token")

    val projectName  = MutableLiveData<String>()
    val projectExplanation = MutableLiveData<String>()
    val projectTeammember = MutableLiveData<List<String>>()

    val startDate = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    private val _successAddProject = MutableLiveData<Boolean>()
    val successAddProject: LiveData<Boolean> get() = _successAddProject


    fun addProject() {
//        flowApiImpl.addProject(AddProjectRequest(projectName.value!!,projectExplanation.value!!,startDate.value!!,endDate.value!!,
//            List(projectTeammember.value!!))).subscribe({
//            if(it.isSuccessful){
//                //TODO 프로젝트 생성 성공
//                _successAddProject.value!!
//            }
//            else {
//            }
//        },{
//        })
    }
}