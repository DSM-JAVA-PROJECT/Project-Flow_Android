package com.example.project_flow_android.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project_flow_android.R
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.fragment.ChatFragment
import com.example.project_flow_android.ui.chat.fragment.ChatListFragment
import com.example.project_flow_android.ui.chat.fragment.ManageFragment
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.chat_frame_container, ChatFragment()).commit()
    }

    fun replace(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.from_bottom, R.anim.to_top, R.anim.from_top, R.anim.to_bottom)
            .addToBackStack(null)
        fragmentTransaction.replace(R.id.chat_frame_container, fragment).commit()
    }

    fun popBackStack(fragment: Fragment){
        val fragmentManager = this.supportFragmentManager
        fragmentManager.beginTransaction().remove(fragment).commit()
        fragmentManager.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}