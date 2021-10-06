package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_flow_android.R
import com.example.project_flow_android.ui.chat.CreateRVAdapter
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat_create.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatCreateFragment : Fragment() {
    private val chatViewModel : ChatViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProjectUser()
        val layoutManager = LinearLayoutManager(requireContext())
        chat_create_user_rv.layoutManager = layoutManager

        chatViewModel.chatLiveData.observe(viewLifecycleOwner, {
            val adapter = CreateRVAdapter(chatViewModel.chatLiveData.value!!)
            chat_create_user_rv.adapter = adapter
        })

        chat_create_btn.setOnClickListener{
            chatViewModel.createRoom()
        }
    }

    private fun getProjectUser(){
        chatViewModel.getProjectUser()
    }
}