package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.network.StompClient
import com.example.project_flow_android.util.GalleryHelper
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val galleryHelper = GalleryHelper(requireActivity())
        val socket = StompClient()
        socket.start()

        chat_more_iv.setOnClickListener{
            if(view_more.visibility == View.VISIBLE)
                view_more.visibility = View.GONE
            else
                view_more.visibility = View.VISIBLE
        }



        chat_photo_tv.setOnClickListener{
            galleryHelper.selectGallery()
        }
    }
}