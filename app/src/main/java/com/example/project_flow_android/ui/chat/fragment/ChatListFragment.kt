package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_flow_android.R
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.RoomRVAdapter
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : Fragment() {
    private val chatViewModel : ChatViewModel by viewModel()

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
            val adapter = RoomRVAdapter(chatViewModel.chatRoomLiveData.value!!)
            chat_list_rv.adapter = adapter
        })

        chat_list_add_iv.setOnClickListener{
            (activity as ChatActivity).replace(ChatCreateFragment())
        }
    }
}