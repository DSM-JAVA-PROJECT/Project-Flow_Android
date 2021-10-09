package com.example.project_flow_android.viewmodel.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.feature.RegisterRequest

class RegisterViewModel(
    private val signApiImpl: SignApiImpl,
    private val sharedPrefenceStorage: SharedPreferenceStorage,
) : ViewModel() {

    val userName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPhone = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val userRePassword = MutableLiveData<String>()

    private val _finishRegister = MutableLiveData(false)
    val finishRegister: LiveData<Boolean> get() = _finishRegister

    private val _nextRegister = MutableLiveData(false)
    val nextRegister: LiveData<Boolean> get() = _nextRegister

    private val _changeComment = MutableLiveData<String>()
    val changeComment: LiveData<String> get() = _changeComment

    private val _changeComment_2 = MutableLiveData<String>()
    val changeComment2: LiveData<String> get() = _changeComment_2

    private fun leaveData() {
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

    fun finishRegister() {
        val userEmail = sharedPrefenceStorage.getInfo("userEmail")
        val userName = sharedPrefenceStorage.getInfo("userName")
        val userPhonenumber = sharedPrefenceStorage.getInfo("userPhone")

        if (userPassword.value?.length ?: 0 in 8..16) {
            if (userPassword.value!! == userRePassword.value!!) {
                signApiImpl.registerApi(RegisterRequest(sharedPrefenceStorage.getInfo(userName),
                    sharedPrefenceStorage.getInfo(userEmail),
                    sharedPrefenceStorage.getInfo(userPhonenumber), userPassword.value!!))
                    .subscribe({
                        if (it.isSuccessful) {
                            _finishRegister.value = true
                        } else {
                            _changeComment_2.value = it.body().toString()
                        }
                    }, {
                        _changeComment_2.value = "회원가입에 실패하였습니다"
                    })
            } else {
                _changeComment_2.value = "작성 비밀번호가 일치하지 않습니다"
            }
        } else {
            _changeComment_2.value = "비밀번호를 8자에서 16자로 설정해주세요"
        }

    }
}
