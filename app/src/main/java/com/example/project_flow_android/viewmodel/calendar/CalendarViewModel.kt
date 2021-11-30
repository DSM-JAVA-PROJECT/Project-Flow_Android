package com.example.project_flow_android.viewmodel.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.feature.GetGitProjectIssue
import com.example.project_flow_android.feature.GetMainInfoResponse

class CalendarViewModel(private val myPageApiImpl: MyPageApiImpl) : ViewModel(){

    private val _getMainInfo = MutableLiveData<GetGitProjectIssue>()
    val getMainInfo: LiveData<GetGitProjectIssue> get() = _getMainInfo

    val gitToken = "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"

    fun getGitInfo(){
        myPageApiImpl.getGitInfo(gitToken).subscribe({ it ->
            if(it.isSuccessful){
                _getMainInfo.value = it.body()
                it
            }
        }){
            it
        }
    }

}