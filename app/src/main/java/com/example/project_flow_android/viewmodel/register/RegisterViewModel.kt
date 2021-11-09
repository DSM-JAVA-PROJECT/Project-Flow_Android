package com.example.project_flow_android.viewmodel.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.data.SharedPreferenceStorage

class RegisterViewModel(
    private val sharedPrefenceStorage: SharedPreferenceStorage
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


    fun leavePassword() {
        if (userPassword.value == userRePassword.value) {
            if (userPassword.value?.length ?: 0 in 8..16) {
                with(sharedPrefenceStorage) {
                    saveInfo(userPassword.value!!, "userPassword")
                }
                _finishRegister.value = true
            } else {
                _changeComment_2.value = "비밀번호는 8~16자로 입력해주세요😳"
            }
        } else {
            _changeComment_2.value = "비밀번호가 일치하는지 확인해주세요😳"
        }

    }
}
