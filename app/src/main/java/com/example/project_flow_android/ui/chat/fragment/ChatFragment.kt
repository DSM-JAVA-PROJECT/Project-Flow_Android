package com.example.project_flow_android.ui.chat.fragment

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.ChatRVAdapter
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.util.GalleryHelper
import com.example.project_flow_android.util.KeyboardUtil
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.add_schedule_bottom.*
import kotlinx.android.synthetic.main.fragment_chat.*
import org.aviran.cookiebar2.CookieBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {

    private val photoSelect =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                Glide.with(requireContext()).load(it.data?.data).into(test_iv)
            }
        }

    private val chatViewModel: ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private var planName = ""
    private val SIZE = 10
    private var page = 0
    private var isLoading = false
    private lateinit var adapter: ChatRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel.getChatList(socket.getChatRoomId(), getPage(), SIZE)
        getChatListMore()
        socket.chatReceive()
        socket.rejoin()
        chatViewModel.messageListLiveData.observe(viewLifecycleOwner, {
            adapterInit(chatViewModel.messageListLiveData.value!!)
        })

        val keyboardUtil = KeyboardUtil(requireContext())
        val dialogUtil = DialogUtil(requireActivity())
        val galleryHelper = GalleryHelper(requireActivity())

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.stackFromEnd = true
        chat_rv.layoutManager = layoutManager

        socket.receiveLiveData.observe(viewLifecycleOwner, {
            Log.i("it", it.toString())
            chatViewModel.messageListLiveData.value!!.oldChatMessageResponses.add(it)
            adapterInit(chatViewModel.messageListLiveData.value!!)

//            socket.readLiveData.observe(viewLifecycleOwner, {
//                val size = chatViewModel.messageListLiveData.value!!.oldChatMessageResponses.size
//                for (i in 0 until size) {
//                    val reader = socket.readLiveData.value
//                    chatViewModel.messageListLiveData.value!!.oldChatMessageResponses[i].readerList.remove(reader)
//                    adapter.notifyDataSetChanged()
//                }
//            })
        })
        socket.errorLiveData.observe(viewLifecycleOwner, {
            errorHandler(socket.errorLiveData.value!!)
        })

        chat_title_tv.text = socket.getRoomName()
        chat_more_iv.setOnClickListener {
            if (view_more.visibility == View.VISIBLE)
                view_more.visibility = View.GONE
            else {
                keyboardUtil.hideKeyboard(chat_input_et)
                view_more.visibility = View.VISIBLE
            }
        }

        chat_send_iv.setOnClickListener {
            if (chat_input_et.text.toString() != "") {
                val message = chat_input_et.text.toString().trim()
                socket.send(message)
                chat_input_et.setText("")
            }
        }

        chat_input_et.setOnClickListener {
            chat_rv.scrollToPosition(chat_rv.adapter!!.itemCount - 1)
            view_more.visibility = View.GONE
        }

        chat_photo_tv.setOnClickListener {
            galleryHelper.selectPhoto(photoSelect)
        }

        chat_schedule_tv.setOnClickListener {
            (activity as ChatActivity).replace(ScheduleFragment())
        }
        chat_manage_tv.setOnClickListener {
            page = 0
            (activity as ChatActivity).replace(ManageFragment())
        }
        chat_add_schedule_tv.setOnClickListener {
            view_more.visibility = View.GONE
            val bottom = dialogUtil.showBottomSheet()
            bottom.show()
            bottom.add_schedule_start_et.setOnClickListener {
                dialogUtil.showDatePicker(bottom.add_schedule_start_et)
            }
            bottom.add_schedule_end_et.setOnClickListener {
                dialogUtil.showDatePicker(bottom.add_schedule_end_et)
            }
            bottom.add_schedule_btn.setOnClickListener {
                if (bottom.add_schedule_content_et.text.toString() != "" &&
                    bottom.add_schedule_start_et.text.toString() != "" &&
                    bottom.add_schedule_end_et.text.toString() != ""
                ) {
                    socket.addPlan(
                        bottom.add_schedule_content_et.text.toString(),
                        bottom.add_schedule_start_et.text.toString(),
                        bottom.add_schedule_end_et.text.toString(),
                        bottom.add_schedule_switch.isChecked
                    )
                    bottom.dismiss()
                }
            }
        }
        chat_prev_btn.setOnClickListener {
            socket.setChatRoomId("")
            (activity as ChatActivity).popBackStack(ChatFragment())
        }
    }

    private fun getPage() = page++

    private fun adapterInit(data: ChatMessageResponse) {
        adapter = ChatRVAdapter(data, requireActivity())
        chat_rv.adapter = adapter
        chat_rv.scrollToPosition(chat_rv.adapter!!.itemCount - 1)

        adapter.setOnJoinClickListener(object : ChatRVAdapter.OnJoinClickListener {
            override fun onJoinClick(v: View, position: Int) {
                val planId =
                    chatViewModel.messageListLiveData.value!!.oldChatMessageResponses[position].planId!!
                socket.joinPlan(planId)
                planName =
                    chatViewModel.messageListLiveData.value!!.oldChatMessageResponses[position].planName!!
            }
        })
        adapter.setOnResignClickListener(object : ChatRVAdapter.OnResignClickListener {
            override fun onResignClick(v: View, position: Int) {
                val planId =
                    chatViewModel.messageListLiveData.value!!.oldChatMessageResponses[position].planId!!
                socket.resignPlan(planId)
            }
        })
        adapter.setOnReJoinClickListener(object : ChatRVAdapter.OnReJoinClickListener {
            override fun onReJoinClick(v: View, position: Int) {
                val planId =
                    chatViewModel.messageListLiveData.value!!.oldChatMessageResponses[position].planId!!
                socket.joinPlan(planId)
            }
        })
    }

    private fun getChatListMore() {
        chat_rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!isLoading && chatViewModel.messageListLiveData.value!!.hasNextPage) {
                    if(!chat_rv.canScrollVertically(-1)){
                        requireActivity().runOnUiThread{
                            val handler = Handler(Looper.getMainLooper())
                            handler.postDelayed({
                                isLoading = true
                                chatViewModel.getChatList(socket.getChatRoomId(), getPage(), SIZE)
                            }, 100)
                        }
                    }
                }
            }
        })
    }

    private fun errorHandler(status: Int) {
        when (status) {
            409 -> {
                CookieBar.build(requireActivity())
                    .setTitle(planName)
                    .setMessage(R.string.already_plan_participate)
                    .setTitleColor(R.color.white)
                    .setBackgroundColor(R.color.color_resign_plan)
                    .show()
            }
        }
    }
}