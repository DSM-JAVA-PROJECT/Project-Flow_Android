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
import com.example.project_flow_android.ui.chat.InviteRVAdapter
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.chat_create_user_item.view.*
import kotlinx.android.synthetic.main.fragment_invite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InviteFragment : Fragment() {

    private val chatViewModel : ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private val userState = HashMap<Int, Boolean>()
    private val userEmail = ArrayList<String>()
    private val userName = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialogUtil = DialogUtil(requireActivity())
        val layoutInflater = LinearLayoutManager(requireContext())
        invite_rv.layoutManager = layoutInflater
        chatViewModel.getNonParticipate(socket.getChatRoomId())
        chatViewModel.participateLiveData.observe(viewLifecycleOwner, {
            val adapter = InviteRVAdapter(requireActivity(), chatViewModel.participateLiveData.value!!)
            invite_rv.adapter = adapter

            adapter.setOnItemClickListener(object : InviteRVAdapter.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    itemClick(v, position)
                }
            })
        })

        invite_prev_iv.setOnClickListener{
            (activity as ChatActivity).popBackStack(InviteFragment())
        }
        invite_btn.setOnClickListener{
            socket.inviteRoom(socket.getChatRoomId(), userEmail)
            dialogUtil.cookieBarBuilder(
                R.string.invite_user_title,
                userName.joinToString(" "),
                null,
                R.color.color_flow
            )
            (activity as ChatActivity).popBackStack(InviteFragment())
        }
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
        val user = chatViewModel.participateLiveData.value!!.responses[position]
        userState[position] = true
        userEmail.add(user.email)
        userName.add(user.name)

        v.create_user_cv.setBackgroundColor(resources.getColor(R.color.color_flow, null))
        v.create_user_item_name_tv.setTextColor(resources.getColor(R.color.white, null))
    }

    private fun itemDeselected(v: View, position: Int){
        val user = chatViewModel.participateLiveData.value!!.responses[position]
        userState[position] = false
        userEmail.remove(user.email)
        userName.add(user.name)

        v.create_user_cv.setBackgroundColor(resources.getColor(R.color.white, null))
        v.create_user_item_name_tv.setTextColor(resources.getColor(R.color.black, null))
    }
}