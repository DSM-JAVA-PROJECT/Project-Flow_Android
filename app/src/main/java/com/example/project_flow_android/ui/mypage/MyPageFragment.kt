package com.example.project_flow_android.ui.mypage

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.FragmentMyPageBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.ui.mypage.dialog.LogoutDialog
import com.example.project_flow_android.util.GalleryHelper
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import gun0912.tedimagepicker.builder.TedRxImagePicker
import kotlinx.android.synthetic.main.activity_dash_borad.*
import kotlinx.android.synthetic.main.fragment_my_page.*
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

    private fun getImage() {
        TedRxImagePicker.with(requireContext())
            .start()
            .subscribe({ uri ->
                val imagePath = uri.toRealPath(requireContext())
                vm.imagePath = imagePath
                binding.profileImg.setImageURI(uri)
            }, Throwable::printStackTrace)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getProjectInfo()
        binding.userRv.adapter = projectAdapter
        binding.userRv.addItemDecoration(VerticalItemDecorator(20))
        getUserInfo()
        observeEvent()
        binding.addProfileImg.setOnClickListener{
            getImage()
        }


    }

    override fun observeEvent() {
        vm.run {
            binding.logoutTv.setOnClickListener {
                showLogoutDialog()
            }
            binding.changePwTv.setOnClickListener {
                showChangePasswordDialog()
            }
            projects.observe(viewLifecycleOwner, {
                projectAdapter.setItem(it.projects)
            })
        }
        vm.run {
            successChange.observe(viewLifecycleOwner,{
                binding.profileImage = lastImage.value!!
            })
        }
    }
}