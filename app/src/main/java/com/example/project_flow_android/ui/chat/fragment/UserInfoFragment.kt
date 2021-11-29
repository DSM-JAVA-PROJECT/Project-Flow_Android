package com.example.project_flow_android.ui.chat.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.UserProfileResponse
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.UserProjectRVAdapter
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_user_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment : Fragment() {

    private val chatViewModel: ChatViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if(bundle != null){
            val userId = bundle.getString("userId")
            chatViewModel.getUserProfile(userId!!)
        }

        val layoutManager = LinearLayoutManager(requireContext())
        user_info_rv.layoutManager = layoutManager

        chatViewModel.userProfileLiveData.observe(viewLifecycleOwner, {
            val userInfo = chatViewModel.userProfileLiveData.value!!
            val adapter =
                UserProjectRVAdapter(userInfo, requireActivity())
            user_info_rv.adapter = adapter
            bind(userInfo)
        })

        user_info_prev_iv.setOnClickListener{
            (activity as ChatActivity).popBackStack(UserInfoFragment())
        }
    }

    private fun bind(userInfo: UserProfileResponse) {
        if(userInfo.profileImage != null){
            user_info_iv.clipToOutline = true
            Glide.with(requireActivity()).load(Uri.parse(userInfo.profileImage)).into(user_info_iv)
        }
        user_info_name_tv.text = userInfo.userName
        user_info_tel_tv.text = userInfo.phoneNumber
    }
}