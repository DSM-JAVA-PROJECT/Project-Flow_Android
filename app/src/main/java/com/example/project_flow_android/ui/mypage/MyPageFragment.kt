package com.example.project_flow_android.ui.mypage

import android.os.Bundle
import android.view.View
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentMyPageBinding
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.ui.mypage.dialog.LogoutDialog
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page){

     override val vm : MyPageViewModel by viewModel()
     val vv : ChangePasswordViewModel by viewModel()

    private val logoutDialog by lazy {
        LogoutDialog(vm)
    }

    private val changepasswordDialog by lazy {
        ChangePasswordDialog(vv)
    }

    private val showLogoutDialog by lazy {
        logoutDialog.show(
            requireActivity().supportFragmentManager,
            "logoutDialog"
        )
    }

    private val showChangePasswordDialog by lazy {
        changepasswordDialog.show(
            requireActivity().supportFragmentManager,
            "changePasswordDialog"
        )
    }


    private fun getUserInfo(){
        vm.run {
            getUserInfo()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserInfo()
        observeEvent()

    }

     override fun observeEvent() {
         binding.run {
             binding.logoutTv.setOnClickListener{
                 showLogoutDialog
             }

             binding.changePwTv.setOnClickListener {
                 showChangePasswordDialog
             }
         }
    }
}