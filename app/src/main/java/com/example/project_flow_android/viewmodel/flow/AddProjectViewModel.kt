package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowRepositoryImpl
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddProjectViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    private val flowRepository = FlowRepositoryImpl()

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


    fun addProject(file: MultipartBody.Part) {
        viewModelScope.launch {
            val member: String = projectMember.value!!
            val splitArray: List<String> = member.split(",")
            val numArray = splitArray.toTypedArray()

            val request = flowRepository.fileUpload(token,
                projectName.value!!,
                projectExplanation.value!!,
                startDate.value!!,
                endDate.value!!,
                file,
                numArray)
            if (request.isSuccessful) {
                request.body()!!
            }
            request.message()
        }
    }
}
