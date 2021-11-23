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
import com.example.project_flow_android.ui.chat.CreateRVAdapter
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.chat_create_user_item.view.*
import kotlinx.android.synthetic.main.fragment_chat_create.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatCreateFragment : Fragment() {
    private val chatViewModel : ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private val userState = HashMap<Int, Boolean>()
    private val userList = ArrayList<String>()
    private val userEmail = ArrayList<String>()
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
        val dialogUtil = DialogUtil(requireActivity())
        val layoutManager = LinearLayoutManager(requireContext())
        chat_create_user_rv.layoutManager = layoutManager

        chatViewModel.chatLiveData.observe(viewLifecycleOwner, {
            val adapter = CreateRVAdapter(chatViewModel.chatLiveData.value!!)
            chat_create_user_rv.adapter = adapter

            adapter.setOnItemClickListener(object : CreateRVAdapter.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    itemClick(v, position)
                }
            })
        })

        chat_create_btn.setOnClickListener{
            socket.createRoom(userEmail)
            dialogUtil.cookieBarBuilder(
                R.string.chat_room_create_title,
                "${getString(R.string.chat_room_create_content)} ${userEmail.size + 1}명",
                null,
                R.color.color_calendar_current
            )
            (activity as ChatActivity).replace(ChatListFragment())
        }
    }

    private fun getProjectUser(){
        chatViewModel.getProjectUser()
    }

    private fun itemClick(v: View, position: Int){
        if(userState[position] == null){
            itemSelected(v, position)
        }
        else{
            if(userState[position]!!) itemDeselected(v, position)
            else itemSelected(v, position)
        }
    }

    private fun itemSelected(v: View, position: Int){
        userState[position] = true
        userList.add(v.create_user_item_name_tv.text.toString())
        userEmail.add(chatViewModel.chatLiveData.value!!.responses[position].id)
        val user = userList.joinToString(" ")

        v.create_user_cv.setBackgroundColor(resources.getColor(R.color.color_flow, null))
        v.create_user_item_name_tv.setTextColor(resources.getColor(R.color.white, null))
        chat_create_user_name_tv.text = user
    }

    private fun itemDeselected(v: View, position: Int){
        userState[position] = false
        userList.remove(v.create_user_item_name_tv.text.toString())
        userEmail.remove(chatViewModel.chatLiveData.value!!.responses[position].id)
        val user = userList.joinToString(" ")

        v.create_user_cv.setBackgroundColor(resources.getColor(R.color.white, null))
        v.create_user_item_name_tv.setTextColor(resources.getColor(R.color.black, null))
        chat_create_user_name_tv.text = user
    }
}