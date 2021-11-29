package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.GetMainInfoResponse

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
     val info = MutableLiveData<String>()

    val dialogContext = MutableLiveData<String>()
    
    private val _getMainInfo = MutableLiveData<GetMainInfoResponse>()
    val getMainInfo: LiveData<GetMainInfoResponse> get() = _getMainInfo

    private val _successRemove = MutableLiveData<Boolean>()
    val successRemove: LiveData<Boolean> get() = _successRemove

    private val _checkCheckBox = MutableLiveData<Boolean>()
    val checkCheckBox: LiveData<Boolean> get() = _checkCheckBox

    private val _checkInfo = MutableLiveData<String>()
    val checkInfo: LiveData<String> get() = _checkInfo

    val projectsId = sharedPreferenceStorage.getInfo("projectId")
    val token = sharedPreferenceStorage.getInfo("access_token")

    fun getMainUserInfo() {
        myPageApiImpl.getUserInfo(token).subscribe({ response ->
            if (response.isSuccessful) {
                    getUserName.value = response.body()!!.name
            } else {
                getUserName.value = "로딩 실패"
            }
        }, {
            getUserName.value = "로딩 실패"
        })
    }

    fun finishProject(projectId : String) {
        flowApiImpl.finishProject(projectId).subscribe { response ->
            if (response.isSuccessful) {
                if(checkCheckBox.value == false)
                {
                    _checkInfo.value = "마감하시려면 체크를 눌러주세요"
                }
                _successRemove.value!!
            }
        }
    }

    fun inputDialogProjectName(position: Int) {
        myPageApiImpl.getUserInfo(token).subscribe { it ->
            if (it.isSuccessful) {
                dialogContext.value = it.body()!!.projects[position].projectName
            }
        }
    }

    fun getProjectDetailInfo() {
        flowApiImpl.getMainInfo(token).subscribe({
            if (it.isSuccessful) {
                _getMainInfo.value = it.body()
            } else {
                it
            }
        }, {
            it
        })
    }
}