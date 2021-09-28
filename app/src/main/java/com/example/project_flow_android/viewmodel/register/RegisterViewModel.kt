package com.example.project_flow_android.viewmodel.register

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

class RegisterViewModel(
    private val signApiImpl: SignApiImpl,
    private val sharedPrefenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val userName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPhone = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val userRePassword = MutableLiveData<String>()
    val verifyCode = MutableLiveData<String>()

    private val _goLogin = MutableLiveData<String>()
    val goLogin: LiveData<String> get() = _goLogin

    private val _checkRegister = MutableLiveData(false)
    val checkRegister: LiveData<Boolean> get() = _checkRegister

    private val _nextRegister = MutableLiveData(false)
    val nextRegister: LiveData<Boolean> get() = _nextRegister

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    private val _changeComment_2 = MutableLiveData<String>()
    val changeComment2: LiveData<String> get() = _changeComment_2

    private val _changeComment_3 = MutableLiveData<String>()
    val changeComment3: LiveData<String> get() = _changeComment_3

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage


    fun leaveData() {
        with(sharedPrefenceStorage) {
            saveInfo(userName.value!!, "userName")
            saveInfo(userEmail.value!!, "userEmail")
            saveInfo(userPhone.value!!, "userPhone")
        }
        _nextRegister.value = true
    }


    fun addInfo() {
        if (userEmail.value == null && userPhone.value == null && userName.value == null) {
            _changeComment.value = "모든 정보를 입력해주세요"
        } else if (userEmail.value == null && userPhone.value == null)
            _changeComment.value = "이메일과 전화번호를 모두 입력해주세요"
        else if (userName.value == null && userPhone.value == null)
            _changeComment.value = "이름과 전화번호를 모두 입력해주세요"
        else if (userName.value == null || userEmail.value == null) {
            _changeComment.value = "이름과 이메일을 모두 입력해주세요"
        } else if (userPhone.value == null) {
            _changeComment.value = "전화번호를 입력해주세요"
        } else if (userEmail.value == null) {
            _changeComment.value = "이메일을 입력해주세요"
        } else if (userName.value == null) {
            _changeComment.value = "이름을 입력해주세요"
        } else leaveData()
    }
}
