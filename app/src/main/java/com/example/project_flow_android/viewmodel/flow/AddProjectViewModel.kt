package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.feature.AddProjectRequest
import java.io.File

class AddProjectViewModel(
    private val flowApiImpl: FlowApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val token by lazy {
        sharedPreferenceStorage.getInfo("access_token")
    }

    val projectName = MutableLiveData<String>()
    val projectExplanation = MutableLiveData<String>()
    val projectMember = MutableLiveData<String>()
    val startDate = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    private val _successAddProject = MutableLiveData<Boolean>()
    val successAddProject: LiveData<Boolean> get() = _successAddProject

    lateinit var imagePath: String
    val responseImage = MutableLiveData<String>()

    fun getImage2() {
        flowApiImpl.postImage(
            File(imagePath)
        ).subscribe({
            if (it.isSuccessful) {
                responseImage.value = it.body()!!.image
            } else {
                it
            }

        }, {
            it
        })
    }


    fun addProject() {
        val member: String = projectMember.value!!
        val splitArray: List<String> = member.split(",")
        val numArray = splitArray.toTypedArray()
        flowApiImpl.addProject2(
            token,
            AddProjectRequest(projectName.value!!,
                projectExplanation.value!!,
                startDate.value!!,
                endDate.value!!,
                responseImage.value!!,
                numArray)).subscribe({
            if (it.isSuccessful) {
                _successAddProject.value = true
                sharedPreferenceStorage.saveInfo( it.body()!!.projectId,"projectId")
                it
            } else {
                it
            }

        }, {
            it
        })
    }
}