package com.example.project_flow_android.ui.chat.fragment

import android.app.Activity
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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
import kotlinx.android.synthetic.main.pin_dialog.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.aviran.cookiebar2.CookieBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class ChatFragment : Fragment() {

    private val photoSelect =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data?.data!!.path
                val uri = it.data?.data!!
                fileUpload(data!!, uri)
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
        chatViewModel.getPin(socket.getChatRoomId())
        socket.rejoin()
        getChatListMore()
        socket.chatReceive()
        chatViewModel.messageListLiveData.observe(viewLifecycleOwner, {
            Log.i("MessageLiveData", ".")
            if(!isLoading){
                adapterInit(chatViewModel.messageListLiveData.value!!)
            }
            else {
                chatViewModel.messageListLiveData.value!!.oldChatMessageResponses.addAll(0, it.oldChatMessageResponses)
            }
        })
        chatViewModel.pinLiveData.observe(viewLifecycleOwner, {
            it?.let {
                pin_layout.visibility = View.VISIBLE
                pin_message_tv.text = it.content
            }
        })
        chatViewModel.fileUpdateLiveData.observe(viewLifecycleOwner, {
            socket.sendImage(it.imageUrl)
        })

        val keyboardUtil = KeyboardUtil(requireContext())
        val dialogUtil = DialogUtil(requireActivity())
        val galleryHelper = GalleryHelper(requireActivity())

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.stackFromEnd = true
        chat_rv.layoutManager = layoutManager

        socket.receiveLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                chatViewModel.messageListLiveData.value!!.oldChatMessageResponses.add(it!!)
                adapterInit(chatViewModel.messageListLiveData.value!!)
            }
        })
        socket.pinLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                pin_layout.visibility = View.VISIBLE
                pin_message_tv.text = it.content
            }
        })
        socket.errorLiveData.observe(viewLifecycleOwner, {
            errorHandler(socket.errorLiveData.value!!.peekContent())
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
            bottom.closing_schedule_btn.setOnClickListener {
                if (bottom.input_schedule_content_tv.text.toString() != "" &&
                    bottom.add_schedule_start_et.text.toString() != "" &&
                    bottom.add_schedule_end_et.text.toString() != ""
                ) {
                    socket.addPlan(
                        bottom.input_schedule_content_tv.text.toString(),
                        bottom.add_schedule_start_et.text.toString(),
                        bottom.add_schedule_end_et.text.toString(),
                        bottom.add_schedule_switch.isChecked
                    )
                    bottom.dismiss()
                }
            }
        }
        chat_prev_btn.setOnClickListener {
            requireActivity().finish()
        }
        pin_delete_ib.setOnClickListener{
            dialogUtil.cookieBarBuilder(
                R.string.pin_message_remove,
                pin_message_tv.text.toString(),
                null,
                R.color.color_flow
            )
            socket.pinRemove()
            pin_layout.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        page = 0
        socket.resignRoom(socket.getChatRoomId())
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
        adapter.setOnShowClickListener(object : ChatRVAdapter.OnShowClickListener {
            override fun onShowClick(v: View, position: Int) {
                (activity as ChatActivity).replace(ScheduleFragment())
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
        adapter.setOnItemLongClickListener(object : ChatRVAdapter.OnItemLongClickListener {
            override fun onItemLongClick(v: View, position: Int) {
                val dialog = Dialog(requireContext())
                dialog.setContentView(R.layout.pin_dialog)
                dialog.show()

                dialog.pin_tv.setOnClickListener{
                    val message = chatViewModel.messageListLiveData.value!!.oldChatMessageResponses[position].id
                    socket.pin(message)
                    dialog.dismiss()
                }
            }
        })
    }

    private fun getChatListMore() {
        chat_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading && chatViewModel.messageListLiveData.value!!.hasNextPage) {
                    if (!chat_rv.canScrollVertically(-1)) {
                        requireActivity().runOnUiThread {
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

    private fun fileUpload(filePath: String, uri: Uri) {
        val file = File(filePath)
        var inputStream: InputStream? = null
        try {
            inputStream = requireContext().contentResolver.openInputStream(uri)
        } catch (e: Exception){
            e.printStackTrace()
        }
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val requestFile = byteArrayOutputStream.toByteArray().toRequestBody("image/jpg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        chatViewModel.fileUpload(body)
    }
}