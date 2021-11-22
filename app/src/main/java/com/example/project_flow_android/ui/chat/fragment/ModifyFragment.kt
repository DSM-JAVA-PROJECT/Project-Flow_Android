package com.example.project_flow_android.ui.chat.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ModifyNameRequest
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.util.KeyboardUtil
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_modify.*
import org.aviran.cookiebar2.CookieBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyFragment : Fragment() {

    private val chatViewModel: ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private val dialogUtil = DialogUtil(requireActivity())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoad(socket.getChatImage(), modify_project_iv)
        val keyboardUtil = KeyboardUtil(requireContext())

        chatViewModel.modifyLiveData.observe(viewLifecycleOwner, {
            notify(chatViewModel.modifyLiveData.value!!)
        })

        modify_prev_iv.setOnClickListener {
            keyboardUtil.hideKeyboard(modify_name_et)
            (activity as ChatActivity).popBackStack(ModifyFragment())
        }
        modify_btn.setOnClickListener {
            keyboardUtil.hideKeyboard(modify_name_et)
            val name = modify_name_et.text.toString()
            if (name == "") {
                notify(0)
            } else {
                modifyName(name)
            }
        }
    }

    private fun imageLoad(image: String, iv: ImageView) {
        iv.clipToOutline = true
        Glide.with(requireActivity()).load(Uri.parse(image)).into(iv)
    }

    private fun modifyName(name: String) {
        chatViewModel.modifyRoomName(socket.getChatRoomId(), ModifyNameRequest(name))
    }

    private fun notify(status: Int) {
        when (status) {
            0 -> {
                dialogUtil.cookieBarBuilder(R.string.cant_modify_room_name_title,
                    null,
                    R.string.cant_modify_room_name_content,
                    R.color.color_resign_plan)
            }
            200 -> {
                val name = modify_name_et.text.toString()
                dialogUtil.cookieBarBuilder(R.string.modify_room_name_title,
                    name,
                    null,
                    R.color.color_calendar_current)
                socket.setRoomName(name)
            }
        }
    }
}