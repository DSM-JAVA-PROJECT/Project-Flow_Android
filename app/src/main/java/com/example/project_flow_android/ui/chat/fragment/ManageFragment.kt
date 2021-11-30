package com.example.project_flow_android.ui.chat.fragment

import android.app.Activity
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ImageUpdateRequest
import com.example.project_flow_android.data.model.sign.chat.RoomMemberResponse
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.ManageRVAdapter
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.util.GalleryHelper
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_manage.*
import kotlinx.android.synthetic.main.fragment_modify.*
import kotlinx.android.synthetic.main.room_exit_dialog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.net.URI

class ManageFragment : Fragment() {

    private val photoSelect =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                //Glide.with(requireContext()).load(it.data?.data).into(manage_project_iv)
                val data = it.data?.data!!.path
                val uri = it.data?.data!!
                fileUpload(data!!, uri)
            }
        }

    private val chatViewModel: ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private lateinit var adapter: ManageRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoad(socket.getChatImage(), manage_project_iv)
        val galleryHelper = GalleryHelper(requireActivity())
        val dialogUtil = DialogUtil(requireActivity())

        chatViewModel.getRoomMember(socket.getChatRoomId())
        val layoutManager = LinearLayoutManager(requireContext())
        manage_rv.layoutManager = layoutManager

        chatViewModel.roomMemberLiveData.observe(viewLifecycleOwner, {
            adapterInit(chatViewModel.roomMemberLiveData.value!!)
        })
        chatViewModel.fileUpdateLiveData.observe(viewLifecycleOwner, {
            imageUpdate(socket.getChatRoomId(), it.imageUrl)
        })
        chatViewModel.imageUpdateLiveData.observe(viewLifecycleOwner, {
            when(it){
                200 ->{
                    imageLoad(chatViewModel.fileUpdateLiveData.value!!.imageUrl, manage_project_iv)
                    dialogUtil.cookieBarBuilder(
                        R.string.chat_room_image_change_title,
                        socket.getRoomName(),
                        null,
                        R.color.color_calendar_current
                    )
                }
            }
        })

        manage_name_tv.text = socket.getRoomName()
        manage_prev_iv.setOnClickListener {
            (activity as ChatActivity).popBackStack(ManageFragment())
        }
        manage_invite_tv.setOnClickListener {
            (activity as ChatActivity).replace(InviteFragment())
        }
        manage_change_name_tv.setOnClickListener {
            (activity as ChatActivity).replace(ModifyFragment())
        }
        manage_project_iv.setOnClickListener {
            galleryHelper.selectPhoto(photoSelect)
        }
        manage_exit_room_tv.setOnClickListener {
            val dialog = Dialog(requireActivity())
            dialog.setContentView(R.layout.room_exit_dialog)
            dialog.show()
            dialog.exit_positive.setOnClickListener{
                socket.leaveRoom(socket.getChatRoomId())
                dialogUtil.cookieBarBuilder(
                    R.string.leave_room_title,
                    socket.getRoomName(),
                    null,
                    R.color.color_flow
                )
                dialog.dismiss()

                (activity as ChatActivity).replace(ChatListFragment())
            }
            dialog.exit_negative.setOnClickListener{
                dialog.dismiss()
            }
        }
    }

    private fun imageLoad(image: String, iv: ImageView) {
        iv.clipToOutline = true
        Glide.with(requireActivity()).load(Uri.parse(image)).into(iv)
    }

    private fun adapterInit(data: RoomMemberResponse) {
        adapter = ManageRVAdapter(data, requireActivity())
        manage_rv.adapter = adapter

        adapter.setOnItemClickListener(object : ManageRVAdapter.OnItemClickListener {
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

    private fun imageUpdate(chatRoomId: String, uri: String) {
        val body = ImageUpdateRequest(uri)
        chatViewModel.imageUpdate(chatRoomId, body)
    }
}