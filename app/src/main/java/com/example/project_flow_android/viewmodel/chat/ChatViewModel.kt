package com.example.project_flow_android.viewmodel.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.chat.ProjectMemberResponse
import com.example.project_flow_android.data.chat.RoomListResponse
import com.example.project_flow_android.data.model.sign.chat.*
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.json.JSONObject

class ChatViewModel : ViewModel() {
    //private val access_token =
    //    "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2Mzc2NTAyODgsImlkIjoiNjE5YzhmNjk4ZDZlMjY3MzRiNTExY2M5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.kZkCt0TiXeWjT-zPwnDOENmLA3WB_NQg7yd4zAo2R1Q"
    private val access_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzgxNzcwNzYsImV4cCI6MTYzODI2MzQ3NiwiaWQiOiI2MTljOTQzMzhkNmUyNjczNGI1MTFjY2QiLCJlbWFpbCI6InduZHVmMDQwNV9AbmF2ZXIuY29tIn0.ovevh3CFd9N7p1hK028bLnPdCPb45jJne7SL651XCu8"
    private val sub_access =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2Mzc2NTAzMDQsImlkIjoiNjE5YzhmNWU4ZDZlMjY3MzRiNTExY2M4IiwiZW1haWwiOiJhYmgwOTIwb25lQG5hdmVyLmNvbSJ9.chufW3OWC_lhzHeFQUDOjJA2b_Kx_01ls6_wgi0Etow"
    //private var projectId = "61a4d2e7b9d4a60b9a6a7ebc"
    private var projectId = "61a4ecffb9d4a60b9a6a7ebe"

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
    val chatLiveData = _chatLiveData
    val chatRoomLiveData = _chatRoomLiveData
    val roomMemberLiveData = _roomMemberLiveData
    val messageListLiveData = _messageListLiveData
    val userProfileLiveData = _userProfileLiveData
    val modifyLiveData = _modifyLiveData
    val fileUpdateLiveData = _fileUploadLiveData
    val imageUpdateLiveData = _imageUpdateLiveData
    val participateLiveData = _participateLiveData

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

    fun setProjectId(projectId: String) {
        this.projectId = projectId
    }

    fun getProjectId() = projectId
}