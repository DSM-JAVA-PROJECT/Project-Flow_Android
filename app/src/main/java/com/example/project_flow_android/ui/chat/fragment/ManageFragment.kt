package com.example.project_flow_android.ui.chat.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.RoomMemberResponse
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.ManageRVAdapter
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_manage.*
import kotlinx.android.synthetic.main.fragment_modify.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageFragment : Fragment() {

    private val chatViewModel : ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private lateinit var adapter : ManageRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoad(socket.getChatImage(), manage_project_iv)

        chatViewModel.getRoomMember(socket.getChatRoomId())
        val layoutManager = LinearLayoutManager(requireContext())
        manage_rv.layoutManager = layoutManager

        chatViewModel.roomMemberLiveData.observe(viewLifecycleOwner, {
            adapterInit(chatViewModel.roomMemberLiveData.value!!)
        })

        manage_name_tv.text = socket.getRoomName()
        manage_prev_iv.setOnClickListener{
            (activity as ChatActivity).popBackStack(ManageFragment())
        }

        manage_invite_tv.setOnClickListener{
            (activity as ChatActivity).replace(InviteFragment())
        }

        manage_change_name_tv.setOnClickListener{
            (activity as ChatActivity).replace(ModifyFragment())
        }
    }

    private fun imageLoad(image: String, iv: ImageView){
        iv.clipToOutline = true
        Glide.with(requireActivity()).load(Uri.parse(image)).into(iv)
    }

    private fun adapterInit(data: RoomMemberResponse) {
        adapter = ManageRVAdapter(data, requireActivity())
        manage_rv.adapter = adapter

        adapter.setOnItemClickListener(object: ManageRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, position: Int) {
                val userId = data.responses[position].id
                val fragment = UserInfoFragment()
                val bundle = Bundle()
                bundle.putString("userId", userId)
                fragment.arguments = bundle
                (activity as ChatActivity).replace(fragment)
            }
        })
    }
}