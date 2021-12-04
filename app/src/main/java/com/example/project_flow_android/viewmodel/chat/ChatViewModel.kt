package com.example.project_flow_android.viewmodel.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.data.chat.ProjectMemberResponse
import com.example.project_flow_android.data.chat.RoomListResponse
import com.example.project_flow_android.data.model.sign.chat.*
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import com.example.project_flow_android.di.ProjectFlowApplication
import com.example.project_flow_android.util.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.json.JSONObject

class ChatViewModel : ViewModel() {

    private val prefs = SharedPreferenceStorage(ProjectFlowApplication.context)
    private val access_token = prefs.getInfo("access_token")
    private var projectId = prefs.getProjectId("projectId")

    private val chatRepository = ChatRepositoryImpl()
    private val _chatLiveData: MutableLiveData<ProjectMemberResponse> = MutableLiveData()
    private val _chatRoomLiveData: MutableLiveData<RoomListResponse> = MutableLiveData()
    private val _roomMemberLiveData: MutableLiveData<RoomMemberResponse> = MutableLiveData()
    private val _messageListLiveData: MutableLiveData<ChatMessageResponse> = MutableLiveData()
    private val _userProfileLiveData: MutableLiveData<UserProfileResponse> = MutableLiveData()
    private val _modifyLiveData: MutableLiveData<Int> = MutableLiveData()
    private val _fileUploadLiveData: MutableLiveData<FileResponse> = MutableLiveData()
    private val _imageUpdateLiveData: MutableLiveData<Int> = MutableLiveData()
    private val _participateLiveData: MutableLiveData<NonParticipateResponse> = MutableLiveData()
    private val _pinLiveData: MutableLiveData<GetPinResponse> = MutableLiveData()
    private val _monthPlanLiveData: MutableLiveData<Event<MonthPlanResponse>> = MutableLiveData()
    private val _datePlanLiveData: MutableLiveData<Event<DatePlanResponse>> = MutableLiveData()
    private val _deletePlanLiveData: MutableLiveData<Event<Int>> = MutableLiveData()
    private val _resignPlanLiveData: MutableLiveData<Event<Int>> = MutableLiveData()
    val chatLiveData = _chatLiveData
    val chatRoomLiveData = _chatRoomLiveData
    val roomMemberLiveData = _roomMemberLiveData
    val messageListLiveData = _messageListLiveData
    val userProfileLiveData = _userProfileLiveData
    val modifyLiveData = _modifyLiveData
    val fileUpdateLiveData = _fileUploadLiveData
    val imageUpdateLiveData = _imageUpdateLiveData
    val participateLiveData = _participateLiveData
    val pinLiveData = _pinLiveData
    val monthPlanLiveData : LiveData<Event<MonthPlanResponse>> get() = _monthPlanLiveData
    val datePlanLiveData: LiveData<Event<DatePlanResponse>> get() = _datePlanLiveData
    val deletePlanLiveData: LiveData<Event<Int>> get() = _deletePlanLiveData
    val resignPlanLiveData: LiveData<Event<Int>> get() = _resignPlanLiveData

    fun getProjectUser() {
        viewModelScope.launch {
            val response = chatRepository.getProjectUser(access_token, projectId)
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    _chatLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getRoomList() {
        viewModelScope.launch {
            val response = chatRepository.getRoomList(access_token, projectId)
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    _chatRoomLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getRoomMember(chatRoomId: String) {
        viewModelScope.launch {
            val response = chatRepository.getRoomMember(access_token, chatRoomId)
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    _roomMemberLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getChatList(chatRoomId: String, page: Int, size: Int) {
        viewModelScope.launch {
            val response = chatRepository.getChatList(access_token, chatRoomId, page, size)
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    _messageListLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getUserProfile(userId: String) {
        viewModelScope.launch {
            val response = chatRepository.getUserProfile(access_token, userId)
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    _userProfileLiveData.postValue(response.body())
                }
            }
        }
    }

    fun modifyRoomName(chatRoomId: String, name: ModifyNameRequest) {
        viewModelScope.launch {
            val response = chatRepository.modifyRoomName(access_token, chatRoomId, name)
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    _modifyLiveData.postValue(response.code())
                }
            }
        }
    }

    fun fileUpload(file: MultipartBody.Part) {
        viewModelScope.launch {
            val response = chatRepository.fileUpload(access_token, file)
            if (response.isSuccessful) {
                _fileUploadLiveData.postValue(response.body())
            }
        }
    }

    fun imageUpdate(chatRoomId: String, imageUrl: ImageUpdateRequest) {
        viewModelScope.launch {
            val response = chatRepository.imageUpdate(access_token, chatRoomId, imageUrl)
            if(response.isSuccessful) {
                _imageUpdateLiveData.postValue(response.code())
            }
        }
    }

    fun getNonParticipate(chatRoomId: String) {
        viewModelScope.launch {
            val response = chatRepository.getNonParticipate(access_token, projectId, chatRoomId)
            if(response.isSuccessful) {
                _participateLiveData.postValue(response.body())
            }
        }
    }

    fun getPin(chatRoomId: String) {
        viewModelScope.launch {
            val response = chatRepository.getPin(access_token, chatRoomId)
            if(response.isSuccessful) {
                _pinLiveData.postValue(response.body())
            }
        }
    }

    fun getMonthPlan(year: String, month: String){
        viewModelScope.launch {
            val response = chatRepository.getMonthPlan(access_token, projectId, year, month)
            if(response.isSuccessful) {
                _monthPlanLiveData.postValue(Event(response.body()!!))
            }
        }
    }

    fun getDatePlan(date: String){
        viewModelScope.launch {
            val response = chatRepository.getDatePlan(access_token, projectId, date)
            if(response.isSuccessful){
                _datePlanLiveData.postValue(Event(response.body()!!))
            }
        }
    }

    fun deletePlan(planId: String){
        viewModelScope.launch {
            val response = chatRepository.deletePlan(access_token, planId)
            if(response.isSuccessful){
                _deletePlanLiveData.postValue(Event(response.code()))
            }
        }
    }

    fun resignPlan(planId: String, chatRoomId: String){
        viewModelScope.launch {
            val response = chatRepository.resignPlan(access_token, chatRoomId, planId)
            if(response.isSuccessful){
                _resignPlanLiveData.postValue(Event(response.code()))
            }
        }
    }

    fun setProjectId(projectId: String) {
        this.projectId = projectId
    }

    fun getProjectId() = projectId
}