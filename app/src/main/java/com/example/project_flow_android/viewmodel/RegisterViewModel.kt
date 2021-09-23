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



    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    private val _changeComment_2 = MutableLiveData<String>()
    val changeComment2: LiveData<String> get() = _changeComment_2

    private val _changeComment_3 = MutableLiveData<String>()
    val changeComment3: LiveData<String> get() = _changeComment_3

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    //null check
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
    //add register info
    fun editRegisterInfo(){
        checkNull()
        if(nextRegister.value!!){
            sharedPrefenceStorage.saveInfo(userName.value!!,"userName")
            sharedPrefenceStorage.saveInfo(userEmail.value!!,"userEmail")
            sharedPrefenceStorage.saveInfo(userPhone.value!!,"userPhone")
        }
    }

    //이게 로그인으로 이동
    fun goLogin() {
        goLogin.value!!
    }
}