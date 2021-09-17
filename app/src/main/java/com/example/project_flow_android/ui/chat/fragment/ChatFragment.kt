package com.example.project_flow_android.ui.chat.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.util.GalleryHelper
import com.example.project_flow_android.util.KeyboardUtil
import kotlinx.android.synthetic.main.add_schedule_bottom.*
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            Glide.with(requireContext()).load(it.data?.data).into(test_iv)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keyboardUtil = KeyboardUtil(requireContext())
        val dialogUtil = DialogUtil(requireContext())
        val galleryHelper = GalleryHelper(requireActivity(), startForResult)

        chat_more_iv.setOnClickListener{
            if(view_more.visibility == View.VISIBLE)
                view_more.visibility = View.GONE
            else{
                keyboardUtil.hideKeyboard(chat_input_et)
                view_more.visibility = View.VISIBLE
            }
        }

        chat_input_et.setOnClickListener{
            view_more.visibility = View.GONE
        }

        chat_photo_tv.setOnClickListener{
            galleryHelper.selectGallery()
        }

        chat_schedule_tv.setOnClickListener{
            (activity as ChatActivity).replace(ScheduleFragment())
        }
        chat_manage_tv.setOnClickListener{
            (activity as ChatActivity).replace(ManageFragment())
        }
        chat_add_schedule_tv.setOnClickListener{
            val bottom = dialogUtil.shoBottomSheet()
            bottom.show()
            bottom.add_schedule_start_et.setOnClickListener{
                dialogUtil.showDatePicker(bottom.add_schedule_start_et)
            }
            bottom.add_schedule_end_et.setOnClickListener{
                dialogUtil.showDatePicker(bottom.add_schedule_end_et)
            }
        }
    }
}