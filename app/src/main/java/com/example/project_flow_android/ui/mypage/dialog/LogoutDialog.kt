package com.example.project_flow_android.ui.mypage.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogLogoutBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel

class LogoutDialog(override val vm: MyPageViewModel) :
    BaseDialog<DialogLogoutBinding>(R.layout.dialog_logout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.run {
            binding.checkLogoutTv.setOnClickListener {
                dismiss()
            }
            binding.checkCancelTv.setOnClickListener {
                Toast.makeText(requireContext(),"로그아웃 성공",Toast.LENGTH_SHORT).show()
                (requireActivity() as MainActivity).startLogin()
            }
        }

    }
}