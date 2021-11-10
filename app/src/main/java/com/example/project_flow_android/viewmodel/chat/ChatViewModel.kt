package com.example.project_flow_android.viewmodel.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.chat.ProjectMemberResponse
import com.example.project_flow_android.data.chat.RoomListResponse
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import com.example.project_flow_android.data.model.sign.chat.RoomMemberResponse
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val access_token =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzQyODMzMjMsImlkIjoiNjE2N2JhNTQyNjdjYTEwZWI1NDkwNGE5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.Y_smWBnm1RrvToFW9kB9pHhnmgZIu0O73OZH4Cy3iZ4"
    private val sub_access = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzU5ODgxMjUsImlkIjoiNjE4MzMyOTU0MzliNGU1Y2VhMjNhNjg0IiwiZW1haWwiOiJhYmgwOTIwb25lQG5hdmVyLmNvbSJ9.gO6C_afNJvyQoKTC5CN-cvhZuZaQRC5dHg9ptssTBag"
    private var projectId = "618333504aa95ded53f3b359"

    private val chatRepository = ChatRepositoryImpl()
    private val _chatLiveData: MutableLiveData<ProjectMemberResponse> = MutableLiveData()
    private val _chatRoomLiveData: MutableLiveData<RoomListResponse> = MutableLiveData()
    private val _roomMemberLiveData: MutableLiveData<RoomMemberResponse> = MutableLiveData()
    private val _messageListLiveData: MutableLiveData<ChatMessageResponse> = MutableLiveData()
    val chatLiveData = _chatLiveData
    val chatRoomLiveData = _chatRoomLiveData
    val roomMemberLiveData = _roomMemberLiveData
    val messageListLiveData = _messageListLiveData

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
            if(response.isSuccessful) {
                if(response.code() == 200){
                    _roomMemberLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getChatList(chatRoomId: String, page: Int, size: Int){
        viewModelScope.launch {
            val response = chatRepository.getChatList(access_token, chatRoomId, page, size)
            if(response.isSuccessful) {
                if(response.code() == 200) {
                    _messageListLiveData.postValue(response.body())
                }
            }
        }
    }

    fun setProjectId(projectId: String) {
        this.projectId = projectId
    }

    fun getProjectId() = projectId
}