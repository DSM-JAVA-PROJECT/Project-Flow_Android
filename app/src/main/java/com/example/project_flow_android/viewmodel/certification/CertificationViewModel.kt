package com.example.project_flow_android.viewmodel.certification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.CertificationApiImpl
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.feature.CertificationRequest

class CertificationViewModel(
    private val certificationApiImpl: CertificationApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel(){

    private val _firstPostCertificationCode = MutableLiveData(false)
    val firstPostCertificationCode: LiveData<Boolean> get() = _firstPostCertificationCode

    private val _changeComment_3 = MutableLiveData<String>()
    val changeComment3: LiveData<String> get() = _changeComment_3

    private val _successfulCertification = MutableLiveData(false)
    val successfulCertification: LiveData<Boolean> get() = _successfulCertification

    val certificationCode = MutableLiveData<String>()

    fun postCertification(){
        val userEmail = sharedPreferenceStorage.getInfo("userEmail")
        certificationApiImpl.postCertification(CertificationRequest(userEmail))
        _changeComment_3.value = "인증번호 전송을 완료했습니다"
        _firstPostCertificationCode.value = true
    }

    //인증 완료 후 바로 넘어감
    fun checkCertifcation(){
        certificationCode.value
        _successfulCertification.value = true

    }

}
