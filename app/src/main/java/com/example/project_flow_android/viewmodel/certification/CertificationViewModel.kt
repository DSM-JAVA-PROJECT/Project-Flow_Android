package com.example.project_flow_android.viewmodel.certification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.CertificationApiImpl
import com.example.project_flow_android.feature.CertificationRequest
import com.example.project_flow_android.feature.PostCertificationRequest

class CertificationViewModel(
    private val certificationApiImpl: CertificationApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    private val _firstPostCertificationCode = MutableLiveData(false)
    val firstPostCertificationCode: LiveData<Boolean> get() = _firstPostCertificationCode

    private val _secondPostCertificationCode = MutableLiveData(false)
    val secondPostCertificationCode: LiveData<Boolean> get() = _secondPostCertificationCode

    private val _changeComment_3 = MutableLiveData<String>()
    val changeComment3: LiveData<String> get() = _changeComment_3

    private val _successfulCertification = MutableLiveData(false)
    val successfulCertification: LiveData<Boolean> get() = _successfulCertification

    val certificationCode = MutableLiveData<String>()

    fun postCertification() {
        val userEmail = sharedPreferenceStorage.getInfo("userEmail")
        certificationApiImpl.postCertification(CertificationRequest(userEmail))
            .subscribe { subscribe ->
                when (subscribe.code()) {
                    200 -> {
                        _changeComment_3.value = "인증번호 전송을 완료했습니다"
                        _firstPostCertificationCode.value = true
                    }
                    else -> {
                        _changeComment_3.value = "인증번호 전송에 실패하였습니다. 다시 시도해주세요"
                    }
                }
            }
    }

    fun reSendCertificationCode() {
        val userEmail = sharedPreferenceStorage.getInfo("userEmail")
        certificationApiImpl.postCertification(CertificationRequest(userEmail))
            .subscribe { subscribe ->
                when (subscribe.code()) {
                    200 -> {
                        _changeComment_3.value = "재전송을 완료했습니다"
                        _secondPostCertificationCode.value = true
                    }
                    500 -> {
                        _changeComment_3.value = "인증번호 전송에 실패하였습니다"
                    }
                    else ->{
                        _changeComment_3.value = "인증번호 전송에 실패하였습니다"
                    }
                }
            }
    }

    fun checkCertifcation() {
        certificationApiImpl.checkCertification(PostCertificationRequest(certificationCode.value!!))
            .subscribe { subscribe ->
                when (subscribe.code()) {
                    200 -> {
                        _successfulCertification.value = true
                    }
                    400 -> {
                        _changeComment_3.value = subscribe.body().toString()
                    }
                    else -> {
                        _changeComment_3.value = subscribe.body().toString()
                    }
                }
                _changeComment_3.value = "인증을 다시 시도해주세요"
            }
    }
}
