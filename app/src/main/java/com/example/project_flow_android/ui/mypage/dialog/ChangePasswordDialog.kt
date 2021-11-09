package com.example.project_flow_android.ui.mypage.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogChangepasswordBinding
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel

class ChangePasswordDialog(override val vm: ChangePasswordViewModel) :
    BaseDialog<DialogChangepasswordBinding>(R.layout.dialog_changepassword) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmTv.setOnClickListener {
               vm.run {
                   changePassword()
                   successChange.observe(viewLifecycleOwner, {
                       if(it) {
                           Toast.makeText(context, "비밀번호 변경에 성공하였습니다", Toast.LENGTH_SHORT).show()
                           dismiss()
                       }
                   })
               }
        }

        binding.cancleTv.setOnClickListener {
            dismiss()
        }
    }

}