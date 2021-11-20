package com.example.project_flow_android.viewmodel.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.model.sign.chat.*
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import kotlinx.coroutines.launch
import org.json.JSONObject

class ChatViewModel : ViewModel() {
    private val access_token =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzY0MzQyNDYsImlkIjoiNjE4OWZlOTcwYzliZmQyYjk4MDRmZjg2IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.6cNSlsTiL4UG4arInBRPaJjV4MeemeXmDiMZiDxXKVQ"
    private val sub_access =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzY0MzQyNjMsImlkIjoiNjE4OWZlYTMwYzliZmQyYjk4MDRmZjg3IiwiZW1haWwiOiJhYmgwOTIwb25lQG5hdmVyLmNvbSJ9.lklPsE4KpZRqSxi5EYahxxTeXigL47eYxbE3UL7ZtMY"
    private var projectId = "6194967186cfc21756269e3c"

    private val chatRepository = ChatRepositoryImpl()
    private val _chatLiveData: MutableLiveData<ProjectMemberResponse> = MutableLiveData()
    private val _chatRoomLiveData: MutableLiveData<RoomListResponse> = MutableLiveData()
    private val _roomMemberLiveData: MutableLiveData<RoomMemberResponse> = MutableLiveData()
    private val _messageListLiveData: MutableLiveData<ChatMessageResponse> = MutableLiveData()
    private val _userProfileLiveData: MutableLiveData<UserProfileResponse> = MutableLiveData()
    private val _modifyLiveData: MutableLiveData<Int> = MutableLiveData()
    val chatLiveData = _chatLiveData
    val chatRoomLiveData = _chatRoomLiveData
    val roomMemberLiveData = _roomMemberLiveData
    val messageListLiveData = _messageListLiveData
    val userProfileLiveData = _userProfileLiveData
    val modifyLiveData = _modifyLiveData

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

    fun setProjectId(projectId: String) {
        this.projectId = projectId
    }

    fun getProjectId() = projectId
}