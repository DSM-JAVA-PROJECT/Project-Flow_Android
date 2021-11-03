package com.example.project_flow_android.viewmodel.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_flow_android.data.model.sign.chat.ProjectMemberResponse
import com.example.project_flow_android.data.model.sign.chat.RoomListResponse
import com.example.project_flow_android.data.remote.chat.ChatRepositoryImpl
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val access_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXJ2ZXIiLCJpYXQiOjE2MzQyODMzMjMsImlkIjoiNjE2N2JhNTQyNjdjYTEwZWI1NDkwNGE5IiwiZW1haWwiOiJhYmgwOTIwb25lQGdtYWlsLmNvbSJ9.Y_smWBnm1RrvToFW9kB9pHhnmgZIu0O73OZH4Cy3iZ4"
    private var chatRoomId = ""
    private var projectId = "616c247724ffea704e117e6e"

    private val chatRepository = ChatRepositoryImpl()
    private val _chatLiveData : MutableLiveData<ProjectMemberResponse> = MutableLiveData()
    private val _chatRoomLiveData : MutableLiveData<RoomListResponse> = MutableLiveData()
    val chatLiveData = _chatLiveData
    val chatRoomLiveData = _chatRoomLiveData

    fun getProjectUser(){
        viewModelScope.launch {
            val response = chatRepository.getProjectUser(access_token, projectId)
            if(response.isSuccessful){
                if(response.code() == 200){
                    _chatLiveData.postValue(response.body())
                }
            }
        }
    }

    fun getRoomList(){
        viewModelScope.launch {
            val response = chatRepository.getRoomList(access_token, projectId)
            if(response.isSuccessful){
                if(response.code() == 200){
                    _chatRoomLiveData.postValue(response.body())
                }
            }
        }
    }

    fun setChatRoomId(chatRoomId: String){
        this.chatRoomId = chatRoomId
    }

    fun setProjectId(projectId: String){
        this.projectId = projectId
    }

    fun getProjectId() = projectId

    fun getChatRoomId() = chatRoomId
}