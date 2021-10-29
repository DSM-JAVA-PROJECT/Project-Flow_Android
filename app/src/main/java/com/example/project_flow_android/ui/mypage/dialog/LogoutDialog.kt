package com.example.project_flow_android.ui.mypage.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogLogoutBinding
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutDialog():BaseDialog<DialogLogoutBinding>(R.layout.dialog_logout){

    override val vm : MyPageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkLogoutTv.setOnClickListener {
            vm.logout()
            dismiss()
        }
        binding.checkCancelTv.setOnClickListener {
            dismiss()
        }
    }

}