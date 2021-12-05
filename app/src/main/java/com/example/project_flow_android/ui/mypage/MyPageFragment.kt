package com.example.project_flow_android.ui.mypage


import android.os.Bundle
import android.view.View
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.FragmentMyPageBinding
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.ui.mypage.dialog.LogoutDialog
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import gun0912.tedimagepicker.builder.TedRxImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override val vm: MyPageViewModel by viewModel()
    private val projectAdapter by lazy { UserProjectRVAdapter(vm) }

    private val logoutDialog by lazy {
        LogoutDialog(vm)
    }

    private val changepasswordDialog by lazy {
        ChangePasswordDialog()
    }

    private fun showLogoutDialog() {
        logoutDialog.show(
            requireActivity().supportFragmentManager,
            "logoutDialog"
        )
    }

    private fun showChangePasswordDialog() {
        changepasswordDialog.show(
            requireActivity().supportFragmentManager,
            "changePasswordDialog"
        )
    }

    private fun getImage() {
        TedRxImagePicker.with(requireActivity())
            .start()
            .subscribe({ uri ->
                val imagePath = uri.toRealPath(requireActivity())
                vm.imagePath = imagePath
                binding.profileImg.setImageURI(uri)
                vm.postProfileImage()
            }, Throwable::printStackTrace)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
        selectProfileImage()
        showRV()
        binding.logoutTv.setOnClickListener {
            showLogoutDialog()
        }
        binding.changePwTv2.setOnClickListener {
            showChangePasswordDialog()
        }
        vm.getUserInfo()
    }

    private fun showRV() {
        binding.userRv.adapter = projectAdapter
        binding.userRv.addItemDecoration(VerticalItemDecorator(20))
    }

    private fun selectProfileImage() {
        binding.run {
            addProfileImg.setOnClickListener {
                getImage()
            }
        }
    }

    override fun observeEvent() {

        vm.run {
            projects.observe(viewLifecycleOwner, {
                projectAdapter.setItem(it.projects)
            })
            successImage.observe(viewLifecycleOwner, {
                if(successImage.value == true){
                    getUserInfo()
                }
            })
            getProjectInfo()
        }
    }
}