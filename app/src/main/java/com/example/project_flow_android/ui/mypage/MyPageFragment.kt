package com.example.project_flow_android.ui.mypage


import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.FragmentMyPageBinding
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.ui.mypage.dialog.LogoutDialog
import com.example.project_flow_android.util.GalleryHelper
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import gun0912.tedimagepicker.builder.TedRxImagePicker
import kotlinx.android.synthetic.main.fragment_my_page.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


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

    private val photoSelect =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data?.data!!.path
                val uri = it.data?.data!!
                fileUpload(data!!, uri)
            }
        }

    private fun fileUpload(filePath: String, uri: Uri) {
        val file = File(filePath)
        var inputStream: InputStream? = null
        try {
            inputStream = requireActivity().contentResolver.openInputStream(uri)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val requestFile = byteArrayOutputStream.toByteArray()
            .toRequestBody("image/jpg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        vm.postProfileImage(body)
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
    }

    private fun showRV() {
        binding.userRv.adapter = projectAdapter
        binding.userRv.addItemDecoration(VerticalItemDecorator(20))
    }

    private fun selectProfileImage() {
        val galleryHelper = GalleryHelper(requireActivity())
        binding.run {
            addProfileImg.setOnClickListener {
                galleryHelper.selectPhoto(photoSelect)
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
            getUserInfo()
            getProjectInfo()
        }
    }
}