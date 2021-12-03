package com.example.project_flow_android.ui.chat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_flow_android.R
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.activity_chat_create.*
import kotlinx.android.synthetic.main.chat_create_user_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatCreateActivity : AppCompatActivity() {
    private val chatViewModel: ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private val userState = HashMap<Int, Boolean>()
    private val userList = ArrayList<String>()
    private val userEmail = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_create)

        getProjectUser()
        val dialogUtil = DialogUtil(this)
        val layoutManager = LinearLayoutManager(this)
        chat_create_user_rv.layoutManager = layoutManager

        chatViewModel.chatLiveData.observe(this, {
            val adapter = CreateRVAdapter(this, chatViewModel.chatLiveData.value!!)
            chat_create_user_rv.adapter = adapter

            adapter.setOnItemClickListener(object : CreateRVAdapter.OnItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    itemClick(v, position)
                }
            })
        })

        chat_create_btn.setOnClickListener {
            socket.createRoom(userEmail)
            dialogUtil.cookieBarBuilder(
                R.string.chat_room_create_title,
                "${getString(R.string.chat_room_create_content)} ${userEmail.size + 1}명",
                null,
                R.color.color_calendar_current
            )
            runOnUiThread {
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    finish()
                }, 2000)
            }
        }
    }

    private fun getProjectUser() {
        chatViewModel.getProjectUser()
    }

    private fun itemClick(v: View, position: Int) {
        if (userState[position] == null) {
            itemSelected(v, position)
        } else {
            if (userState[position]!!) itemDeselected(v, position)
            else itemSelected(v, position)
        }
    }

    private fun itemSelected(v: View, position: Int) {
        userState[position] = true
        userList.add(v.create_user_item_name_tv.text.toString())
        userEmail.add(chatViewModel.chatLiveData.value!!.responses[position].id)
        val user = userList.joinToString(" ")

        v.create_user_cv.setBackgroundColor(resources.getColor(R.color.color_flow, null))
        v.create_user_item_name_tv.setTextColor(resources.getColor(R.color.white, null))
        chat_create_user_name_tv.text = user
    }

    private fun itemDeselected(v: View, position: Int) {
        userState[position] = false
        userList.remove(v.create_user_item_name_tv.text.toString())
        userEmail.remove(chatViewModel.chatLiveData.value!!.responses[position].id)
        val user = userList.joinToString(" ")

        v.create_user_cv.setBackgroundColor(resources.getColor(R.color.white, null))
        v.create_user_item_name_tv.setTextColor(resources.getColor(R.color.black, null))
        chat_create_user_name_tv.text = user
    }
}