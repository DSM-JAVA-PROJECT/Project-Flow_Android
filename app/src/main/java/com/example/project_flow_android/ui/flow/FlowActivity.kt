package com.example.project_flow_android.ui.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project_flow_android.R
import com.example.project_flow_android.ui.chat.fragment.ChatListFragment

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flow_frame_container, FlowFragment()).commit()
    }
}