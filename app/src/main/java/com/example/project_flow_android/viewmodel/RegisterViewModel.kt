package com.example.project_flow_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.feature.CertificationRequest
import com.example.project_flow_android.feature.RegisterRequest
import retrofit2.Callback
import com.example.project_flow_android.network.ApiProvider
import com.example.project_flow_android.network.ProjectFlowAPI
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel(private val signApiImpl: SignApiImpl, private val sharedPrefenceStorage: SharedPreferenceStorage) : ViewModel() {

    val userName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPhone = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val userRePassword = MutableLiveData<String>()
    val verifyCode = MutableLiveData<String>()

    val nextRegister = MutableLiveData<Boolean>(false)
    val goLogin = MutableLiveData<Boolean>(false)
    val finalRegister = MutableLiveData<Boolean>(false)

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    private val _changeComment_2 = MutableLiveData<String>()
    val changeComment2: LiveData<String> get() = _changeComment_2

    private val _changeComment_3 = MutableLiveData<String>()
    val changeComment3: LiveData<String> get() = _changeComment_3

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage


    fun checkNull() {
        if (userName.value == null) {
            _changeComment.value = "이름을 입력해주세요"
        } else if (userEmail.value == null)
            _changeComment.value = "이메일을 입력해주세요"
        else if (userPhone.value == null)
            _changeComment.value = "핸드폰 번호를 입력해주세요"
        else if (userName.value == null || userEmail.value == null) {
            _changeComment.value = "이름과 핸드폰 번호를 모두 입력해주세요"
        }
    }

    fun nextRegister() {
//        if (nextRegister.value!!) {
//            val userEmail = sharedPrefenceStorage.saveInfo(userEmail.value!!, "userEmail")
//            sharedPrefenceStorage.saveInfo(userPhone.value!!, "userPhone")
//
//            val certificationCall = registerInterface.doCertification(
//                CertificationRequest(
//                   userEmail.toString()
//                )
//            )
//            certificationCall.enqueue(object : Callback<Unit>{
//                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                    _toastMessage.value = "인증번호를 발송하였습니다."
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//                    _toastMessage.value = "인증번호를 발송에 실패하였습니다"
//                }
//
//            })
//        }

    }

    fun livePassword() {
        if (finalRegister.value!!) {
            sharedPrefenceStorage.saveInfo(userPassword.value!!, "userPassword")
        }
    }

    fun doLogin() {
//        checkNull()
//        if (userPassword.value?.length.toString() >= 8.toString() || userPassword.value?.length.toString() <= 20.toString()) {
//            if (userPassword == userRePassword) {
//                val registerCall = signApiImpl.loginApi(userPassword.value!!,)
//                    RegisterRequest(
//                        sharedPrefenceStorage.getInfo(userName.value!!),
//                        sharedPrefenceStorage.getInfo(userEmail.value!!),
//                        sharedPrefenceStorage.getInfo(userPhone.value!!),
//                        sharedPrefenceStorage.getInfo(userPassword.value!!)
//                    )
//                )
//                registerCall.enqueue(object : Callback<Unit> {
//                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                        while (response.isSuccessful) {
//                            _toastMessage.value = "성공"
//                            livePassword()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Unit>, t: Throwable) {
//                        _toastMessage.value = "회원가입 실패"
//                    }
//                })
//            }
//            _changeComment_2.value = "비밀번호가 일치하지 않아요"
//        }
//        _toastMessage.value = "비밀번호는 8자~20자로 입력하세요"
    }


    //이게 로그인으로 이동
    fun goLogin() {
        goLogin.value!!
    }
}