package com.example.project_flow_android.viewmodel.certification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.sign.CertificationApiImpl
import com.example.project_flow_android.data.remote.sign.SignApiImpl
import com.example.project_flow_android.feature.CertificationRequest
import com.example.project_flow_android.feature.PostCertificationRequest
import com.example.project_flow_android.feature.RegisterRequest

class CertificationViewModel(
    private val certificationApiImpl: CertificationApiImpl,
    private val signApiImpl: SignApiImpl,
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

    private fun doRegister(){
        val leaveUserName = sharedPreferenceStorage.getInfo("userName")
        val leaveUserEmail = sharedPreferenceStorage.getInfo("userEmail")
        val leaveUserPassword = sharedPreferenceStorage.getInfo("userPassword")
        val leaveUserPhone = sharedPreferenceStorage.getInfo("userPhone")

        val request = RegisterRequest(leaveUserName,leaveUserEmail,leaveUserPhone,leaveUserPassword)
        signApiImpl.registerApi(request).subscribe{response ->
            when(response.code()){
                201 -> {
                    _successfulCertification.value = true
                }
                400 -> {
                    _changeComment_3.value = "이미 가입된 이메일 입니다😮‍💨"
                }
                else -> {
                    _changeComment_3.value ="회원가입에 실패하였습니다😮‍💨"
                }
            }
        }

    }

    fun checkCertifcation() {
        certificationApiImpl.checkCertification(PostCertificationRequest(certificationCode.value!!))
            .subscribe { subscribe ->
                when (subscribe.code()) {
                    200 -> {
                        doRegister()
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
