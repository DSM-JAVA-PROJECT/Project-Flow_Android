package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_flow_android.R
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.RoomRVAdapter
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment: Fragment() {
    private val chatViewModel : ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel.getRoomList()
        val layoutManager = LinearLayoutManager(requireContext())
        chat_list_rv.layoutManager = layoutManager

        chatViewModel.chatRoomLiveData.observe(viewLifecycleOwner, {
            val adapter = RoomRVAdapter(chatViewModel.chatRoomLiveData.value!!, requireActivity())
            chat_list_rv.adapter = adapter

            adapter.setOnItemClickListener(object : RoomRVAdapter.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    itemClick(v, position)
                }

            })
        })

        chat_list_add_iv.setOnClickListener{
            (activity as ChatActivity).replace(ChatCreateFragment())
        }
    }

    private fun itemClick(v: View, position: Int) {
        socket.setChatRoomId(chatViewModel.chatRoomLiveData.value!!.responses[position].id)
        socket.setChatImage(chatViewModel.chatRoomLiveData.value!!.responses[position].chatRoomImage)
        (activity as ChatActivity).replace(ChatFragment())
    }
}