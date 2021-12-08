package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.base.SingleLiveEvent
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.feature.GetProjectScheduleDetailResponse
import io.reactivex.Single

class FlowViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val flowApiImpl: FlowApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val getUserName = MutableLiveData<String>()
    val projectName = MutableLiveData<String>()
    val projectImage = MutableLiveData<String>()
    val projectLastDate = MutableLiveData<String>()
    val personalProgress = MutableLiveData<String>()
    val info = MutableLiveData<String>()
    val getProjectId = MutableLiveData<String>()
    val getProjectIdForPlan = MutableLiveData<String>()
    val clickFinish = SingleLiveEvent<GetMainInfoResponse.GetMainInfoDetailResponse>()
    val planclickFinish = SingleLiveEvent<GetProjectScheduleDetailResponse>()
    val getPlanId = MutableLiveData<String>()
    val planItemContent = MutableLiveData<String>()

    private val _projectId = MutableLiveData<String>()
    val projectId: LiveData<String> get() = _projectId

    private val _planId = MutableLiveData<String>()
    val planId: LiveData<String> get() = _planId

    private val _getMainInfo = MutableLiveData<GetMainInfoResponse>()
    val getMainInfo: LiveData<GetMainInfoResponse> get() = _getMainInfo

    private val _successRemove = MutableLiveData<Boolean>()
    val successRemove: LiveData<Boolean> get() = _successRemove

    private val _successPlanRemove = MutableLiveData<Boolean>()
    val successPlanRemove: LiveData<Boolean> get() = _successPlanRemove

    private val _checkCheckBox = MutableLiveData<Boolean>()
    val checkCheckBox: LiveData<Boolean> get() = _checkCheckBox

    private val _checkInfo = MutableLiveData<String>()
    val checkInfo: LiveData<String> get() = _checkInfo

    private val _fullProject = MutableLiveData<Boolean>()
    val fullProject: LiveData<Boolean> get() = _fullProject

    val token by lazy {
        sharedPreferenceStorage.getInfo("access_token")

    }
    val projectNumber = sharedPreferenceStorage.getProjectId("projectId")
    val planNumber = sharedPreferenceStorage.getPlanId("planId")

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

    fun finishProject() {
        flowApiImpl.finishProject(token, getProjectId.value!!).subscribe({ response ->
            if (response.isSuccessful) {
                _successRemove.value = true
            }
        }, {
            it
        })
    }


    fun finishPlan() {
        flowApiImpl.finishPlan(token, projectNumber, planNumber).subscribe({
            if (it.isSuccessful) {
                _successPlanRemove.value = true
            }
        }, {
            it
        })
    }


    fun getProjectDetailInfo() {
        flowApiImpl.getMainInfo(token).subscribe({
            if (it.isSuccessful) {
                _getMainInfo.value = it.body()
                _projectId.value = it.body().toString()
                if (it.body()!!.projects.size != 0) {
                    _fullProject.value = true
                }
            } else {
                it
            }
        }, {
            it
        })
    }
}