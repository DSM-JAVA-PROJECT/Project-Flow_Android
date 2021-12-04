package com.example.project_flow_android.ui.mypage.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogChangepasswordBinding
import com.example.project_flow_android.ui.flow.FlowFragment
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordDialog :
    BaseDialog<DialogChangepasswordBinding>(R.layout.dialog_changepassword) {
    override val vm: ChangePasswordViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.run {
            binding.cancleTv.setOnClickListener {
                dismiss()
            }
            binding.confirmTv.setOnClickListener {
                changePassword()
            }
            successChange.observe(viewLifecycleOwner, {
                if(successChange.value == true){
                    Toast.makeText(requireContext(),"비밀번호 변경에 성공하였습니다",Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            })
        }
    }

}