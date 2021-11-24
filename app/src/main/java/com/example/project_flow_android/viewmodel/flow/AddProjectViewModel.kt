package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.feature.AddProjectRequest
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.fragment.ChatListFragment
import com.example.project_flow_android.ui.main.MainActivity

class AddProjectViewModel(
    private val flowApiImpl: FlowApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {
    val projectName = MutableLiveData<String>()
    val projectExplanation = MutableLiveData<String>()

    val projectMember = MutableLiveData<String>()

    val startDate = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()

    private val _successAddProject = MutableLiveData<Boolean>()
    val successAddProject: LiveData<Boolean> get() = _successAddProject

    fun addProject() {
        val member : String =  projectMember.value!!
        val splitArray = member.split(",")
        val token = sharedPreferenceStorage.getInfo("access_token")
        flowApiImpl.addProject(token,AddProjectRequest(projectName.value!!,projectExplanation.value!!,startDate.value!!,endDate.value!!,splitArray)).subscribe({
            if(it.isSuccessful){
                sharedPreferenceStorage.saveInfo(it.body()!!.projectId, "projectsId")
                _successAddProject.value!!
            }
            else {
            }
        },{
        })
    }
}