package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.network.StompClient
import com.example.project_flow_android.ui.chat.ChatActivity
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.koin.android.ext.android.inject

class ChatListFragment : Fragment() {
    private val stompClient: StompClient by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(stompClient.state == Thread.State.NEW){
            stompClient.start()
        }

        chat_list_add_iv.setOnClickListener{
            (activity as ChatActivity).replace(ChatCreateFragment())
        }
    }
}