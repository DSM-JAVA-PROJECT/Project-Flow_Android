package com.example.project_flow_android.ui.mypage.dialog

import android.os.Bundle
import android.view.View
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogChangepasswordBinding
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordDialog : BaseDialog<DialogChangepasswordBinding>(R.layout.dialog_changepassword){

    override val vm : ChangePasswordViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}