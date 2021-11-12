package com.example.project_flow_android.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentMyPageBinding
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.ui.mypage.dialog.LogoutDialog
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override val vm: MyPageViewModel by viewModel()
    private val cv: ChangePasswordViewModel by viewModel()
    private val projectAdapter by lazy { UserProjectRVAdapter(vm) }

    private val logoutDialog by lazy {
        LogoutDialog(vm)
    }

    private val changepasswordDialog by lazy {
        ChangePasswordDialog(cv)
    }

    private fun showLogoutDialog(){
        logoutDialog.show(
            requireActivity().supportFragmentManager,
            "logoutDialog"
        )
    }

    private fun showChangePasswordDialog(){
        changepasswordDialog.show(
            requireActivity().supportFragmentManager,
            "changePasswordDialog"
        )
    }


    private fun getUserInfo() {
        vm.run {
            getUserInfo()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getProjectInfo()
        binding.userRv.adapter = projectAdapter
        binding.userRv.addItemDecoration(VerticalItemDecorator(20))
        getUserInfo()
        observeEvent()

    }

    override fun observeEvent() {
        binding.run {
            binding.logoutTv.setOnClickListener {
                showLogoutDialog()
            }

            binding.changePwTv.setOnClickListener {
                showChangePasswordDialog()
            }
        }
        vm.run {
            projects.observe(viewLifecycleOwner, {
                projectAdapter.setItem(it.projects)
            })
        }
        vm.run {
            successChange.observe(viewLifecycleOwner,{
                //TODO image 업로드 성공시에
            })
        }
    }
}